package com.inventory.manager.domain.deliveryConditions;

public record DeliveryConditionsDTO(
    Long id,
    String code,
    String description,
    Boolean isActive
) {
    public DeliveryConditionsDTO(DeliveryConditions deliveryConditions) {
        this(
            deliveryConditions.getId(),
            deliveryConditions.getCode(),
            deliveryConditions.getDescription(),
            deliveryConditions.getIsActive()
        );
    }
}


