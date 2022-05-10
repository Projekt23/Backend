package com.project23.app.controller;

import com.project23.app.dto.DTOLabel;
import com.project23.app.dto.DTOUser;
import com.project23.app.helper.Mapper;
import com.project23.app.pojo.BusinessObject;
import com.project23.app.pojo.Label;
import com.project23.app.pojo.User;
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

    @PostMapping(path = "/new")
    public void addLabel(@RequestBody String l) {
        labelService.addLabelIfNotExists(l);
    }

    @PutMapping(path = "/update")
    public void updateLabel(@RequestBody DTOLabel l) {
        labelService.updateLabel(m.dtoLabelToLabel(l));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteLabel(@PathVariable long id) {
        labelService.deleteLabel(id);
    }


}