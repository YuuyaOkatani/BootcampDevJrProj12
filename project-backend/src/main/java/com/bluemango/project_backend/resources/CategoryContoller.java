package com.bluemango.project_backend.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bluemango.project_backend.models.Category;
import com.bluemango.project_backend.repositories.CategoryRepository;

import jakarta.annotation.PostConstruct;

@CrossOrigin
@RestController
public class CategoryContoller {

    

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("categories")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }


     @GetMapping("categories/{id}")
     public ResponseEntity<Category> getCategories(@PathVariable int id){
      Category cat = categoryRepository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
     "Category not found"));
     
      return ResponseEntity.ok(cat);
      
      }

}
