package com.inventory.manager.domain.purchaseOrder;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record PurchaseOrderRequestDTO(
	@NotNull LocalDateTime dateOfIssu,
	@NotNull String orderStatus,
	@NotNull Long project,
	@NotNull Long company,
	@NotNull Long paymentConditions,
	@NotNull Long deliveryConditions,
	@NotNull Long totalIpi,
	@NotNull Long totalIcms,
	Long subtotal,
	Long discountValue,
	String discountDescription,
	Long totalNetValue,
	String generalObservations,
	String businessObservations,
	String supplierBusiness,
	@NotNull Long userId
) { }
