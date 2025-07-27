package com.inventory.manager.controller.Position;

import com.inventory.manager.domain.position.PositionDTO;
import com.inventory.manager.domain.position.PositionRequestDTO;
import com.inventory.manager.services.PositionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/position")
public class PositionController {

    @Autowired
    PositionService positionService;

    @GetMapping("/{id}")
    public ResponseEntity<PositionDTO> findById (@Valid @PathVariable Long id){
        return ResponseEntity.ok(new PositionDTO(positionService.findById(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PositionDTO>> findAll (){
        return ResponseEntity.ok(positionService.findAll());
    }

    @PostMapping()
    public ResponseEntity<PositionDTO> createPosition (@Valid @RequestBody PositionRequestDTO positionRequestDTO){
        PositionDTO positionDTO = positionService.createPosition(positionRequestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(positionDTO.id()).toUri();
        return ResponseEntity.created(location).body(positionDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PositionDTO> updatePosition (@Valid @PathVariable Long id,
                                                       @Valid @RequestBody PositionDTO positionDTO){
        return ResponseEntity.ok(positionService.updatePosition(id, positionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById (@Valid @PathVariable Long id){
        positionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
