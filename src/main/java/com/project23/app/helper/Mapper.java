package com.project23.app.helper;

import com.project23.app.dto.DTOBusinessObject;
import com.project23.app.dto.DTOSynonym;
import com.project23.app.dto.DTOUser;
import com.project23.app.pojo.BusinessObject;
import com.project23.app.pojo.User;
import com.project23.app.service.BusinessObjectService;
import com.project23.app.service.SourceSystemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class Mapper {

    @Autowired
    private final SourceSystemService sourceSystemService;

    @Autowired
    private final BusinessObjectService boService;

    public DTOUser userToDtoUser(User user) {
        return new DTOUser(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEMail(),
                user.getPassword()
        );
    }

    public User dtoUserToUser(DTOUser dtoUser) {
        return new User(
                dtoUser.getId(),
                dtoUser.getFirstName(),
                dtoUser.getLastName(),
                dtoUser.getUsername(),
                dtoUser.getEMail(),
                dtoUser.getPassword()
        );
    }

    public BusinessObject dtoBoToBo(DTOBusinessObject dtoBo){
        return new BusinessObject(
                dtoBo.getId(),
                dtoBo.getName(),
                dtoBo.getDescription(),
                sourceSystemService.getSourceSystem(dtoBo.getSourceSystemId()),
                dtoSynonymToBoList(dtoBo.getSynonyms()));
    }

    public DTOBusinessObject boToDtoBo(BusinessObject bo) {
        return new DTOBusinessObject(
                bo.getId(),
                bo.getName(),
                bo.getDescription(),
                bo.getSourceSystem().getId(),
                boToDtoSynonymList(bo.getSynonyms())
        );
    }

    public List<DTOSynonym> boToDtoSynonymList(List<BusinessObject> bolist) {
        ArrayList<DTOSynonym> synonyms = new ArrayList<>();
        for(BusinessObject bo : bolist) {
            synonyms.add(new DTOSynonym(
                    bo.getId(),
                    bo.getName(),
                    bo.getDescription()
            ));
        }
        return synonyms;
    }

    public List<BusinessObject> dtoSynonymToBoList(List<DTOSynonym> dtoSynonyms) {
        ArrayList<BusinessObject> bolist = new ArrayList<>();
        if (!dtoSynonyms.isEmpty()) {
            for(DTOSynonym dtoSynonym : dtoSynonyms) {
                bolist.add(boService.getBusinessObject(dtoSynonym.getId()));
            }
        }
        return bolist;
    }


}
