package com.inventory.manager.domain.supplier;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Supplier")
@Table(name = "im_suppliers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fantasyName;
    private String companyName;
    private String cnpj;
}
