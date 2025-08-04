package com.inventory.manager.domain.warehouse;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity(name = "Warehouse")
@Table(name = "im_warehouses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@SQLDelete(sql = " UPDATE im_warehouses " +
        " SET isactive = false" +
        " WHERE id = ? ")
@SQLRestriction(" isactive = true ")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String endereco;
    @Column(name = " isactive ")
    private Boolean isActive;
}
