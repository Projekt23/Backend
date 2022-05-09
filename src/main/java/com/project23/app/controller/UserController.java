package com.project23.app.controller;

import com.project23.app.dto.DTOUser;
import com.project23.app.helper.Mapper;
import com.project23.app.pojo.User;
import com.project23.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
@Log
public class UserController {

    private final Mapper m;
    private final UserService userService;

    @GetMapping(path = "/")
    public String home(){
        return ("Jonas ist der coolste");
    }

    // Passwort wird noch mit geschickt
    @GetMapping(path = "/all")
    public List<DTOUser> getAllUser(){
        List<DTOUser> allUser = new ArrayList();
        for(User u : userService.getAllUser()) {
            allUser.add(m.userToDtoUser(u));
        }
        return allUser;
    }


}