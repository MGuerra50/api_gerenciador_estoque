package com.inventory.manager.services;

import com.inventory.manager.domain.users.*;
import com.inventory.manager.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class UsersServices {

    @Autowired
    UsersRepository repository;

    public List<UserDTO> findAll() {
        return repository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public Users findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuário com id \"" + id + "\" não encontrado!")
        );
    }

    @Transactional
    public UserDTO updateUser(UserDTO userDTO) {
        Users users = findById(userDTO.id());
        users.setName(userDTO.name());
        users.setEmail(userDTO.email());
        users.set_active(userDTO.is_active());
        users.setRole((userDTO.role()).equals("admin") ? Roles.ADMIN : Roles.USER);
        Users userSaved = repository.save(users);
        return new UserDTO(userSaved);
    }

    @Transactional
    public void deleteById (Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Erro, usuário \"" + id + "\" não encontrado!");
        }
        repository.deleteById(id);
    }
}
