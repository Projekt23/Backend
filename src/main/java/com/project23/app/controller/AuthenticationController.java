package com.project23.app.controller;

import com.project23.app.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
@Log
public class AuthenticationController {

    private final AuthenticationService authService;

    @GetMapping(path = "/jwt")
    public String generateJWT(@RequestParam String mail){
        return authService.createJWT(mail);
    }
    @GetMapping(path = "/mail")
    public void sendRegistrationMail(@RequestParam String mail){
        String token = authService.createJWT(mail);
        authService.sendRegistrationMail(mail,token);
    }

    @GetMapping(path = "/validate")
    public String validateToken(@RequestParam String token){
        return authService.validateToken(token);
    }

}
