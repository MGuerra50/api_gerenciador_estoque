package com.inventory.manager.domain.stock;

public record StockRequestDTO(Long productId, Long locationId, int amount) {
}
