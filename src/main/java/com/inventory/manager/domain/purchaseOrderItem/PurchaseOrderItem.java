package com.inventory.manager.domain.purchaseOrderItem;

import com.inventory.manager.domain.product.Product;
import com.inventory.manager.domain.purchaseOrder.PurchaseOrder;
import com.inventory.manager.domain.users.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "PurchaseOrderItem")
@Table(name = "im_purchase_order_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PurchaseOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "id_product")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @JoinColumn(name = "id_purchase_order")
    @ManyToOne(fetch = FetchType.LAZY)
    private PurchaseOrder purchaseOrder;
    private Long quantity;
    private Long unitPrice;
    private Long lineTotal;
    @JoinColumn(name = "created_by")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users createdByUser;
    @JoinColumn(name = "updated_by")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users updatedByUser;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
