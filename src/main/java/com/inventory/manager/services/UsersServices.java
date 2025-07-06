package com.inventory.manager.services;

import com.inventory.manager.domain.users.UserDTO;
import com.inventory.manager.domain.users.Users;
import com.inventory.manager.domain.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServices {

    @Autowired
    UsersRepository repository;

    public List<UserDTO> findAll(){
        return repository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    };
}
