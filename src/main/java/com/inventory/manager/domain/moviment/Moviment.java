package com.inventory.manager.domain.moviment;

import com.inventory.manager.domain.location.Location;
import com.inventory.manager.domain.product.Product;
import com.inventory.manager.domain.supplier.Supplier;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "Moviment")
@Table(name = "im_moviments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Moviment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locations_id")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "suppliers_id")
    private Supplier supplier;

    private Type type;
    private int numberOfItemsInTransaction;
    private Date date_time;

}
