package com.inventory.manager.domain.product;

import com.inventory.manager.domain.category.Category;
import com.inventory.manager.domain.supplier.Supplier;
import com.inventory.manager.domain.users.Users;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity(name = "Product")
@Table(name = "im_products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@SQLDelete(sql = " UPDATE im_products " +
        " SET is_active = false " +
        " WHERE id = ? ")
@SQLRestriction(" is_active = true ")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_id")
    private Category category;
    private String sku;
    private String name;
    private String description;
    private Float cost_price;
    private Float selling_price;
    private Boolean is_active;
    private String brand;
    private String model;
    private String version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_supplier")
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_second_supplier")
    private Supplier secondSupplier;
    private String color;
    private String dimensions;

    @Column(name = "gross_weight")
    private Long grossWeight;

    @Column(name = "net_weight")
    private Long netWeight;

    @Column(name = "product_datails")
    private String productDetails;

    @Column(name = "unit_of_measurement")
    private String unitOfMeasurement;

    @Column(name = "unit_of_measurement_description")
    private String unitOfMeasurementDescription;

    @Column(name = "unit_price_of_last_purchase")
    private Long unitPriceOfLastPurchase;

    @Column(name = "average_unit_price")
    private Long averageUnitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private Users createdByUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private Users updatedByUser;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
