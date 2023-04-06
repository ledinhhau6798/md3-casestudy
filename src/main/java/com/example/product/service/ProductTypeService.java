package com.example.product.service;

import com.example.product.model.ProductType;

import java.util.List;

public interface ProductTypeService {
    List<ProductType> findAll();
    ProductType findAllId(long id);
}
