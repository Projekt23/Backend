package com.project23.app.controller;

import com.project23.app.Entity.Label;
import com.project23.app.dto.DTOLabel;
import com.project23.app.dto.DTOUser;
import com.project23.app.helper.Mapper;
import com.project23.app.repository.LabelRepository;
import com.project23.app.service.LabelService;
import com.project23.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/label")
@Log
public class LabelController {

    private final Mapper m;
    private final LabelService labelService;
    private final LabelRepository labelRepository;

    @GetMapping(path = "/all")
    public List<Label> getAllLabels(){
        return labelService.getAllLabels();
    }

    @GetMapping(path = "/{id}")
    public DTOLabel getLabel(@PathVariable Long id) {
        return m.labelToDtoLabel(labelService.getLabel(id));
    }

    @PostMapping
    public void addLabel(@RequestParam String label) {
        labelService.addLabelIfNotExists(label);
    }

    @PutMapping(path = "/{id}")
    public void updateLabel(@RequestBody String label, @PathVariable Long id) {
        DTOLabel l = new DTOLabel(id, label);
        labelService.updateLabel(m.dtoLabelToLabel(l));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteLabel(@PathVariable long id) {
        labelService.deleteLabel(id);
    }


}