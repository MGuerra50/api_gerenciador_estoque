package com.inventory.manager.services;

import com.inventory.manager.domain.supplier.Supplier;
import com.inventory.manager.domain.supplier.SupplierDTO;
import com.inventory.manager.domain.supplier.SupplierRequestDTO;
import com.inventory.manager.domain.supplier.SupplierRespository;
import com.inventory.manager.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService {

    @Autowired
    SupplierRespository supplierRespository;

    public Supplier findById (Long id){
        return supplierRespository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Supplier id \"" + id + "\" não encontrado.")
        );
    }

    public List<SupplierDTO> findAll (){
        return supplierRespository.findAll().stream().map(SupplierDTO::new).collect(Collectors.toList());
    }

    public SupplierDTO createSupplier (SupplierRequestDTO supplierRequestDTO){
        Supplier supplier = new Supplier();
        supplier.setFantasyName(supplierRequestDTO.fantasyName());
        supplier.setCompanyName(supplierRequestDTO.companyName());
        supplier.setCnpj(supplierRequestDTO.cnpj());
        supplier.setIsActive(true);
        supplier.setEmail(supplierRequestDTO.email());

        Supplier savedSupplier = supplierRespository.save(supplier);
        return new SupplierDTO(savedSupplier);
    }

    @Transactional
    public SupplierDTO updateSupplier (Long id, SupplierDTO supplierDTO){
        Supplier supplier = findById(id);
        if (supplier == null) throw new ResourceNotFoundException("Supplier id \"" + id + "\" não encontrado.");

        supplier.setFantasyName(supplierDTO.fantasyName());
        supplier.setCompanyName(supplierDTO.companyName());
        supplier.setCnpj(supplierDTO.cnpj());
        supplier.setIsActive(supplierDTO.isActive());
        supplier.setEmail(supplierDTO.email());

        Supplier savedSupplier = supplierRespository.save(supplier);
        return new SupplierDTO(savedSupplier);
    }

    public void deleteById (Long id){
        if(!supplierRespository.existsById(id))
            throw new ResourceNotFoundException("Suppliers id \"" + id + "\" não encontrado.");

        supplierRespository.deleteById(id);
    }
}
