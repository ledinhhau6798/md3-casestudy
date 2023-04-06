package com.example.product.model;

public class ProductType {
    private long id;
    private String name;

    public ProductType(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductType() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
