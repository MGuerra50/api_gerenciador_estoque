package com.inventory.manager.domain.stock;

public record StockRequestDTO(Long idProduct, Long idLocation, int amount) {
}
