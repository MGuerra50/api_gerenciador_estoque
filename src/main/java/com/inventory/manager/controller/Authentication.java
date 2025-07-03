package com.inventory.manager.controller;

import com.inventory.manager.domain.users.LoginDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class Authentication {

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity login (@RequestBody LoginDetails loginDetails){

        var userNamePassword = new UsernamePasswordAuthenticationToken(loginDetails.email(), loginDetails.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody LoginDetails loginDetails){
        System.out.println(loginDetails);
        return null;
    }
}
