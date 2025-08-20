package com.inventory.manager.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.manager.domain.deliveryConditions.DeliveryConditionsRepository;
import com.inventory.manager.domain.deliveryConditions.DeliveryConditions;
import com.inventory.manager.domain.deliveryConditions.DeliveryConditionsDTO;
import com.inventory.manager.domain.deliveryConditions.DeliveryConditionsRequestDTO;
import com.inventory.manager.domain.users.Users;

@Service
public class DeliveryConditionsService {

    @Autowired
    private DeliveryConditionsRepository repository;
    @Autowired
    private UsersServices usersServices;

    public List<DeliveryConditionsDTO> findAll() {
        return repository.findAll().stream().map(DeliveryConditionsDTO::new).toList();
    }

    public DeliveryConditions findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Condição de entrega com id \"" + id + "\" não encontrada.")
            );
    }

    public DeliveryConditionsDTO createDeliveryConditions(DeliveryConditionsRequestDTO dto) {
        Users users = usersServices.findById(dto.user());
        DeliveryConditions deliveryConditions = new DeliveryConditions();
        deliveryConditions.setCode(dto.code());
        deliveryConditions.setDescription(dto.description());
        deliveryConditions.setIsActive(true);
        deliveryConditions.setCreatedByUser(users);

        DeliveryConditions saved = repository.save(deliveryConditions);
        return new DeliveryConditionsDTO(saved);
    }

    public DeliveryConditionsDTO updateDeliveryConditions(Long id, DeliveryConditionsRequestDTO dto) {
        Users user = usersServices.findById(dto.user());
        
        DeliveryConditions deliveryConditions = findById(id);
        deliveryConditions.setCode(dto.code());
        deliveryConditions.setDescription(dto.description());
        deliveryConditions.setUpdatedByUser(user);
        deliveryConditions.setUpdatedAt(LocalDateTime.now());

        DeliveryConditions updated = repository.save(deliveryConditions);
        return new DeliveryConditionsDTO(updated);
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Condição de entrega com id \"" + id + "\" não encontrada.");
        }
        repository.deleteById(id);
    }
}
