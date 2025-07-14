package com.inventory.manager.controller.Users;

import com.inventory.manager.domain.users.UserDTO;
import com.inventory.manager.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UsersServices usersServices;

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> findAll (){
        List<UserDTO> allUsers = usersServices.findAll();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById (Long id){
        return ResponseEntity.ok(new UserDTO(usersServices.findById(id)));
    }
}
