package com.inventory.manager.controller.Warehouse;

import com.inventory.manager.domain.warehouse.WarehouseDTO;
import com.inventory.manager.domain.warehouse.WarehouseRequestDTO;
import com.inventory.manager.services.WarehouseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/warehouse")
@SecurityRequirement(name = "bearer-key")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseDTO> findById(@Valid @PathVariable Long id){
        return ResponseEntity.ok(new WarehouseDTO(warehouseService.findById(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<WarehouseDTO>> findAll (){
        return ResponseEntity.ok(warehouseService.findAll());
    }

    @PostMapping()
    public ResponseEntity<WarehouseDTO> createWarehouse (@Valid @RequestBody WarehouseRequestDTO warehouseRequestDTO){
        WarehouseDTO warehouseDTO = warehouseService.createWarehouse(warehouseRequestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(warehouseDTO.id()).toUri();
        return ResponseEntity.created(location).body(warehouseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseDTO> updateWarehouse (@Valid @PathVariable Long id,
                                                         @Valid @RequestBody WarehouseDTO dto){
        return ResponseEntity.ok(warehouseService.updateWarehouse(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse (@Valid @PathVariable Long id){
        warehouseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
