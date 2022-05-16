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
    }

    public List<BusinessObject> getAllBusinessObjects(){
        return (List<BusinessObject>) businessObjectRepository.findAll();
    }

    public BusinessObject getBusinessObject(long id){
        return businessObjectRepository.getById(id);
    }

    public void updateBusinessObject(BusinessObject bo) {
        BusinessObject boToUpdate = businessObjectRepository.getById(bo.getId());
        boToUpdate.setName(bo.getName());
        boToUpdate.setDescription((bo.getDescription()));
        boToUpdate.setSourceSystem(bo.getSourceSystem());
        boToUpdate.setSynonyms(bo.getSynonyms());
        boToUpdate.setLabels(bo.getLabels());
        businessObjectRepository.saveAndFlush(boToUpdate);
    }

    public void deleteBusinessObject(Long id) {
        businessObjectRepository.deleteById(id);
    }
}
