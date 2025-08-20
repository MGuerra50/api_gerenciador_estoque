package com.inventory.manager.domain.paymentConditions;

import java.time.LocalDateTime;

public record PaymentConditionsDTO(
    Long id,
    String code,
    String description,
    LocalDateTime daysToMaturity,
    Boolean isActive
) {
    public PaymentConditionsDTO(PaymentConditions paymentConditions) {
        this(
            paymentConditions.getId(),
            paymentConditions.getCode(),
            paymentConditions.getDescription(),
            paymentConditions.getDaysToMaturity(),
            paymentConditions.getIsActive()
        );
    }
}
