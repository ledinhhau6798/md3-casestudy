package com.example.product.service;

import com.example.product.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ProductServiceMySQLImpl extends DB implements ProductService {
    private static final String SELECT_ALL = "SELECT * FROM product_ll.product";
    private static final String ADD_PRODUCT = "INSERT INTO product_ll.product ( `name`, `description`, `quantity`, `price`,`idCategory` ) VALUES ( ?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT = "UPDATE product_ll.product SET `name` = ?, `description` = ?, `quantity`= ?, `price` = ?,`idCategory` = ? WHERE `id` =?";
    private static final String DELETE_PRODUCT = "Delete from product_ll.product where id = ?";
    private static final String FIND_PRODUCT_BY_ID = "SELECT * FROM product_ll.product where id = ?";
    private static final String SEARCH_PRODUCT_KW_ALL = "SELECT sql_calc_found_rows * FROM product_ll.product where `name` like ?  limit ?,?";
    private static final String SEARCH_PRODUCT_KW = "SELECT sql_calc_found_rows * FROM product_ll.product where `name` like ? and producttype = ? limit ?,? ";


    private int totalLines;
    private Product getProductResultSet(ResultSet rs) throws SQLException {
    long id =rs.getLong("id");
    String name = rs.getString("name");
    String description = rs.getString("description");
    Integer quantity = rs.getInt("quantity");
    Double price = rs.getDouble("price");
    long idCategory = rs.getLong("idCategory");
    Product product = new Product(id,name,description,quantity,price,idCategory);
    return product;
    }

    @Override
    public List<Product> findAll() {
       List<Product> products = new ArrayList<>();

        Connection connection =  getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Product product =getProductResultSet(rs);
                products.add(product);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    @Override
    public void add(Product product) {
    Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setString(2,product.getDescription());
            preparedStatement.setInt(3,product.getQuantity());
            preparedStatement.setDouble(4,product.getPrice());
            preparedStatement.setLong(5,product.getIdCategory());
            preparedStatement.executeUpdate();

            System.out.println("add product(): " + preparedStatement);
            preparedStatement.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void update(long id,Product product) {
    Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setString(1,product.getName() );
            preparedStatement.setString(2,product.getDescription());
            preparedStatement.setInt(3,product.getQuantity());
            preparedStatement.setDouble(4,product.getPrice());
            preparedStatement.setLong(5,product.getIdCategory());
            preparedStatement.setLong(6,id);
            preparedStatement.executeUpdate();

            System.out.println("update Product: " + preparedStatement);
            preparedStatement.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void delete(long id) {
    Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Product finAllId(long id) {
        Connection connection = getConnection();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_ID);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Product product = getProductResultSet(rs);
                return product;
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    @Override
    public long maxId() {
        return 0;
    }

    @Override
    public List<Product> search(int offset, int limit, String kw, int productType) {
       Connection connection = getConnection();
        PreparedStatement preparedStatement;
        List<Product> products = new ArrayList<>();
        try {
            if (productType == -1){
                 preparedStatement = connection.prepareStatement(SEARCH_PRODUCT_KW_ALL);
                preparedStatement.setString(1,kw);
                preparedStatement.setInt(2,offset);
                preparedStatement.setInt(3,limit);
            }else {
                preparedStatement = connection.prepareStatement(SEARCH_PRODUCT_KW);
                preparedStatement.setString(1,kw);
                preparedStatement.setInt(2,productType);
                preparedStatement.setInt(3,offset);
                preparedStatement.setInt(4,limit);
            }
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Product product = getProductResultSet(rs);
                products.add(product);
            }

           rs = preparedStatement.executeQuery("select found_rows()");
            while (rs.next()){
                totalLines = rs.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
