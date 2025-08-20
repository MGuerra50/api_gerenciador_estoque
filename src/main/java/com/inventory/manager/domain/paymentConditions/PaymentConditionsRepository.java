package com.inventory.manager.domain.paymentConditions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentConditionsRepository extends JpaRepository<PaymentConditions, Long> {
}
