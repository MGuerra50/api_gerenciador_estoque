package com.inventory.manager.domain.warehouse;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Warehouse")
@Table(name = "im_warehouses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String endereco;
    private Boolean isActive;
}
