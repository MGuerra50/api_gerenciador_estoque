package com.inventory.manager.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductDTORequestUpdate(
        @NotNull @Positive Long categoryId,
        @NotBlank String sku,
        @NotBlank String name,
        String description,
        @NotNull @Positive Float cost_price,
        @NotNull @Positive Float selling_price,
        String brand,
        String model,
        String version,
        Long idSupplier,
        Long idSecondSupplier,
        String color,
        String dimensions,
        Long grossWeight,
        Long netWeight,
        String productDetails,
        String unitOfMeasurement,
        String unitOfMeasurementDescription,
        Long averageUnitPrice,
        Long updatedBy
        ) {

    public ProductDTORequestUpdate(Product product) {
        this(
                product.getCategory().getId(),
                product.getSku(),
                product.getName(),
                product.getDescription(),
                product.getCost_price(),
                product.getSelling_price(),
                product.getBrand(),
                product.getModel(),
                product.getVersion(),
                product.getSupplier().getId(),
                product.getSecondSupplier().getId(),
                product.getColor(),
                product.getDimensions(),
                product.getGrossWeight(),
                product.getNetWeight(),
                product.getProductDetails(),
                product.getUnitOfMeasurement(),
                product.getUnitOfMeasurementDescription(),
                product.getAverageUnitPrice(),
                product.getUpdatedByUser().getId()
        );
    }
}
