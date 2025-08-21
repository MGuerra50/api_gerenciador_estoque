package com.inventory.manager.controller.PaymentConditions;

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

import com.inventory.manager.domain.paymentConditions.PaymentConditionsDTO;
import com.inventory.manager.domain.paymentConditions.PaymentConditionsRequestDTO;
import com.inventory.manager.services.PaymentConditionsService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/paymentConditions")
public class PaymentConditionsController {
 
    @Autowired
    private PaymentConditionsService paymentConditionsService;

    @GetMapping
    public ResponseEntity<List<PaymentConditionsDTO>> findAll() {
        return ResponseEntity.ok(paymentConditionsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentConditionsDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new PaymentConditionsDTO(paymentConditionsService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<PaymentConditionsDTO> createPaymentConditions(@RequestBody PaymentConditionsRequestDTO dto) {
        PaymentConditionsDTO paymentConditions = paymentConditionsService.createPaymentConditions(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(paymentConditions.id())
            .toUri();
        return ResponseEntity.created(location).body(paymentConditions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentConditionsDTO> updatePaymentConditions(
        @PathVariable Long id, 
        @RequestBody PaymentConditionsRequestDTO dto
    ) {
        return ResponseEntity.ok(paymentConditionsService.updatePaymentConditions(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        paymentConditionsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
