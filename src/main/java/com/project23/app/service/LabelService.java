package com.project23.app.service;

import com.project23.app.Entity.Label;
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
            addLabelIfNotExists(l);
        }
    }

    public void addLabelIfNotExists(Label l) {
        if(!checkLabelExists(l)) {
            labelRepository.save(l);
        }
    }

    public boolean checkLabelExists(Label l) {
        return labelRepository.existsByName(l.getName());
    }

}
