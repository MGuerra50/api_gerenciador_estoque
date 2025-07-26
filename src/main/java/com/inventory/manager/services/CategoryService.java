package com.inventory.manager.services;

import com.inventory.manager.domain.category.Category;
import com.inventory.manager.domain.category.CategoryDTO;
import com.inventory.manager.domain.category.CategoryRepository;
import com.inventory.manager.domain.category.CategoryRequestDTO;
import com.inventory.manager.exception.DuplicateCategoryException;
import com.inventory.manager.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Categoria id \"" + id + "\" não encontrada.")
        );
    }

    private Category findByName(String name){
        return categoryRepository.findByName(name);
    }

    public CategoryDTO createCategory (CategoryRequestDTO dto){
        Category check = findByName(dto.name());
        if(check != null){
            throw new DuplicateCategoryException("Erro, categoria duplicada");
        }
        Category category = new Category();
        category.setName(dto.name());
        category.setIsActive(true);

        Category savedCategory = categoryRepository.save(category);
        return new CategoryDTO(savedCategory);
    }

    @Transactional
    public CategoryDTO updateCategory (Long id, CategoryDTO categoryDTO){
        Category category = findById(id);
        category.setName(categoryDTO.name());
        category.setIsActive(categoryDTO.isActive());
        Category savedCategory = categoryRepository.save(category);

        return new CategoryDTO(savedCategory);
    }

    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria \"" + id + "\" não encontrada!");
        }
        categoryRepository.deleteById(id);
    }
}
