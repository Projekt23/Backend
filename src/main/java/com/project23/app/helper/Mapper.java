package com.project23.app.helper;

import com.project23.app.dto.*;
import com.project23.app.Entity.BusinessObject;
import com.project23.app.Entity.Label;
import com.project23.app.Entity.User;
import com.project23.app.service.BusinessObjectService;
import com.project23.app.service.LabelService;
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

    @Autowired
    private final LabelService labelService;

    public DTOUser userToDtoUser(User user) {
        return new DTOUser(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public DTOGetUser userToDtoGetUser(User user){
        return new DTOGetUser(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail()
        );
    }

    public User dtoUserToUser(DTOUser dtoUser) {
        return new User(
                dtoUser.getId(),
                dtoUser.getName(),
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
                dtoSynonymToBoList(dtoBo.getSynonyms()),
                dtoLabelToLabel(dtoBo.getLabels())
        );
    }

    public DTOBusinessObject boToDtoBo(BusinessObject bo) {
        return new DTOBusinessObject(
                bo.getId(),
                bo.getName(),
                bo.getDescription(),
                bo.getSourceSystem().getId(),
                boToDtoSynonymList(bo.getSynonyms()),
                labelToDtoLabel(bo.getLabels())
        );
    }

    public List<DTOSynonym> boToDtoSynonymList(List<BusinessObject> boList) {
        ArrayList<DTOSynonym> synonyms = new ArrayList<>();
        for(BusinessObject bo : boList) {
            synonyms.add(new DTOSynonym(
                    bo.getId(),
                    bo.getName(),
                    bo.getDescription()
            ));
        }
        return synonyms;
    }

    public List<BusinessObject> dtoSynonymToBoList(List<DTOSynonym> dtoSynonyms) {
        List<BusinessObject> boList = new ArrayList<>();
        if (!dtoSynonyms.isEmpty()) {
            for(DTOSynonym dtoSynonym : dtoSynonyms) {
                boList.add(boService.getBusinessObject(dtoSynonym.getId()));
            }
        }
        return boList;
    }

    public List<DTOLabel> labelToDtoLabel(List<Label> labels) {
        List<DTOLabel> dtoLabels = new ArrayList<>();
        for(Label l : labels) {
            dtoLabels.add(new DTOLabel(
                    l.getId(),
                    l.getName()
            ));
        }
        return dtoLabels;
    }

    public List<Label> dtoLabelToLabel(List<DTOLabel> dtoLabels) {
        List<Label> labelIdName = new ArrayList<>();
        List<Label> labels = new ArrayList<>();
        if(!dtoLabels.isEmpty()) {
            for(DTOLabel dl : dtoLabels) {
                labelIdName.add(new Label(dl.getId(), dl.getName()));
            }
        }
        if(!labelIdName.isEmpty()) {
            for(Label l : labelIdName) {
                labels.add(new Label(l.getId(), l.getName()));
            }
        }
        return labels;
    }



}
