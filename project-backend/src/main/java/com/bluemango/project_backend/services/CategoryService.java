package com.bluemango.project_backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bluemango.project_backend.dto.CategoryRequest;
import com.bluemango.project_backend.dto.CategoryResponse;
import com.bluemango.project_backend.models.Category;
import com.bluemango.project_backend.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponse getDTOById(Integer id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category not found"));
        return category.toDTO();
    }

    public Category getById(Integer id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category not found"));
        return category;
    }

    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll().stream().map(c -> c.toDTO()).collect(Collectors.toList()) ; // Muda de um tipo para outro...
    }

    public CategoryResponse save(CategoryRequest categoryRequest){
        Category category = categoryRepository.save(categoryRequest.toEntity());
        return category.toDTO();
    }

    public void deleteById(Integer id){
        Category category = getById(id); 
        categoryRepository.delete(category);
    }

    public void update(Integer id, CategoryRequest categoryUpdate){
        

        Category category = getById(id);
        category.setName(categoryUpdate.getName());
        categoryRepository.save(category);
    }



}
