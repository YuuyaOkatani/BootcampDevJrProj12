package com.bluemango.project_backend.resources;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bluemango.project_backend.dto.ProductRequest;
import com.bluemango.project_backend.dto.ProductResponse;
import com.bluemango.project_backend.models.Product;
import com.bluemango.project_backend.services.ProductService;



@RestController
@CrossOrigin
@RequestMapping("products")
public class ProductContoller {

    // private List<Product> products = new ArrayList<>();

    @Autowired
    private ProductService productService;

    @PostMapping
    // criar um corpo JSON para postar
    public ResponseEntity<ProductResponse> save(@Validated @RequestBody ProductRequest productRequest) {

        ProductResponse product = productService.save(productRequest.toEntity() ); 

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

        productService.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateProducts(@Validated @PathVariable Long id, @RequestBody ProductRequest productUpdate) {

        productService.update(id, productUpdate);

        return ResponseEntity.ok().build();

    }

}
