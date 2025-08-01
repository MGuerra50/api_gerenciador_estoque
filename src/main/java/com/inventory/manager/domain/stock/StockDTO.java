package com.inventory.manager.domain.stock;

public record StockDTO(Long productId, Long locationId, int amount, Boolean isActive) {
    public StockDTO(Stock stock) {
        this(
                stock.getProduct().getId(),
                stock.getLocation().getId(),
                stock.getAmount(),
                stock.getIsActive()
        );
    }
}
