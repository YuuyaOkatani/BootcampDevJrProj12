package com.bluemango.project_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_CATEGORY") // Coloca o nome na tabela
public class Category {
    
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    
    private String name;

    // Constructor
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
    

    // Getters and Setters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    
    

    
}