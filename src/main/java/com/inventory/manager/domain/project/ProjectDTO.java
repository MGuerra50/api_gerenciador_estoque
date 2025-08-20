package com.inventory.manager.domain.project;

import java.time.LocalDateTime;

public record ProjectDTO(
    Long id,
    String code,
    String name,
    String description,
    LocalDateTime startDate,
    LocalDateTime endDate,
    Long budget,
    String status
) {
    public ProjectDTO(Project project) {
        this(
            project.getId(),
            project.getCode(),
            project.getName(),
            project.getDescription(),
            project.getStartDate(),
            project.getEndDate(),
            project.getBudget(),
            project.getStatus()
        );
    }
}
