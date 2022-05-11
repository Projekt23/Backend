package com.project23.app.service;

import com.project23.app.Entity.BusinessObject;
import com.project23.app.repository.BusinessObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessObjectService {

    private final BusinessObjectRepository businessObjectRepository;

    public String addBusinessObject(BusinessObject bo){
        if(businessObjectRepository.existsByName(bo.getName())){
            return "Object already exists! Please chance existing Object.";
        } else {
            businessObjectRepository.save(bo);
            return "Added Object";
        }

//        List<BusinessObject> boList = getAllBusinessObjects();
//        if(!boList.isEmpty()) {
//            for (BusinessObject bObject : boList) {
//                if (!bObject.getName().equals(bo.getName())) {
//                    businessObjectRepository.save(bo);
//                    return "ok";
//                } else {
//                    return "Object already exists! Please chance existing Object.";
//                }
//            }
//        } else {
//            businessObjectRepository.save(bo);
//            return "ok";
//        }
//        return "failed!";
    }

    public List<BusinessObject> getAllBusinessObjects(){
        return (List<BusinessObject>) businessObjectRepository.findAll();
    }

    public BusinessObject getBusinessObject(long id){
        return businessObjectRepository.getById(id);
    }

    // Update whole Object
    public void updateBusinessObject(BusinessObject bo) {
        BusinessObject boToUpdate = businessObjectRepository.getById(bo.getId());
        boToUpdate.setName(bo.getName());
        boToUpdate.setDescription((bo.getDescription()));
        boToUpdate.setSourceSystem(bo.getSourceSystem());
        boToUpdate.setSynonyms(bo.getSynonyms());
        boToUpdate.setLabels(bo.getLabels());
        businessObjectRepository.saveAndFlush(boToUpdate);
    }

//    Update not whole Objects?
//    public void updateBusinessObject(BusinessObject bo) {
//        BusinessObject oldbo = businessObjectRepository.getById(bo.getId());
//        BusinessObject newbo = new BusinessObject();
//        if(!bo.getName().equals(oldbo.getName()) && !bo.getName().equals(null)) {
//            newbo.setName(bo.getName());
//        } else newbo.setName(oldbo.getName());
//        if(!bo.getDescription().equalsIgnoreCase(oldbo.getDescription().toLowerCase()) && !bo.getDescription().equals(null)) {
//            newbo.setDescription(bo.getDescription());
//        } else newbo.setDescription(oldbo.getDescription());
//        if(!bo.getSourceSystem().equals(oldbo.getSourceSystem()) && !bo.getSourceSystem().equals(null)) {
//            newbo.setSourceSystem(bo.getSourceSystem());
//        } else newbo.setSourceSystem(oldbo.getSourceSystem());
//        // synonyms vergleichen
//
//        businessObjectRepository.save(newbo);
//    }



}
