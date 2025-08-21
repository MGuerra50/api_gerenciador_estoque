package com.inventory.manager.domain.purchaseOrder;

import java.time.LocalDateTime;

public record PurchaseOrderDTO(
	Long id,
	LocalDateTime dateOfIssu,
	String orderStatus,
	Long projectId,
	Long companyId,
	Long paymentConditionsId,
	Long deliveryConditionsId,
	Long totalIpi,
	Long totalIcms,
	Long subtotal,
	Long discountValue,
	String discountDescription,
	Long totalNetValue,
	String generalObservations,
	String businessObservations,
	String supplierBusiness,
	Long createdByUserId,
	Long updatedByUserId,
	Long approvedByUserId,
	LocalDateTime createdAt,
	LocalDateTime updatedAt,
	LocalDateTime approvedAt
) {
	public PurchaseOrderDTO(PurchaseOrder purchaseOrder) {
		this(
			purchaseOrder.getId(),
			purchaseOrder.getDateOfIssu(),
			purchaseOrder.getOrderStatus(),
			purchaseOrder.getProject() != null ? purchaseOrder.getProject().getId() : null,
			purchaseOrder.getCompany() != null ? purchaseOrder.getCompany().getId() : null,
			purchaseOrder.getPaymentConditions() != null ? purchaseOrder.getPaymentConditions().getId() : null,
			purchaseOrder.getDeliveryConditions() != null ? purchaseOrder.getDeliveryConditions().getId() : null,
			purchaseOrder.getTotalIpi(),
			purchaseOrder.getTotalIcms(),
			purchaseOrder.getSubtotal(),
			purchaseOrder.getDiscountValue(),
			purchaseOrder.getDiscountDescription(),
			purchaseOrder.getTotalNetValue(),
			purchaseOrder.getGeneralObservations(),
			purchaseOrder.getBusinessObservations(),
			purchaseOrder.getSupplierBusiness(),
			purchaseOrder.getCreatedByUser() != null ? purchaseOrder.getCreatedByUser().getId() : null,
			purchaseOrder.getUpdatedByUser() != null ? purchaseOrder.getUpdatedByUser().getId() : null,
			purchaseOrder.getApprovedByUser() != null ? purchaseOrder.getApprovedByUser().getId() : null,
			purchaseOrder.getCreatedAt(),
			purchaseOrder.getUpdatedAt(),
			purchaseOrder.getApprovedAt()
		);
	}
}

