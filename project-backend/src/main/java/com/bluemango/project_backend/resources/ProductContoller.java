package com.bluemango.project_backend.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bluemango.project_backend.dto.ProductRequest;
import com.bluemango.project_backend.dto.ProductResponse;
import com.bluemango.project_backend.models.Category;
import com.bluemango.project_backend.models.Product;
import com.bluemango.project_backend.repositories.CategoryRepository;
import com.bluemango.project_backend.repositories.ProductRepository;
import com.bluemango.project_backend.services.ProductService;

@RestController
@CrossOrigin
@RequestMapping("products")
public class ProductContoller {

    // private List<Product> products = new ArrayList<>();

    // Ela inicializa a lista de produtos

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductService productService; 

    @PostMapping
    // criar um corpo JSON para postar
    public ResponseEntity<ProductResponse> save(@Validated @RequestBody ProductRequest productRequest) {

        ProductResponse product = productService.save(productRequest);

        // Location -> URI(Endereço)
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/${id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(location).body(product);

    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {

        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProducts(@PathVariable Long id) {

        Product product = productService.getById(id);   
        return ResponseEntity.ok(product);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeProducts(@PathVariable Long id) {

        Product prod = productRepository.findById(id)

                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product not found"));

        productRepository.delete(prod);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateProducts(@PathVariable Long id, @RequestBody Product productUpdate) {

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
