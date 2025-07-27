package com.inventory.manager.domain.location;

public record LocationDTO(Long id, Long warehouseId, String locationCode, Boolean isActive) {

    public LocationDTO (Location location){
        this(location.getId(), location.getWarehouse().getId(), location.getLocationCode(), location.getIsActive() );
    }
}
