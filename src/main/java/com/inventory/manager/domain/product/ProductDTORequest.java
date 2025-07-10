package com.inventory.manager.domain.product;

import com.inventory.manager.domain.category.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductDTORequest(
        Category category,
        @NotBlank String sku,
        @NotBlank String name,
        String description,
        @NotBlank @Positive Float cost_price,
        @NotBlank @Positive Float selling_price) {

    public ProductDTORequest(Product product) {
        this(
                product.getCategory(),
                product.getSku(),
                product.getName(),
                product.getDescription(),
                product.getCost_price(),
                product.getSelling_price()

        );
    }
}
