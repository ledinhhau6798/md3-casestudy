package com.example.product.service;

import com.example.product.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    void add(Product product);
    void update(long id, Product product);
    void delete(long id);

    Product finAllId(long id);

    public long maxId();
    List<Product> search(int offset, int limit, String kw, int productType);

}
