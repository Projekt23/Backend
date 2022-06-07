package com.project23.app.service;

import com.project23.app.Entity.BusinessObject;
import com.project23.app.Entity.Statistic;
import com.project23.app.repository.BusinessObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Service-Klasse für Operationen der Entität BusinessObject (get, add, update, delete).
 */

@Service
@RequiredArgsConstructor
public class BusinessObjectService {

    private final BusinessObjectRepository businessObjectRepository;
    private final UserService userService;
    private final StatisticService statisticService;

    public void addBusinessObject(BusinessObject bo, Long userId){
        if(businessObjectRepository.existsByNameAndDescription(bo.getName(), bo.getDescription())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Business Object already exists");
        } else {
            businessObjectRepository.save(bo);
            statisticService.addStatistic(new Statistic(
                    ZonedDateTime.now(ZoneId.of("GMT+2")),
                    bo,
                    1,
                    userService.getUser(userId)
            ));
        }
    }

    public List<BusinessObject> getAllBusinessObjects(){
       try{
           return businessObjectRepository.findAll();
       } catch (NoSuchElementException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Business Objects found.");
       }
    }

    public BusinessObject getBusinessObject(long id){
        if(businessObjectRepository.existsById(id)) {
            return businessObjectRepository.getById(id);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Business Object with ID " +id +" found.");
    }

    public void updateBusinessObject(BusinessObject bo, Long id, Long userId) {
        if(businessObjectRepository.existsById(id)) {
            BusinessObject boToUpdate = businessObjectRepository.getById(id);
            boToUpdate.setName(bo.getName());
            boToUpdate.setDescription((bo.getDescription()));
            boToUpdate.setSynonyms(bo.getSynonyms());
            boToUpdate.setLabels(bo.getLabels());
            boToUpdate.setContext(bo.getContext());
            businessObjectRepository.saveAndFlush(boToUpdate);
            statisticService.addStatistic(new Statistic(
                    ZonedDateTime.now(ZoneId.of("GMT+2")),
                    boToUpdate,
                    2,
                    userService.getUser(userId)
            ));
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Business Object with ID " +id +" found.");
    }

    public void deleteBusinessObject(Long id) {
        try {

            businessObjectRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Business Object with ID " +id +" found.");
        }
    }

    public BusinessObject getRandomBusinessObject() {
        return businessObjectRepository.getById((long)businessObjectRepository.getRandomBusinessObjectId());
    }
}
