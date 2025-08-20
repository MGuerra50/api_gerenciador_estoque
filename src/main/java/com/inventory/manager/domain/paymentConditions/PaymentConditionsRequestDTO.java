package com.inventory.manager.domain.paymentConditions;

import java.time.LocalDateTime;

public record PaymentConditionsRequestDTO(
    String code,
    String description,
    LocalDateTime daysToMaturity
) {
}
