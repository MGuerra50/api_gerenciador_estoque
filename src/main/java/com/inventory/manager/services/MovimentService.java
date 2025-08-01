package com.inventory.manager.services;

import com.inventory.manager.domain.location.Location;
import com.inventory.manager.domain.moviment.*;
import com.inventory.manager.domain.product.Product;
import com.inventory.manager.domain.supplier.Supplier;
import com.inventory.manager.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimentService {

    @Autowired
    MovimentRepository movimentRepository;
    @Autowired
    ProductService productService;
    @Autowired
    LocationService locationService;
    @Autowired
    SupplierService supplierService;

    public Moviment findById (Long id){
        return movimentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(
                "Moviment id \"" + id + "\" não encontrado!")
        );
    }

    public List<MovimentDTO> findAll(){
        return movimentRepository.findAll().stream().map(MovimentDTO::new).collect(Collectors.toList());
    }

    public MovimentDTO createMoviment (MovimentRequestDTO movimentRequestDTO){
        Product product = productService.findById(movimentRequestDTO.productId());
        Location location = locationService.findById(movimentRequestDTO.locationId());
        Supplier supplier = supplierService.findById(movimentRequestDTO.supplierId());

        Moviment moviment = new Moviment();
        moviment.setProduct(product);
        moviment.setLocation(location);
        moviment.setSupplier(supplier);
        moviment.setType(movimentRequestDTO.type());
        moviment.setNumberOfItemsInTransaction(movimentRequestDTO.numberOfItemsTransaction());
        moviment.setDate_time(new Date());
        moviment.setIsActive(true);

        Moviment savedMoviment = movimentRepository.save(moviment);
        return new MovimentDTO(savedMoviment);
    }

    @Transactional
    public MovimentDTO updateMoviment (Long id, MovimentUpdateDTO dto){
        Moviment moviment = findById(id);
        moviment.setType(dto.type());
        moviment.setNumberOfItemsInTransaction(dto.numberOfItemsTransaction());
        moviment.setIsActive(dto.isActive());

        Moviment savedMoviment = movimentRepository.save(moviment);
        return new MovimentDTO(savedMoviment);
    }

    public void deleteById (Long id){
        if(!movimentRepository.existsById(id)){
            throw new ResourceNotFoundException("Moviment id \"" + id + "\" não encontrado!");
        }
        movimentRepository.deleteById(id);
    }
}
