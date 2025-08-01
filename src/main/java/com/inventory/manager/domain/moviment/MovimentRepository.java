package com.inventory.manager.domain.moviment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentRepository extends JpaRepository<Moviment, Long> {
}
