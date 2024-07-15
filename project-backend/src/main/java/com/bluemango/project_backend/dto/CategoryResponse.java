package com.bluemango.project_backend.dto;

public class CategoryResponse {

    // Não será necessário fazer constrains, pois o objeto é o mesmo
    private Integer id;
    private String name;

    public CategoryResponse(Integer id, String name) {
        //TODO Auto-generated constructor stub
        this.id = id;
        this.name = name;
    }

    public CategoryResponse(){

    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    

    
}
