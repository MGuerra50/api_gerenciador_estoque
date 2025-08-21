package com.inventory.manager.controller.PurchaseOrder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.inventory.manager.domain.purchaseOrder.PurchaseOrderDTO;
import com.inventory.manager.domain.purchaseOrder.PurchaseOrderRequestDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.manager.services.PurchaseOrderService;

@RestController
@RequestMapping("/purchaseOrder")
public class PurchaseOrder {
    
    @Autowired
    private PurchaseOrderService orderService;


    @GetMapping
    public ResponseEntity<List<PurchaseOrderDTO>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new PurchaseOrderDTO(orderService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<PurchaseOrderDTO> createPurchaseOrder(@RequestBody PurchaseOrderRequestDTO dto) {
        PurchaseOrderDTO order = orderService.createPurchaseOrder(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(order.id())
            .toUri();
        return ResponseEntity.created(location).body(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrderDTO> updatePurchaseOrder(@PathVariable Long id, @RequestBody PurchaseOrderRequestDTO dto) {
        return ResponseEntity.ok(orderService.updatePurchaseOrder(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
