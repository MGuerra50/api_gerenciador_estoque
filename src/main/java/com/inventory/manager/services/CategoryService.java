package com.inventory.manager.services;

import com.inventory.manager.domain.category.Category;
import com.inventory.manager.domain.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findById (Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            Category categoryFound = new Category();
            categoryFound.setId(category.get().getId());
            categoryFound.setName(category.get().getName());
            return categoryFound;
        }
        return null;
    }
}
