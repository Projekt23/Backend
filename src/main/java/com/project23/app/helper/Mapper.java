package com.project23.app.helper;

import com.project23.app.Entity.Favourite;
import com.project23.app.dto.*;
import com.project23.app.service.*;
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
    private final UserService userService;

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
                user.getUsername(),
                user.getEmail(),
                user.getName()
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

    // Nicht mehr benötigt! Nach Test löschen
//    public BusinessObject dtoBoToBo(DTOBusinessObject dtoBo){
//        return new BusinessObject(
//                dtoBo.getId(),
//                dtoBo.getName(),
//                dtoBo.getDescription(),
//                sourceSystemService.getSourceSystem(dtoBo.getSourceSystemId()),
//                dtoSynonymToBoList(dtoBo.getSynonyms()),
//                dtoLabelToLabelList(dtoBo.getLabels())
//        );
//    }

    public BusinessObject dtoCreateBoToBo(DTOCreateBusinessObject dtoCBo){
        return new BusinessObject(
                dtoCBo.getName(),
                dtoCBo.getDescription(),
                sourceSystemService.getSourceSystem(dtoCBo.getSourceSystemId()),
                dtoSynonymToBoList(dtoCBo.getSynonymIds()),
                dtoLabelToLabelList(dtoCBo.getLabels())
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

    public List<BusinessObject> dtoSynonymToBoList(ArrayList<Long> synonymIds) {
        List<BusinessObject> boList = new ArrayList<>();
        if (!synonymIds.isEmpty()) {
            for(Long id : synonymIds) {
                boList.add(boService.getBusinessObject(id));
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

    public List<Label> dtoLabelToLabelList(ArrayList<String> labelNames) {
        List<Label> labels = new ArrayList<>();
        if(!labelNames.isEmpty()) {
            for(String label : labelNames) {
                labels.add(labelStringToLabel(label));
            }
        }
        return labels;
    }

    public DTOLabel labelToDtoLabel(Label l) {
        return new DTOLabel(
                    l.getId(),
                    l.getName());
    }

    public Label labelStringToLabel (String label) {
       return labelService.addLabelIfNotExists(label);
    }

    public Label dtoLabelToLabel(DTOLabel dl) {
        return new Label(dl.getId(), dl.getName());
    }

    public DTOFavourite favToDtoFav(Favourite f) {
        return new DTOFavourite(
                f.getId(),
                f.getUser().getId(),
                f.getBusinessObject().getId()
        );
    }

    public Favourite dtoFavtoFav(DTOFavourite df) {
        return new Favourite(
                df.getId(),
                userService.getUser(df.getUserId()),
                boService.getBusinessObject(df.getBusinessObjectId())
        );
    }


}
