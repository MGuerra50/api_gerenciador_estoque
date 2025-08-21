package com.inventory.manager.domain.moviment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MovimentUpdateDTO(@NotNull Type type,
                                @NotNull @Positive int numberOfItemsTransaction) {
}
