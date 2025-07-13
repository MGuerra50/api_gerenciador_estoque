package com.inventory.manager.domain.product;

import com.inventory.manager.domain.category.CategoryDTO;

public record ProductDTOResponse(
        Long id,
        CategoryDTO category,
        String sku,
        String name,
        String description,
        Float cost_price,
        Float selling_price
) {
    public ProductDTOResponse(Product product) {
        this(
                product.getId(),
                new CategoryDTO(product.getCategory()),
                product.getSku(),
                product.getName(),
                product.getDescription(),
                product.getCost_price(),
                product.getSelling_price()
        );
    }
}
