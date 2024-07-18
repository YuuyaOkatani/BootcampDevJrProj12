package com.bluemango.project_backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bluemango.project_backend.dto.ProductRequest;
import com.bluemango.project_backend.dto.ProductResponse;
import com.bluemango.project_backend.models.Category;
import com.bluemango.project_backend.models.Product;
import com.bluemango.project_backend.repositories.CategoryRepository;
import com.bluemango.project_backend.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private CategoryRepository categoryRepository;

    public Product getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        return product;

    }

    public List<ProductResponse> getAll() {
        return productRepository.findAll()
                .stream()
                .map(p -> p.toDTO())
                .collect(Collectors.toList());
    }

    public ProductResponse save(ProductRequest productRequest) {
        Product product = productRepository.save(productRequest.toEntity());
        return product.toDTO();
    }

    public void deleteById(Long id) {
        
        productRepository.deleteById(id);
    }

    public void update(Long id, ProductRequest productRequest) {

        Product existingProduct = getById(id);

        Category category = categoryRepository.findById(productRequest.getCategory().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        existingProduct.setName(productRequest.getName());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setDesc(productRequest.getDesc());
        existingProduct.setCategory(category); // returns the category
        existingProduct.setPromotion(productRequest.isPromotion());
        existingProduct.setNovo(productRequest.isNovo());
        productRepository.save(existingProduct);
    }

    public ProductResponse getDTOById(Long id) {
        // TODO Auto-generated method stub
        Product product = getById(id);
        return product.toDTO();
    }
}
