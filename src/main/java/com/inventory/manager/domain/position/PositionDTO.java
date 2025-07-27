package com.inventory.manager.domain.position;

public record PositionDTO(Long id, String name, Boolean isActive) {
    public PositionDTO (Position position){
        this(position.getId(), position.getName(), position.getIsActive());
    }
}
