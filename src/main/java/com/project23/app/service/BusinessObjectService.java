package com.project23.app.service;

import com.project23.app.Entity.BusinessObject;
import com.project23.app.repository.BusinessObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusinessObjectService {

    private final BusinessObjectRepository businessObjectRepository;

    public void addBusinessObject(BusinessObject bo){
        if(businessObjectRepository.existsByName(bo.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Business Object already exists");
        } else {
            businessObjectRepository.save(bo);
        }
    }

//    public List<BusinessObject> getAllBusinessObjects(){
//        List<BusinessObject> boList = businessObjectRepository.findAll();
//        if(boList.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Business Objects found.");
//        return boList;
//    }

    public List<BusinessObject> getAllBusinessObjects(){
       try{
           return businessObjectRepository.findAll();
       } catch (NoSuchElementException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Business Objects found.");
       }
    }

    // Ausführliche Methode unnötig? Siehe kurze Methode unten.
//    public BusinessObject getBusinessObject(long id){
//        try {
//            Optional<BusinessObject> o  = businessObjectRepository.findById(id);
//            BusinessObject bo = new BusinessObject(
//                    o.get().getId(),
//                    o.get().getName(),
//                    o.get().getDescription(),
//                    o.get().getSourceSystem(),
//                    o.get().getSynonyms(),
//                    o.get().getLabels()
//            );
//            return bo;
//        } catch (NoSuchElementException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Business Object with ID " +id +" found.");
//        }
//    }

    public BusinessObject getBusinessObject(long id){
        if(businessObjectRepository.existsById(id)) {
            return businessObjectRepository.getById(id);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Business Object with ID " +id +" found.");
    }

    public void updateBusinessObject(BusinessObject bo, Long id) {
        System.out.println(id);
        if(businessObjectRepository.existsById(id)) {
            BusinessObject boToUpdate = businessObjectRepository.getById(id);
            boToUpdate.setName(bo.getName());
            boToUpdate.setDescription((bo.getDescription()));
            boToUpdate.setSourceSystem(bo.getSourceSystem());
            boToUpdate.setSynonyms(bo.getSynonyms());
            boToUpdate.setLabels(bo.getLabels());
            businessObjectRepository.saveAndFlush(boToUpdate);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Business Object with ID " +id +" found.");
    }

    public void deleteBusinessObject(Long id) {
        try {
            businessObjectRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Business Object with ID " +id +" found.");
        }
    }
}
