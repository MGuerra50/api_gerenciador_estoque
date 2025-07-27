package com.inventory.manager.domain.supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRespository extends JpaRepository<Supplier, Long> {
}
