package com.bluemango.project_backend.dto;

public class ProductResponse {
    private Long id;
    private String name;
    private String desc;
    private Double price;
    private CategoryResponse category;
    private boolean promotion;
    private boolean novo;

    public ProductResponse(Long id, String name, String desc, Double price, CategoryResponse category, boolean promotion, boolean novo) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.category = category;
        this.promotion = promotion; 
        this.novo = novo;
    }

    public ProductResponse() {
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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

}
