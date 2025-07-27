package com.inventory.manager.domain.supplier;

public record SupplierDTO(Long id, String fantasyName, String companyName, String cnpj, Boolean isActive) {
    public SupplierDTO (Supplier supplier){
        this(supplier.getId(), supplier.getFantasyName(), supplier.getCompanyName(), supplier.getCnpj(), supplier.getIsActive());
    }
}
