package com.dineshmane.project.controller.publicEndpoints;

import com.dineshmane.project.entity.Customer;
import com.dineshmane.project.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        try {
            String passHash = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(passHash);
            Customer saved = customerService.saveUser(customer);
            if (saved.getId()>0){
                return ResponseEntity.status(HttpStatus.CREATED).body("User Registered Successfully!!!");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed registering user");
            }
        } catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An Exception Occurred: "+e.getMessage());
        }
    }

}
