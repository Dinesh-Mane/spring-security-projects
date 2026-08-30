package com.dineshmane.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {

    @GetMapping("/notices")
    public String getAllNotices(){
        return "Hello Dinesh!, here are all the notices and articles published by us lately...";
    }

}