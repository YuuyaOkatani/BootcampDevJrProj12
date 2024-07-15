package com.bluemango.project_backend.dto;

import com.bluemango.project_backend.models.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;


public class CategoryRequest {

 // Com essa única linha de código ajuda muito...
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @NotBlank(message = "Name cannot be blank")
    @Size(min=3, max = 255, message = "Name length max = 3, min = 255" )
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category toEntity(){
        return new Category(name);
    }


    
}
