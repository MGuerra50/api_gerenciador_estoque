package com.inventory.manager.domain.purchaseOrder;

import com.inventory.manager.domain.company.Company;
import com.inventory.manager.domain.deliveryConditions.DeliveryConditions;
import com.inventory.manager.domain.paymentConditions.PaymentConditions;
import com.inventory.manager.domain.project.Project;
import com.inventory.manager.domain.users.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "PurchaseOrder")
@Table(name = "im_purchase_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateOfIssu;
    private String orderStatus;
    @JoinColumn(name = "id_project")
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;
    @JoinColumn(name = "id_company")
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;
    @JoinColumn(name = "id_paymant_conditions")
    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentConditions paymentConditions;
    @JoinColumn(name = "id_delivery_conditions")
    @ManyToOne(fetch = FetchType.LAZY)
    private DeliveryConditions deliveryConditions;
    private Long totalIpi;
    private Long totalIcms;
    private Long subtotal;
    private Long discountValue;
    private String discountDescription;
    private Long totalNetValue;
    private String generalObservations;
    private String businessObservations;
    private String supplierBusiness;
    @JoinColumn(name = "created_by")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users createdByUser;
    @JoinColumn(name = "updated_by")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users updatedByUser;
    @JoinColumn(name = "approvet_by")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users approvedByUser;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Column(name = "approvet_at")
    private LocalDateTime approvedAt;
}
