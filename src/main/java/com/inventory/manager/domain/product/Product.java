package com.inventory.manager.domain.product;

import com.inventory.manager.domain.category.Category;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Product")
@Table(name = "im_products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
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
}
