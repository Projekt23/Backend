package com.project23.app.controller;

import com.project23.app.Entity.Label;
import com.project23.app.dto.DTOBusinessObject;
import com.project23.app.dto.DTOCreateBusinessObject;
import com.project23.app.helper.Mapper;
import com.project23.app.Entity.BusinessObject;
import com.project23.app.service.BusinessObjectService;
import com.project23.app.service.LabelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<DTOBusinessObject> getBusinessObject(@PathVariable Long id){
        return ResponseEntity.ok().body(m.boToDtoBo(businessObjectService.getBusinessObject(id)));
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
        businessObjectService.deleteBusinessObject(id);
    }

}