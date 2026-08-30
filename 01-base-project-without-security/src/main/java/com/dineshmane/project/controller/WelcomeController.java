package com.dineshmane.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public String sayHello(){
        return "Hello Dinesh, Welcome to home page!";
    }
}
