package com.dineshmane.project.controller.publicEndpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @GetMapping("/contact")
    public String saveContactInquiryDetails(){
        return "Hello Dinesh!, Inquiry details are saved to the DB";
    }

}