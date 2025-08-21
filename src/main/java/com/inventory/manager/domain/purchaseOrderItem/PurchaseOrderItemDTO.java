package com.inventory.manager.domain.purchaseOrderItem;

import java.time.LocalDateTime;

public record PurchaseOrderItemDTO(
    Long id,
    Long productId,
    Long purchaseOrderId,
    Long quantity,
    Long unitPrice,
    Long lineTotal,
    Long createdByUserId,
    Long updatedByUserId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public PurchaseOrderItemDTO(PurchaseOrderItem item) {
        this(
            item.getId(),
            item.getProduct() != null ? item.getProduct().getId() : null,
            item.getPurchaseOrder() != null ? item.getPurchaseOrder().getId() : null,
            item.getQuantity(),
            item.getUnitPrice(),
            item.getLineTotal(),
            item.getCreatedByUser() != null ? item.getCreatedByUser().getId() : null,
            item.getUpdatedByUser() != null ? item.getUpdatedByUser().getId() : null,
            item.getCreatedAt(),
            item.getUpdatedAt()
        );
    }
}


