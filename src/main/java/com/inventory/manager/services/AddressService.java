package com.inventory.manager.services;

import com.inventory.manager.domain.address.Address;
import com.inventory.manager.domain.address.AddressDTO;
import com.inventory.manager.domain.address.AddressRepository;
import com.inventory.manager.domain.address.AddressRequestDTO;
import com.inventory.manager.domain.users.Users;
import com.inventory.manager.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UsersServices usersServices;

    public Address findById(Long id){
        return addressRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Não existe um endereço com o ID informado!")
        );
    }

    public List<AddressDTO> findAll (){
        return addressRepository.findAll().stream().map(AddressDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public AddressDTO createAddress (Long userId, AddressRequestDTO addressRequestDTO){
        Users user = usersServices.findById(userId);
        Address address = new Address();
        address.setStreet(addressRequestDTO.street());
        address.setNumber(addressRequestDTO.number());
        address.setComplement(addressRequestDTO.complement());
        address.setNeighborhood(addressRequestDTO.neighborhood());
        address.setCity(addressRequestDTO.city());
        address.setState(addressRequestDTO.state());
        address.setPostalCode(addressRequestDTO.postalCode());
        address.setCreatedByUser(user);

        Address savedAddress = addressRepository.save(address);
        return new AddressDTO(savedAddress);
    }

    @Transactional
    public AddressDTO updateAddress (Long userId, Long addressId, AddressRequestDTO addressRequestDTO){
        Users user = usersServices.findById(userId);
        Address address = findById(addressId);
        address.setStreet(addressRequestDTO.street());
        address.setNumber(addressRequestDTO.number());
        address.setComplement(addressRequestDTO.complement());
        address.setNeighborhood(addressRequestDTO.neighborhood());
        address.setCity(addressRequestDTO.city());
        address.setState(addressRequestDTO.state());
        address.setPostalCode(addressRequestDTO.postalCode());
        address.setUpdatedByUser(user);
        address.setUpdatedAt(LocalDateTime.now());

        Address savedAddress = addressRepository.save(address);
        return new AddressDTO(savedAddress);
    }

    public void deleteById(Long id){
        if(!addressRepository.existsById(id)){
            throw new ResourceNotFoundException("Não foi possível deletar o endereço pois o ID não foi encontrado!");
        }
        addressRepository.deleteById(id);
    }
}
