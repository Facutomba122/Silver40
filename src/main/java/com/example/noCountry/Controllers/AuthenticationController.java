package com.example.noCountry.Controllers;

import com.example.noCountry.DTO.RegistrationDTO;
import com.example.noCountry.Entity.User;
import com.example.noCountry.Services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
//@CrossOrigin("*")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationService authService;
    
    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationDTO body){
        return authService.registerUser(body.getUsername(), body.getPassword());
    }
}
