package com.inventory.manager.domain.stock;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class StockId implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "location_id")
    private Long locationId;

    public StockId (Long productId, Long locationId){
        this.productId = productId;
        this.locationId = locationId;
    }
}
