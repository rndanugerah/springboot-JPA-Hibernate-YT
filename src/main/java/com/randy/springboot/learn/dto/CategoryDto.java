package com.randy.springboot.learn.dto;

import jakarta.validation.constraints.NotEmpty;

public class CategoryDto {
    
    @NotEmpty(message = "Name is required")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
