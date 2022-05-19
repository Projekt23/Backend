package com.project23.app.controller;

import com.project23.app.Entity.Label;
import com.project23.app.Entity.Statistic;
import com.project23.app.dto.DTOBusinessObject;
import com.project23.app.dto.DTOCreateBusinessObject;
import com.project23.app.helper.Mapper;
import com.project23.app.Entity.BusinessObject;
import com.project23.app.service.BusinessObjectService;
import com.project23.app.service.LabelService;
import com.project23.app.service.StatisticService;
import com.project23.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

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
    public void addBusinessObject(@RequestBody DTOCreateBusinessObject dtoCBo){
        BusinessObject bo = m.dtoCreateBoToBo(dtoCBo);
        List<Label> newLabels = new ArrayList();
        for (Label l : bo.getLabels()) {
            newLabels.add(labelService.addLabelIfNotExists(l.getName()));
        }
        bo.setLabels(newLabels);
        businessObjectService.addBusinessObject(bo);
        statisticService.addStatistic(new Statistic(
                new Date(System.currentTimeMillis()),
                bo,
                1,
                userService.getUser(1)
        ));
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<DTOBusinessObject> getBusinessObject(@PathVariable Long id){
        BusinessObject bo = businessObjectService.getBusinessObject(id);
        statisticService.addStatistic(new Statistic(
                new Date(System.currentTimeMillis()),
                bo,
                3,
                userService.getUser(1)
        ));
        return ResponseEntity.ok().body(m.boToDtoBo(bo));
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Updated Business Object.")
    public void updateBusinessObject(@RequestBody DTOCreateBusinessObject dtoCBo, @PathVariable Long id) {
        BusinessObject bo = m.dtoCreateBoToBo(dtoCBo);
        businessObjectService.updateBusinessObject(bo, id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Deleted Business Object.")
    public void deleteBusinessObject(@PathVariable Long id) {
        statisticService.deleteStatisticByBo(id);
        businessObjectService.deleteBusinessObject(id);
    }

}