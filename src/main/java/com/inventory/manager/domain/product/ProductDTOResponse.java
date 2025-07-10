package com.inventory.manager.domain.product;

import com.inventory.manager.domain.category.Category;

public record ProductDTOResponse(
        Long id,
        Category category,
        String sku,
        String name,
        String description,
        Float cost_price,
        Float selling_price
) {
    public ProductDTOResponse(Product product) {
        this(
                product.getId(),
                product.getCategory(),
                product.getSku(),
                product.getName(),
                product.getDescription(),
                product.getCost_price(),
                product.getSelling_price()
        );
    }
}
