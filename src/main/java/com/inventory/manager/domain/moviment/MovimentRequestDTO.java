package com.inventory.manager.domain.moviment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MovimentRequestDTO(@Positive @NotNull Long productId,
                                 @Positive @NotNull Long locationId,
                                 @Positive @NotNull Long supplierId,
                                 @NotBlank Type type,
                                 @Positive @NotNull int numberOfItemsTransaction) {
}
