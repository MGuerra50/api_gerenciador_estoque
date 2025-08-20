package com.inventory.manager.controller.Stock;

import com.inventory.manager.domain.stock.StockDTO;
import com.inventory.manager.domain.stock.StockRequestDTO;
import com.inventory.manager.services.StockService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/stock")
@SecurityRequirement(name = "bearer-key")
public class StockController {

    @Autowired
    StockService stockService;

    @GetMapping("/product/{productId}/location/{locationId}")
    public ResponseEntity<StockDTO> findById(@PathVariable Long productId,
                                             @PathVariable Long locationId) {
        return ResponseEntity.ok(new StockDTO(stockService.findById(productId, locationId)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<StockDTO>> findaAll (){
        return ResponseEntity.ok(stockService.findAll());
    }

    @PostMapping()
    public ResponseEntity<StockDTO> createStock (@Valid @RequestBody StockRequestDTO stockRequestDTO){
        StockDTO stockDTO = stockService.createStock(stockRequestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(
                        stockService.findById(stockDTO.productId(), stockDTO.locationId()).getId()
                ).toUri();
        return ResponseEntity.created(location).body(stockDTO);
    }

    @PutMapping("/updateStock")
    public ResponseEntity<StockDTO> updateStock (@Valid @RequestBody StockDTO stockDTO){
        return ResponseEntity.ok(stockService.updateStock(stockDTO));
    }

    @DeleteMapping("/product/{productId}/location/{locationId}")
    public ResponseEntity<Void> deleteById (@PathVariable Long productId, @PathVariable Long locationId){
        stockService.deleteById(productId, locationId);
        return ResponseEntity.noContent().build();
    }
}
