package com.inventory.manager.controller.DeliveryConditions;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.inventory.manager.domain.deliveryConditions.DeliveryConditionsDTO;
import com.inventory.manager.domain.deliveryConditions.DeliveryConditionsRequestDTO;
import com.inventory.manager.services.DeliveryConditionsService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/delivery")
public class DeliveryConditionsController {
    
    @Autowired
    private DeliveryConditionsService conditionsService;

    @GetMapping
    public ResponseEntity<List<DeliveryConditionsDTO>> findAll() {
        return ResponseEntity.ok(conditionsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryConditionsDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new DeliveryConditionsDTO(conditionsService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<DeliveryConditionsDTO> createDeliveryConditions(@RequestBody DeliveryConditionsRequestDTO dto) {
        DeliveryConditionsDTO deliveryConditions = conditionsService.createDeliveryConditions(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(deliveryConditions.id())
            .toUri();
        return ResponseEntity.created(location).body(deliveryConditions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryConditionsDTO> updateDeliveryConditions(@PathVariable Long id, @RequestBody DeliveryConditionsRequestDTO dto) {
        return ResponseEntity.ok(conditionsService.updateDeliveryConditions(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeliveryConditions(@PathVariable Long id) {
        conditionsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
