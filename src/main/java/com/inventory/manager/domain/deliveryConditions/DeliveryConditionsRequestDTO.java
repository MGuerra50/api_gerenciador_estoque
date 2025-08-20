package com.inventory.manager.domain.deliveryConditions;

import jakarta.validation.constraints.NotNull;

public record DeliveryConditionsRequestDTO(
    @NotNull String code, 
    @NotNull String description, 
    @NotNull Long user) {
}


