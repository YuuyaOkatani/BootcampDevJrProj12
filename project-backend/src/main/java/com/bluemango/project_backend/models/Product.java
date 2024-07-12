package com.bluemango.project_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_PRODUCT")
public class Product {
    
    //Atributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name; 
    private String desc;
    private double price;

    @ManyToOne
    private Category category; 

    private boolean promotion;
    private boolean novo; 

    // Constructor

    public Product(int id, String name, String desc, double price, Category category, boolean promotion, boolean novo) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.category = category;
        this.promotion = promotion; 
        this.novo = novo;
    }

    public Product(){

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public boolean isNovo() {
        return novo;
    }

    public void setId(int id) {
        this.id = id;
    }


    


    



    // Getters and Setters
    
    
    
}
