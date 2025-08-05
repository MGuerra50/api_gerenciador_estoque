package com.inventory.manager.controller.Supplier;

import com.inventory.manager.domain.supplier.SupplierDTO;
import com.inventory.manager.domain.supplier.SupplierRequestDTO;
import com.inventory.manager.services.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> findById (@Valid @PathVariable Long id){
        return ResponseEntity.ok(new SupplierDTO(supplierService.findById(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SupplierDTO>> findAll (){
        return ResponseEntity.ok(supplierService.findAll());
    }

    @PostMapping()
    public ResponseEntity<SupplierDTO> createSupplier (@Valid @RequestBody SupplierRequestDTO requestDTO){
        SupplierDTO supplierDTO = supplierService.createSupplier(requestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(supplierDTO.id()).toUri();
        return ResponseEntity.created(location).body(supplierDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> updateSupplier (@Valid @PathVariable Long id,
                                                       @Valid @RequestBody SupplierDTO supplierDTO){
        return ResponseEntity.ok(supplierService.updateSupplier(id, supplierDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById (@Valid @PathVariable Long id){
        supplierService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
