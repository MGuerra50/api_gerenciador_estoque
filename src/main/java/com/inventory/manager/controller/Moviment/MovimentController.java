package com.inventory.manager.controller.Moviment;

import com.inventory.manager.domain.moviment.MovimentDTO;
import com.inventory.manager.domain.moviment.MovimentRequestDTO;
import com.inventory.manager.domain.moviment.MovimentUpdateDTO;
import com.inventory.manager.services.MovimentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/moviment")
@SecurityRequirement(name = "bearer-key")
public class MovimentController {

    @Autowired
    MovimentService movimentService;

    @GetMapping("/{id}")
    public ResponseEntity<MovimentDTO> findById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(new MovimentDTO(movimentService.findById(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovimentDTO>> findAll() {
        return ResponseEntity.ok(movimentService.findAll());
    }

    @PostMapping()
    public ResponseEntity<MovimentDTO> createMoviment(@Valid @RequestBody MovimentRequestDTO movimentRequestDTO) {
        MovimentDTO movimentDTO = movimentService.createMoviment(movimentRequestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(movimentDTO.id()).toUri();
        return ResponseEntity.created(location).body(movimentDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimentDTO> updateMoviment (@PathVariable Long id,
                                                       @Valid @RequestBody MovimentUpdateDTO dto){
        return ResponseEntity.ok(movimentService.updateMoviment(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById (@PathVariable Long id){
        movimentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
