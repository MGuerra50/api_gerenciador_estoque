package com.inventory.manager.domain.stock;

import com.inventory.manager.domain.location.Location;
import com.inventory.manager.domain.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Stock")
@Table(name = "im_stock")
@Getter
@Setter
@NoArgsConstructor
public class Stock {

    @EmbeddedId
    private StockId id = new StockId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("locationId")
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(nullable = false)
    private int amount;
}
