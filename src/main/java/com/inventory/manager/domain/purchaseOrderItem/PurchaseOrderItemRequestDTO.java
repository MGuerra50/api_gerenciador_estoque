package com.inventory.manager.domain.purchaseOrderItem;

import jakarta.validation.constraints.NotNull;

public record PurchaseOrderItemRequestDTO(
    @NotNull Long productId,
    @NotNull Long purchaseOrderId,
    Long quantity,
    Long unitPrice,
    Long lineTotal,
    @NotNull Long user
) {
}
