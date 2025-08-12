package com.inventory.manager.domain.deliveryConditions;

import com.inventory.manager.domain.users.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "DeliveryConditions")
@Table(name = "im_delivery_conditions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DeliveryConditions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String description;
    @Column(name = "is_active")
    private Boolean isActive;
    @JoinColumn(name = "created_by")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users createdByUser;
    @JoinColumn(name = "updated_by")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users updatedByUser;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
