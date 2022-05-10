package com.project23.app.controller;

import com.project23.app.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
@Log
public class AuthenticationController {

    private final AuthenticationService authService;

    @GetMapping(path = "/sendMail")
    public String sendRegistrationMail(){
        return authService.createToken("test").toString();

    }
}
