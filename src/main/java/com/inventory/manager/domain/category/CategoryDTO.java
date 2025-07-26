package com.inventory.manager.domain.category;

public record CategoryDTO(Long id, String name, Boolean isActive) {

    public CategoryDTO (Category category){
        this(category.getId(), category.getName(), category.getIsActive());
    }

}
