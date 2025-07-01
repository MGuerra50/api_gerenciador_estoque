package com.inventory.manager.controller;

import com.inventory.manager.domain.users.LoginDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class Authentication {

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody LoginDetails loginDetails){
        System.out.println(loginDetails);
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody LoginDetails loginDetails){
        System.out.println(loginDetails);
        return null;
    }
}
