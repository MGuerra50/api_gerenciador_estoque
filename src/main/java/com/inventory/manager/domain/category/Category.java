package com.inventory.manager.domain.category;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Category")
@Table(name = "im_categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
