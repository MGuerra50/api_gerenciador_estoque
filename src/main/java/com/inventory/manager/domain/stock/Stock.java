package com.inventory.manager.domain.stock;

import com.inventory.manager.domain.location.Location;
import com.inventory.manager.domain.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity(name = "Stock")
@Table(name = "im_stock")
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = " UPDATE im_stock" +
        " SET isactive = false" +
        " WHERE id = ? ")
@SQLRestriction(" isactive = false ")
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
    private Boolean isActive;
}
