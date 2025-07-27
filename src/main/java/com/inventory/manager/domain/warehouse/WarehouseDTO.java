package com.inventory.manager.domain.warehouse;

public record WarehouseDTO(Long id, String name, String endereco, Boolean isActive) {

    public WarehouseDTO(Warehouse warehouse) {
        this(warehouse.getId(), warehouse.getName(), warehouse.getEndereco(), warehouse.getIsActive());
    }
}
