package com.bluemango.project_backend.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bluemango.project_backend.dto.ProductRequest;
import com.bluemango.project_backend.dto.ProductResponse;
import com.bluemango.project_backend.models.Category;
import com.bluemango.project_backend.models.Product;
import com.bluemango.project_backend.repositories.ProductRepository;

@Service
public class ProductService {
    

    @Autowired
    private ProductRepository productRepository; 

    @Autowired
    private CategoryService categoryService; 

    
    public Product getById(Long id){
        Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        return product;

    }


    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public ProductResponse save(Product productRequest){
        Product product =  productRepository.save(productRequest);
        return product.toDTO();
    }

    public void deleteById(Long id){
        Product product = getById(id); 
        productRepository.delete(product);
    }

    public void update(Long id, ProductRequest productUpdate){
        Product product = getById(id);

        if(product.getCategory() == null){
            new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Category can not be empty");

        }

        Category category = categoryService.getById(productUpdate.getCategory().getId());
        product.setName(productUpdate.getName());
        product.setPrice(productUpdate.getPrice());
        product.setDesc(productUpdate.getDesc());
        product.setCategory(category.toDTO());
        product.setPromotion(productUpdate.isPromotion());
        product.setNovo(productUpdate.isNovo()); 
        productRepository.save(product);
    }
}
