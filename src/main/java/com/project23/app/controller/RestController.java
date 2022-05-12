package com.project23.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "*")

public class RestController {
    @GetMapping(path = "/")
    public String home(){
        return ("Jonas ist der coolste");
    }

}
