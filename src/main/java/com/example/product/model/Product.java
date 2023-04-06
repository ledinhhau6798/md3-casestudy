package com.example.product.model;

public class Product {
    private long id;
    private String name;
    private String description;
    private int quantity;
    private double price;

    private long idCategory;

    public Product(long id, String name, String description, int quatity, double price, long idCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quatity;
        this.price = price;
        this.idCategory = idCategory;
    }

    public Product() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }
}
