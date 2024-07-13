package com.bluemango.project_backend.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bluemango.project_backend.models.Product;
import com.bluemango.project_backend.repositories.ProductRepository;

@Service
public class ProductService {
    

    @Autowired
    private ProductRepository productRepository; 

    
    public Product getById(int id){
        Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        return product;

    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product save( Product product){
        return productRepository.save(product);
    }

    public void deleteById(int id){
        productRepository.deleteById(id);
    }

    public void update(int id, Product product){
        Product existingProduct = getById(id);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDesc(product.getDesc());
        existingProduct.setPromotion(product.isPromotion());
        existingProduct.setNovo(product.isNovo()); 
        save(existingProduct);
    }
}
