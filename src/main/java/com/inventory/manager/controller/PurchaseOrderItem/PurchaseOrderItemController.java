package com.inventory.manager.controller.PurchaseOrderItem;

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

import com.inventory.manager.domain.purchaseOrderItem.PurchaseOrderItemDTO;
import com.inventory.manager.domain.purchaseOrderItem.PurchaseOrderItemRequestDTO;
import com.inventory.manager.services.PurchaseOrderItemService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/purchaseItem")
public class PurchaseOrderItemController {
    
    @Autowired
    private PurchaseOrderItemService itemService;


    @GetMapping
    public ResponseEntity<List<PurchaseOrderItemDTO>> findAll() {
        return ResponseEntity.ok(itemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderItemDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new PurchaseOrderItemDTO(itemService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<PurchaseOrderItemDTO> createPurchaseOrderItem(@RequestBody PurchaseOrderItemRequestDTO dto) {
        PurchaseOrderItemDTO item = itemService.createPurchaseOrderItem(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(item.id())
            .toUri();
        return ResponseEntity.created(location).body(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrderItemDTO> updatePurchaseOrderItem(@PathVariable Long id, @RequestBody PurchaseOrderItemRequestDTO dto) {
        return ResponseEntity.ok(itemService.updatePurchaseOrderItem(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        itemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
