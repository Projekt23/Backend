package com.project23.app.service;

import com.project23.app.Entity.Label;
import com.project23.app.repository.LabelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelService {


    private final LabelRepository labelRepository;

    public List<Label> getAllLabels() {
        return labelRepository.findAll();
    }

    public void addLabel(String l) {
        if(!labelRepository.existsByName(l)) {
            labelRepository.save(new Label(l));
        } else throw new ResponseStatusException(HttpStatus.CONFLICT, "Label " +l +" already exists.");
    }

    public Label addLabelIfNotExists(String l) {
        if(!labelRepository.existsByName(l)) {
            labelRepository.save(new Label(l));
        }
        return labelRepository.getLabelByName(l);
    }

    public void updateLabel(Label l) {
        if(labelRepository.existsById(l.getId())){
            Label labelUpdate = labelRepository.getById(l.getId());
            labelUpdate.setName(l.getName());
            labelRepository.saveAndFlush(labelUpdate);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Label " +l.getName() +" found.");
    }

    public void deleteLabel(Long id) {
        if(labelRepository.existsById(id)){
            deleteLabelObjectConnection(id);
            labelRepository.deleteById(id);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Label with ID " +id +" found.");
    }

    public void deleteLabel(String label) {
        if(labelRepository.existsByName(label)){
            Label l = getLabel(label);
            deleteLabelObjectConnection(l.getId());
            labelRepository.deleteById(l.getId());
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Label " +label +" found.");
    }

    private void deleteLabelObjectConnection(Long id) {
        labelRepository.deleteLabelConnection(id);
    }

    public Label getLabel(Long id) {
        if(labelRepository.existsById(id)) {
            return labelRepository.getById(id);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Label with ID " +id +" found.");

    }

    public Label getLabel(String label) {
        if(labelRepository.existsByName(label)){
            return labelRepository.getLabelByName(label);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Label " +label +" found.");
    }



}
