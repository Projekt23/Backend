package com.project23.app.service;

import com.project23.app.pojo.Label;
import com.project23.app.repository.LabelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelService {

    private final LabelRepository labelRepository;

    public List<Label> getAllLabels() {
        return labelRepository.findAll();
    }

    public void checkAddLabels(List<Label> labelList) {
        for(Label l : labelList) {
            addLabelIfNotExists(l.getName());
        }
    }

    public void addLabelIfNotExists(String l) {
        if(!checkLabelExists(l)) {
            labelRepository.save(new Label(l));
        }
    }

    public boolean checkLabelExists(String l) {
        return labelRepository.existsByName(l);
    }

    public void updateLabel(Label l) {
        Label labelUpdate = labelRepository.getById(l.getId());
        labelUpdate.setName(l.getName());
        labelRepository.saveAndFlush(labelUpdate);
    }

    public void deleteLabel(long id) {
        deleteLabelObjectConnection(id);
        labelRepository.deleteById(id);
    }

    private void deleteLabelObjectConnection(long id) {
        labelRepository.deleteLabelConnection(id);
    }
}
