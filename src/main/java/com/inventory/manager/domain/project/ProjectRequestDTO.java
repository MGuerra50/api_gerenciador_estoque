package com.inventory.manager.domain.project;

import java.time.LocalDateTime;

public record ProjectRequestDTO(
    String code,
    String name,
    String description,
    LocalDateTime startDate,
    LocalDateTime endDate,
    Long budget,
    String status,
    Long userId
) {
}
