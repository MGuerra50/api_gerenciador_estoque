package com.inventory.manager.services;

import com.inventory.manager.domain.warehouse.Warehouse;
import com.inventory.manager.domain.warehouse.WarehouseDTO;
import com.inventory.manager.domain.warehouse.WarehouseRepository;
import com.inventory.manager.domain.warehouse.WarehouseRequestDTO;
import com.inventory.manager.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public Warehouse findById (Long id){
        return warehouseRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Não foi encontrado um armazém com o id \"" + id + "\".")
        );
    }

    public List<WarehouseDTO> findAll (){
        return warehouseRepository.findAll().stream().map(WarehouseDTO::new).collect(Collectors.toList());
    }

    public WarehouseDTO createWarehouse (WarehouseRequestDTO warehouseRequestDTO){
        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouse.getName());
        warehouse.setEndereco(warehouse.getEndereco());
        warehouse.setIsActive(true);

        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        return new WarehouseDTO(savedWarehouse);
    }

    @Transactional
    public WarehouseDTO updateWarehouse (Long id, WarehouseDTO warehouseDTO){
        Warehouse warehouse = findById(id);
        warehouse.setName(warehouseDTO.name());
        warehouse.setEndereco(warehouseDTO.endereco());
        warehouse.setIsActive(warehouseDTO.isActive());

        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        return new WarehouseDTO(savedWarehouse);
    }

    public void deleteById (Long id){
        if(!warehouseRepository.existsById(id)){
            throw new ResourceNotFoundException("Warehouse id \"" + id + "\" não encontrado.");
        }
        warehouseRepository.deleteById(id);
    }

}
