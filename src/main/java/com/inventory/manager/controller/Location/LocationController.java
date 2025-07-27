package com.inventory.manager.controller.Location;

import com.inventory.manager.domain.location.LocationDTO;
import com.inventory.manager.domain.location.LocationRequestDTO;
import com.inventory.manager.services.LocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> findById (@Valid @PathVariable Long id){
        return ResponseEntity.ok(new LocationDTO(locationService.findById(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<LocationDTO>> findAll (){
        return ResponseEntity.ok(locationService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<LocationDTO> createLocation (@RequestBody LocationRequestDTO locationRequestDTO){
        LocationDTO locationDTO = locationService.createLocation(locationRequestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(locationDTO.id()).toUri();
        return ResponseEntity.created(location).body(locationDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationDTO> updateById (@Valid @PathVariable Long id, @Valid @RequestBody LocationDTO dto){
        return ResponseEntity.ok(locationService.updateLocation(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById (@Valid @PathVariable Long id){
        locationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
