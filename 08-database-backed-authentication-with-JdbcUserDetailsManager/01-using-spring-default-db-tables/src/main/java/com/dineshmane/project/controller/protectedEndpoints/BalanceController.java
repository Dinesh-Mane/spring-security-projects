package com.dineshmane.project.controller.protectedEndpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @GetMapping("/myBalance")
    public String getBalanceDetails(){
        return "Hello Dinesh!, here are your Balance details from the DB";
    }

}