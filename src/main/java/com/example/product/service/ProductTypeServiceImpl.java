package com.example.product.service;

import com.example.product.model.ProductType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductTypeServiceImpl extends DB implements ProductTypeService {
    private static final String SELECT_ALL_PRODUCT_TYPE = "SELECT * FROM product_ll.producttype";
    private static final String FIND_PRODUCT_TYPE_BY_ID = "SELECT * FROM product_ll.producttype where id = ?";

    private ProductType getProductTypeResultSet(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");


        ProductType productType = new ProductType(id, name);
        return productType;
    }
    @Override
    public List<ProductType> findAll() {
        List<ProductType> productTypes = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT_TYPE);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                ProductType productType = getProductTypeResultSet(rs);
                productTypes.add(productType);
            }
            preparedStatement.close();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return productTypes;

    }

    @Override
    public ProductType findAllId(long id) {
        Connection connection = getConnection();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_TYPE_BY_ID);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                ProductType productType = getProductTypeResultSet(rs);
                return productType;
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
     return null;
    }
    }

