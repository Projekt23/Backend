package com.project23.app.controller;

import com.project23.app.dto.DTOBusinessObject;
import com.project23.app.helper.Mapper;
import com.project23.app.pojo.BusinessObject;
import com.project23.app.pojo.User;
import com.project23.app.service.BusinessObjectService;
import com.project23.app.service.LabelService;
import com.project23.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
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


    @GetMapping(path = "/all")
    public List<DTOBusinessObject> getAllBusinessObjects(){
        List<DTOBusinessObject> allBo = new ArrayList<>();
        for(BusinessObject bo : businessObjectService.getAllBusinessObjects()) {
            allBo.add(m.boToDtoBo(bo));
        }
        return allBo;
    }

    @PostMapping(path = "/new")
    public void addBusinessObject(@RequestBody DTOBusinessObject dtoBo){
        BusinessObject bo = m.dtoBoToBo(dtoBo);
        businessObjectService.addBusinessObject(bo);
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<DTOBusinessObject> getBusinessObject(@PathVariable Long id){
        return ResponseEntity.ok().body(m.boToDtoBo(businessObjectService.getBusinessObject(id)));
    }

    @PutMapping("/update")
    public void updateBusinessObject(@RequestBody DTOBusinessObject dtoBo) {
        BusinessObject bo = m.dtoBoToBo(dtoBo);
        businessObjectService.updateBusinessObject(bo);
    }

}