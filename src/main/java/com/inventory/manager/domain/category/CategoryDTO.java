package com.inventory.manager.domain.category;

public record CategoryDTO(Long id, String name) {

    public CategoryDTO (Category category){
        this(category.getId(), category.getName());
    }

}
