package com.project23.app.service;

import com.project23.app.pojo.BusinessObject;
import com.project23.app.pojo.User;
import com.project23.app.repository.BusinessObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessObjectService {

    private final BusinessObjectRepository businessObjectRepository;

    public void addBusinessObject(BusinessObject bo){
        businessObjectRepository.save(bo);
    }

    public List<BusinessObject> getAllBusinessObjects(){
        return (List<BusinessObject>) businessObjectRepository.findAll();
    }

    public void updateBusinessObject(BusinessObject bo) {
        businessObjectRepository.save(bo);
    }

    public BusinessObject getBusinessObject(long id){
        return businessObjectRepository.getById(id);
    }

}
