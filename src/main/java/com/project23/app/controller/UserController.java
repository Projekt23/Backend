package com.project23.app.controller;

import com.project23.app.dto.DTOCreateUser;
import com.project23.app.dto.DTOGetUser;
import com.project23.app.helper.Mapper;
import com.project23.app.Entity.User;
import com.project23.app.service.FavouriteService;
import com.project23.app.service.StatisticService;
import com.project23.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller-Klasse für den Datentransfer zwischen der Spring-Web-Applikation und dem Web-Client.
 * Stellt diverse Endpunkte für die Entität User zur Verfügung.
 */

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
@Log
public class UserController {

    private final Mapper m;
    private final UserService userService;
    private final StatisticService statisticService;
    private final FavouriteService favouriteService;

    @GetMapping(path = "/all")
    public List<DTOGetUser> getAllUser(){
        List<DTOGetUser> allUser = new ArrayList();
        for(User u : userService.getAllUser()) {
            allUser.add(m.userToDtoGetUser(u));
        }
        return allUser;
    }

    @GetMapping(path = "/{id}")
    public DTOGetUser getUser(@PathVariable long id){
       User user = userService.getUser(id);
       return m.userToDtoGetUser(user);
    }
    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable long id){
        statisticService.deleteStatsticByUser(id);
        favouriteService.deleteFavByUser(id);
        userService.deleteUser(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@RequestBody DTOCreateUser user, @PathVariable long id){
        userService.updateUser(m.dtoCreateUserToUserWithId(user, id));
    }
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public long createUser(@RequestBody DTOCreateUser user){
        User u  = m.dtoCreateUserToUser(user);
        return  userService.addUser(u);
    }

}