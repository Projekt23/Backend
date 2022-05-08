package com.project23.app.controller;

import com.project23.app.pojo.BusinessObject;
import com.project23.app.pojo.User;
import com.project23.app.service.BusinessObjectService;
import com.project23.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/businessobject")
@Log
public class BusinessObjectController {

    private final BusinessObjectService businessObjectService;


    @GetMapping(path = "/all")
    public List<BusinessObject> getAllBusinessObjects(){
        return businessObjectService.getAllBusinessObjects();
    }

    @PostMapping(path = "/new")
    public void addBusinessObject(BusinessObject bo){
        businessObjectService.addBusinessObject(bo);
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<BusinessObject> getBusinessObject(@PathVariable Long id){
        return ResponseEntity.ok().body(businessObjectService.getBusinessObject(id));
    }

}