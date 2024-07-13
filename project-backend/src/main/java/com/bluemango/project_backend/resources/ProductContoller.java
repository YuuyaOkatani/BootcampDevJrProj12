package com.bluemango.project_backend.resources;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bluemango.project_backend.models.Category;
import com.bluemango.project_backend.models.Product;
import com.bluemango.project_backend.repositories.CategoryRepository;
import com.bluemango.project_backend.repositories.ProductRepository;
import com.bluemango.project_backend.services.ProductService;

import jakarta.annotation.PostConstruct;

@RestController
@CrossOrigin
public class ProductContoller {

    // private List<Product> products = new ArrayList<>();

    // Ela inicializa a lista de produtos

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductService productService; 

    @PostMapping("products")
    // criar um corpo JSON para postar
    public ResponseEntity<Product> save(@RequestBody Product product) {

        product = productRepository.save(product);

        // Location -> URI(Endereço)
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/${id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(location).body(product);

    }

    @GetMapping("products")
    public List<Product> getProducts() {

        return productRepository.findAll();
    }

    @GetMapping("products/{id}")
    public ResponseEntity<Product> getProducts(@PathVariable int id) {

        Product product = productService.getById(id);   
        return ResponseEntity.ok(product);

    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<Void> removeProducts(@PathVariable int id) {

        Product prod = productRepository.findById(id)

                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product not found"));

        productRepository.delete(prod);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("products/{id}")
    public ResponseEntity<Void> updateProducts(@PathVariable int id, @RequestBody Product productUpdate) {

        Product product = productRepository.findById(id)

                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product not found"));

        if(productUpdate.getCategory() == null){
            new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Category can not be empty");

        }

        Category category = categoryRepository.findById(productUpdate.getCategory().getId())

                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category not found"));

        product.setName(productUpdate.getName());
        product.setDesc(productUpdate.getDesc());
        product.setPrice(productUpdate.getPrice());
        product.setCategory(category);
        product.setPromotion(productUpdate.isPromotion());
        product.setNovo(productUpdate.isNovo());

        productRepository.save(product); 

        return ResponseEntity.ok().build();

    }

}
