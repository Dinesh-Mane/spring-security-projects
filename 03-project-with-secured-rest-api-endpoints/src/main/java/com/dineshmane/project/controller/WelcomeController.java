package com.dineshmane.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

//    for sanity checks
    @GetMapping("/welcome")
    public String sayHello(){
        return "Hello Dinesh!, Welcome to Home page";
    }
}