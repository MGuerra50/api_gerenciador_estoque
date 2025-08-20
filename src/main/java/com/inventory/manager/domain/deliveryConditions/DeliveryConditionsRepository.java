package com.inventory.manager.domain.deliveryConditions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryConditionsRepository extends JpaRepository<DeliveryConditions, Long> {
}
