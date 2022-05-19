package com.project23.app.controller;

import com.project23.app.Entity.Label;
import com.project23.app.dto.DTOLabel;
import com.project23.app.helper.Mapper;
import com.project23.app.repository.LabelRepository;
import com.project23.app.service.LabelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public DTOLabel getLabel(@RequestParam String label) {
        return m.labelToDtoLabel(labelService.getLabel(label));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Created Label.")
    public void addLabel(@RequestParam String label) {
        labelService.addLabel(label.toUpperCase());
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Updated Label.")
    public void updateLabel(@RequestBody String label, @PathVariable Long id) {
        DTOLabel l = new DTOLabel(id, label.toUpperCase());
        labelService.updateLabel(m.dtoLabelToLabel(l));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Deleted Label.")
    public void deleteLabel(@PathVariable Long id) {
        labelService.deleteLabel(id);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.OK, reason = "Deleted Label.")
    public void deleteLabel(@RequestParam String label) {
        labelService.deleteLabel(label);
    }


}