package com.inventory.manager.controller.Address;

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

import com.inventory.manager.domain.address.AddressDTO;
import com.inventory.manager.domain.address.AddressRequestDTO;
import com.inventory.manager.services.AddressService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/address")
public class AddressController {
   
    @Autowired
    private AddressService addressService;


    @GetMapping
    public ResponseEntity<List<AddressDTO>> findAll() {
        return ResponseEntity.ok(addressService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> findById (@PathVariable Long id) {
        return ResponseEntity.ok(new AddressDTO(addressService.findById(id)));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<AddressDTO> createAddress (@PathVariable Long userId, @RequestBody AddressRequestDTO dto) {
        AddressDTO address = addressService.createAddress(userId, dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("{userId}").buildAndExpand(address.id())
        .toUri();
        return ResponseEntity.created(location).body(address);
    }

    @PutMapping("/{userId}/address/{addressId}")
    public ResponseEntity<AddressDTO> atualizar(@PathVariable Long userId, @PathVariable Long addressId, @RequestBody AddressRequestDTO dto) {
        return ResponseEntity.ok(addressService.updateAddress(userId, addressId, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        addressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
