package com.project23.app.helper;

import com.project23.app.Entity.*;
import com.project23.app.dto.*;
import com.project23.app.service.*;
import com.project23.app.service.BusinessObjectService;
import com.project23.app.service.LabelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class Mapper {

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


    public BusinessObject dtoCreateBoToBo(DTOCreateBusinessObject dtoCBo){
        return new BusinessObject(
                dtoCBo.getName(),
                dtoCBo.getDescription(),
                dtoSynonymToBoList(dtoCBo.getSynonymIds()),
                dtoLabelToLabelList(dtoCBo.getLabels()),
                dtoContextToBoList(dtoCBo.getContextIds())
        );
    }

    public DTOBusinessObject boToDtoBo(BusinessObject bo) {
        return new DTOBusinessObject(
                bo.getId(),
                bo.getName(),
                bo.getDescription(),
                boToDtoSynonymList(bo.getSynonyms()),
                labelToDtoLabelList(bo.getLabels()),
                boToDtoContextList(bo.getContext())
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

    public List<DTOContext> boToDtoContextList(List<BusinessObject> boList) {
        ArrayList<DTOContext> contextList = new ArrayList<>();
        for(BusinessObject bo : boList) {
            contextList.add(new DTOContext(
                    bo.getId(),
                    bo.getName(),
                    bo.getDescription()
            ));
        }
        return contextList;
    }

    public List<BusinessObject> dtoContextToBoList(ArrayList<Long> contextIds) {
        List<BusinessObject> boList = new ArrayList<>();
        if (!contextIds.isEmpty()) {
            for(Long id : contextIds) {
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
                f.getUserId(),
                f.getBusinessObjectId()
        );
    }

    public Favourite dtoFavtoFav(DTOFavourite df) {
        return new Favourite(
                df.getUserId(),
                df.getBusinessObjectId()
        );
    }

    public List<DTOChangeHistory> statisticsToDtoChangeHistory(List<Statistic> statistics) {
        List<DTOChangeHistory> changeHistory = new ArrayList<>();
        for(Statistic s : statistics) {
            changeHistory.add(new DTOChangeHistory(
                    s.getBusinessObject().getId(),
                    s.getBusinessObject().getName(),
                    s.getUser().getName(),
                    s.getTimestamp()
            ));
        }
        return changeHistory;
    }

    public List<DTOLastSeen> statisticsToDtoLastSeen(List<Statistic> statistics) {
        List<DTOLastSeen> lastSeen = new ArrayList<>();
        for(Statistic s : statistics) {
            lastSeen.add(new DTOLastSeen(
                    s.getBusinessObject().getId(),
                    s.getBusinessObject().getName(),
                    s.getTimestamp()
            ));
        }
        return lastSeen;
    }

    public DTORandom boToDtoRandom() {
        BusinessObject bo = boService.getRandomBusinessObject();
        return new DTORandom(
                bo.getId(),
                bo.getName(),
                bo.getDescription()
        );
    }

}
