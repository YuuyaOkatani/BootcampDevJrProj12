package com.bluemango.project_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bluemango.project_backend.dto.CategoryRequest;
import com.bluemango.project_backend.models.Category;
import com.bluemango.project_backend.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository; 

    
    public Category getById(int id){
        Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        return category;

    }

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Category save(CategoryRequest categoryRequest){
        return categoryRepository.save(categoryRequest.toEntity());
    }

    public void deleteById(int id){
        categoryRepository.deleteById(id);
    }

    public void update(int id, Category category){
        Category existingCategory = getById(id);
        existingCategory.setName(category.getName()); 
        categoryRepository.save(existingCategory);
    }
    
}
