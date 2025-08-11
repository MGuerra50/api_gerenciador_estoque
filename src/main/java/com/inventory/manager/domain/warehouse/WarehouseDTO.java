package com.inventory.manager.domain.warehouse;

public record WarehouseDTO(Long id, String name, Long address, Boolean isActive) {

    public WarehouseDTO(Warehouse warehouse) {
        this(warehouse.getId(), warehouse.getName(), warehouse.getAddress().getId(), warehouse.getIsActive());
    }
}
