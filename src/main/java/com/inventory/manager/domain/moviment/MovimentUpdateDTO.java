package com.inventory.manager.domain.moviment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MovimentUpdateDTO(@NotBlank Type type,
                                @NotNull @Positive int numberOfItemsTransaction,
                                @NotNull Boolean isActive) {
}
