package com.project23.app.controller;

import com.project23.app.Entity.Label;
import com.project23.app.Entity.Statistic;
import com.project23.app.dto.DTOBusinessObject;
import com.project23.app.dto.DTOCreateBusinessObject;
import com.project23.app.helper.Mapper;
import com.project23.app.Entity.BusinessObject;
import com.project23.app.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.hibernate.internal.util.ZonedDateTimeComparator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/businessobject")
@Log
public class BusinessObjectController {

    private final Mapper m;
    private final BusinessObjectService businessObjectService;
    private final LabelService labelService;
    private final StatisticService statisticService;
    private final UserService userService;
    private final FavouriteService favouriteService;


    @GetMapping(path = "/all")
    public List<DTOBusinessObject> getAllBusinessObjects(){
        List<DTOBusinessObject> allBo = new ArrayList<>();
        for(BusinessObject bo : businessObjectService.getAllBusinessObjects()) {
            allBo.add(m.boToDtoBo(bo));
        }
        return allBo;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Created Business Object.")
    public void addBusinessObject(@RequestBody DTOCreateBusinessObject dtoCBo, @RequestParam Long userId){
        BusinessObject bo = m.dtoCreateBoToBo(dtoCBo);
        List<Label> newLabels = new ArrayList();
        for (Label l : bo.getLabels()) {
            newLabels.add(labelService.addLabelIfNotExists(l.getName().toUpperCase()));
        }
        bo.setLabels(newLabels);
        businessObjectService.addBusinessObject(bo, userId);
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<DTOBusinessObject> getBusinessObject(@PathVariable Long id, @RequestParam Long userId){
        BusinessObject bo = businessObjectService.getBusinessObject(id);
        statisticService.addStatistic(new Statistic(
                ZonedDateTime.now(ZoneId.of("GMT+2")),
                bo,
                3,
                userService.getUser(userId)
        ));
        return ResponseEntity.ok().body(m.boToDtoBo(bo));
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Updated Business Object.")
    public void updateBusinessObject(@RequestBody DTOCreateBusinessObject dtoCBo, @PathVariable Long id, @RequestParam Long userId) {
        BusinessObject bo = m.dtoCreateBoToBo(dtoCBo);
        for(Label l : bo.getLabels()) {
            l.setName(l.getName().toUpperCase());
        }
        businessObjectService.updateBusinessObject(bo, id, userId);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Deleted Business Object.")
    public void deleteBusinessObject(@PathVariable Long id) {
        statisticService.deleteStatisticByBo(id);
        favouriteService.deleteFavByBo(id);
        businessObjectService.deleteBusinessObject(id);
    }

}