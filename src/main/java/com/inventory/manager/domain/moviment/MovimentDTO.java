package com.inventory.manager.domain.moviment;

import java.util.Date;

public record MovimentDTO(
        Long id,
        Long userId,
        Long productId,
        Long locationId,
        Long supplierId,
        Type type,
        int numberOfItemsTransaction,
        Date date_time,
        Boolean isActive) {

    public MovimentDTO (Moviment moviment){
        this(
                moviment.getId(),
                moviment.getUsers().getId(),
                moviment.getProduct().getId(),
                moviment.getLocation().getId(),
                moviment.getSupplier().getId(),
                moviment.getType(),
                moviment.getNumberOfItemsInTransaction(),
                moviment.getDate_time(),
                moviment.getIsActive()
        );
    }
}
