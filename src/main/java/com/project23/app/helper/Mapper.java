package com.project23.app.helper;

import com.project23.app.dto.DTOBusinessObject;
import com.project23.app.dto.DTOLabel;
import com.project23.app.dto.DTOSynonym;
import com.project23.app.dto.DTOUser;
import com.project23.app.pojo.BusinessObject;
import com.project23.app.pojo.Label;
import com.project23.app.pojo.User;
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
                dtoSynonymToBoList(dtoBo.getSynonyms()),
                dtoLabelToLabelList(dtoBo.getLabels())
        );
    }

    public DTOBusinessObject boToDtoBo(BusinessObject bo) {
        return new DTOBusinessObject(
                bo.getId(),
                bo.getName(),
                bo.getDescription(),
                bo.getSourceSystem().getId(),
                boToDtoSynonymList(bo.getSynonyms()),
                labelToDtoLabelList(bo.getLabels())
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

    public List<DTOLabel> labelToDtoLabelList(List<Label> labels) {
        List<DTOLabel> dtoLabels = new ArrayList<>();
        for(Label l : labels) {
            dtoLabels.add(labelToDtoLabel(l));
        }
        return dtoLabels;
    }

    public List<Label> dtoLabelToLabelList(List<DTOLabel> dtoLabels) {
        List<Label> labels = new ArrayList<>();
        if(!dtoLabels.isEmpty()) {
            for(DTOLabel dl : dtoLabels) {
                labels.add(dtoLabelToLabel(dl));
            }
        }
        return labels;
    }

    public DTOLabel labelToDtoLabel(Label l) {
        return new DTOLabel(
                    l.getId(),
                    l.getName());
    }

    public Label dtoLabelToLabel(DTOLabel dl) {
        return new Label(dl.getId(), dl.getName());
    }



}
