package com.inventory.manager.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductDTORequest(
        @NotNull @Positive Long categoryId,
        @NotBlank String sku,
        @NotBlank String name,
        String description,
        @NotNull @Positive Float cost_price,
        @NotNull @Positive Float selling_price,
        @NotNull Boolean isActive) {

    public ProductDTORequest(Product product) {
        this(
                product.getCategory().getId(),
                product.getSku(),
                product.getName(),
                product.getDescription(),
                product.getCost_price(),
                product.getSelling_price(),
                product.getIs_active()
        );
    }
}
