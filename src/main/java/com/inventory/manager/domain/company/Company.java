package com.inventory.manager.domain.company;

import com.inventory.manager.domain.address.Address;
import com.inventory.manager.domain.users.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Company")
@Table(name = "im_companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String fantasyName;
    private String cnpj;
    private String stateRegistration;
    private String municipalRegistration;
    private String email;
    private String phone;
    @JoinColumn(name = "id_address")
    @OneToOne(fetch = FetchType.LAZY)
    private Address address;
    @JoinColumn(name = "created_by")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users createdByUser;
    @JoinColumn(name = "updated_by")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users updatedByUser;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
