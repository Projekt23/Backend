package com.project23.app.controller;

import com.project23.app.Entity.User;
import com.project23.app.dto.DTOCreateUser;
import com.project23.app.dto.DTOEmailLogin;
import com.project23.app.dto.DTOGetUser;
import com.project23.app.dto.DTOUserNameLogin;
import com.project23.app.helper.Mapper;
import com.project23.app.service.AuthenticationService;
import com.project23.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Controller-Klasse für den Datentransfer zwischen der Spring-Web-Applikation und dem Web-Client.
 * Stellt diverse Endpunkte für die Authentifizierung zur Verfügung.
 */

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
@Log
public class AuthenticationController {

    private final AuthenticationService authService;
    private final UserService userService;

    private final Mapper m;

    @GetMapping(path = "/jwt")
    public String generateJWT(@RequestParam String mail, @RequestParam Integer expSek){
        return authService.generateRegisterToken(mail,0,0,0,expSek);
    }
    @GetMapping(path = "/mail")
    public void sendRegistrationMail(@RequestParam String mail){
        String token = authService.generateRegisterToken(mail,1,0,0,0);
        authService.sendRegistrationMail(mail,token);
    }
    @GetMapping(path = "/validateRegisterToken")
    public String validateRegisterToken(@RequestParam String token){
        return authService.validateRegisterToken(token);
    }

    @GetMapping(path = "/validateUserToken")
    public DTOGetUser validateUserToken(@RequestParam String token){
        User user = authService.validateUserToken(token);
        return m.userToDtoGetUser(user);
    }


    @PostMapping(path = "/register")
    public String register(@RequestBody DTOCreateUser user){
        Long UserID = userService.addUser(m.dtoCreateUserToUser(user));
        User createdUser = userService.getUser(UserID);
        log.info("ID: "+UserID+"| "+createdUser.toString());
        return authService.authenticateWithEmail(createdUser.getEmail(), createdUser.getPassword());
    }

    @PostMapping(path = "/login/user")
    public String login(@RequestBody DTOUserNameLogin user){
        return authService.authenticateWithUserName(user.getUsername(), user.getPassword());
    }

    @PostMapping(path = "/login/mail")
    public String login(@RequestBody DTOEmailLogin user){
        return authService.authenticateWithEmail(user.getEmail(), user.getPassword());
    }


}
