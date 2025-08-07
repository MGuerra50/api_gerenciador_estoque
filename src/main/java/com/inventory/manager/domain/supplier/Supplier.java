package com.inventory.manager.domain.supplier;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity(name = "Supplier")
@Table(name = "im_suppliers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@SQLDelete(sql = " UPDATE im_suppliers " +
        "SET isactive = false " +
        "WHERE id = ? ")
@SQLRestriction(" isactive = true ")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fantasyName;
    private String companyName;
    private String cnpj;
    @Column(name = "isactive")
    private Boolean isActive;
    private String email;
}
