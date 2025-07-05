package com.inventory.manager.controller;

import com.inventory.manager.domain.users.LoginDetails;
import com.inventory.manager.domain.users.RegisterDTO;
import com.inventory.manager.domain.users.Users;
import com.inventory.manager.domain.users.UsersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class Authentication {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRepository repository;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid LoginDetails loginDetails){

        var userNamePassword = new UsernamePasswordAuthenticationToken(loginDetails.email(), loginDetails.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);

        return ResponseEntity.ok().build();

    }

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody @Valid RegisterDTO registerDTO){
        if(this.repository.findByEmail(registerDTO.email()) != null){
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        Users user = new Users(registerDTO.email(), encryptedPassword, registerDTO.roles());

        this.repository.save(user);
        return ResponseEntity.ok().build();
    }
}
