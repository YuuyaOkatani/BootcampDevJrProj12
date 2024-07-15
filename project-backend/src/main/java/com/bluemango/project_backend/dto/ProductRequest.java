package com.bluemango.project_backend.dto;


import com.bluemango.project_backend.models.Product;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductRequest {

   
    // Getters and Setters

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @NotBlank(message = "Name cannot be blank")
    @Size(min=3, max = 255, message = "Name length max = 3, min = 255" )
    private String name; 

    @NotNull(message = "Description cannot be null")
    @NotEmpty(message = "Description cannot be empty")
    @NotBlank(message = "Description cannot be blank")
    @Size(min=3, max = 1024, message = "Name length max = 3, min = 255" )
    private String desc;

    private Double price;

    @ManyToOne
    private CategoryResponse category; 

    private boolean promotion;
    private boolean novo;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public CategoryResponse getCategory() {
        return category;
    }
    public void setCategory(CategoryResponse category) {
        this.category = category;
    }
    public boolean isPromotion() {
        return promotion;
    }
    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }
    public boolean isNovo() {
        return novo;
    }
    public void setNovo(boolean novo) {
        this.novo = novo;
    } 
    
    public Product toEntity(){
        Product product = new Product(name, desc, price, category, promotion, novo);
        return product; 
    }


 
}
