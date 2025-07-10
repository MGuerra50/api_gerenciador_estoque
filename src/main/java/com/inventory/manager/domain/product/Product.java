package com.inventory.manager.domain.product;

import com.inventory.manager.domain.category.Category;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity(name = "Product")
@Table(name = "im_products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@SQLDelete(sql = "UPDATE im_products" +
        "SET is_active = false" +
        "WHERE id = ?")
@SQLRestriction("is_active = true")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categories_id")
    private Category category;
    private String sku;
    private String name;
    private String description;
    private Float cost_price;
    private Float selling_price;
    private Boolean is_active;
}
