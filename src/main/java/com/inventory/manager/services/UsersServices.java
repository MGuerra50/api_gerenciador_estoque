package com.inventory.manager.services;

import com.inventory.manager.domain.users.UserDTO;
import com.inventory.manager.domain.users.Users;
import com.inventory.manager.domain.users.UsersRepository;
import com.inventory.manager.exception.ResourceNotFoundException;
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
    }

    public Users findById (Long id){
        return repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Usuário com id \"" + id + "\" não encontrado!")
        );
    }
}
