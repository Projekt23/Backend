package com.project23.app.service;

import com.project23.app.Entity.Label;
import com.project23.app.repository.LabelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service-Klasse für Operationen der Entität Label (get, add, update, delete).
 */

@Service
@RequiredArgsConstructor
public class LabelService {


    private final LabelRepository labelRepository;

    /**
     * Gibt alle Labels aus der Datenbank zurück.
     * @return Liste von Labels
     */
    public List<Label> getAllLabels() {
        return labelRepository.findAll();
    }

    /**
     * Speichert ein neues Label in der Datenbank.
     * @param l Label
     */
    public void addLabel(String l) {
        if(!labelRepository.existsByName(l)) {
            labelRepository.save(new Label(l));
        } else throw new ResponseStatusException(HttpStatus.CONFLICT, "Label " +l +" already exists.");
    }

    /**
     * Speichert ein neues Label in der Datenbank, falls dieses noch nicht existiert.
     * @param l Label
     * @return Label das erstellt wurde
     */
    public Label addLabelIfNotExists(String l) {
        if(!labelRepository.existsByName(l)) {
            labelRepository.save(new Label(l));
        }
        return labelRepository.getLabelByName(l);
    }

    /**
     * Ändert ein Label (z.B. bei Rechtschreibfehlern)
     * @param l Aktualisiertes Label
     */
    public void updateLabel(Label l) {
        if(labelRepository.existsById(l.getId())){
            Label labelUpdate = labelRepository.getById(l.getId());
            labelUpdate.setName(l.getName());
            labelRepository.saveAndFlush(labelUpdate);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Label " +l.getName() +" found.");
    }

    /**
     * Löscht ein Label anhand der ID.
     * @param id LabelId
     */
    public void deleteLabel(Long id) {
        if(labelRepository.existsById(id)){
            deleteLabelObjectConnection(id);
            labelRepository.deleteById(id);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Label with ID " +id +" found.");
    }

    /**
     * Löscht ein Label anhand der Bezeichnung.
     * @param label LabelName
     */
    public void deleteLabel(String label) {
        if(labelRepository.existsByName(label)){
            Label l = getLabel(label);
            deleteLabelObjectConnection(l.getId());
            labelRepository.deleteById(l.getId());
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Label " +label +" found.");
    }

    /**
     * Löscht alle Relationen zwischen einem Label und den BusinessObjects.
     * @param id Label, dessen Relationen gelöscht werden
     */
    private void deleteLabelObjectConnection(Long id) {
        labelRepository.deleteLabelConnection(id);
    }

    /**
     * Gibt ein Label anhand dessen ID zurück.
     * @param id LabelId
     * @return Label
     */
    public Label getLabel(Long id) {
        if(labelRepository.existsById(id)) {
            return labelRepository.getById(id);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Label with ID " +id +" found.");

    }

    /**
     * Gibt ein Label anhand dessen Bezeichnung zurück.
     * @param label LabelName
     * @return Label
     */
    public Label getLabel(String label) {
        if(labelRepository.existsByName(label)){
            return labelRepository.getLabelByName(label);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Label " +label +" found.");
    }



}
