package com.example.product.controller;

import com.example.product.model.Product;
import com.example.product.model.ProductType;
import com.example.product.service.ProductService;
import com.example.product.service.ProductServiceMySQLImpl;
import com.example.product.service.ProductTypeService;
import com.example.product.service.ProductTypeServiceImpl;
import com.example.product.uiti.Regex;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private ProductService productService;
    private ProductTypeService productTypeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
    productService = new ProductServiceMySQLImpl();
    productTypeService = new ProductTypeServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    if (action == null){
        action = "";
    }
    switch (action){
        case "add":
            showAdd(request,response);
            break;
        case "update":
            showUpdate(request,response);
            break;
        case "delete":
            showDelete(request,response);
            break;
        default:
            showFindAll(request,response);
    }
    }

    private void showFindAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message = null;
        if (request.getParameter("message") != null) {
            message = request.getParameter("message");
        }



        request.setAttribute("message", message);
        request.setAttribute("products",productService.findAll());
        request.setAttribute("producttypes",productTypeService.findAll());



        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/products.jsp");
        requestDispatcher.forward(request,response);
    }

    private void showDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idProduct = Long.parseLong(request.getParameter("id"));
        Product p = productService.finAllId(idProduct);

        request.setAttribute("product1", p);
        request.setAttribute("producttypes",productTypeService.findAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/delete.jsp");
        requestDispatcher.forward(request,response);
    }

    private void showUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idProduct = Long.parseLong(request.getParameter("id"));
        Product p = productService.finAllId(idProduct);

        request.setAttribute("product1", p);
        request.setAttribute("producttypes",productTypeService.findAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/update.jsp");
        requestDispatcher.forward(request,response);
    }

    private void showAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("producttypes",productTypeService.findAll());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/add.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action =request.getParameter("action");
    if (action == null){
        action="";
    }
    switch (action){
        case "add":
            addProduct(request,response);
            break;
        case "update":
            updateProduct(request,response);
            break;
        case "delete":
            deleteProduct(request,response);
            break;
        default:
            showFindAll(request,response);
    }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long idProduct = Long.parseLong(request.getParameter("id"));

        productService.delete(idProduct);
        response.sendRedirect("/products?message=delsuccess");


    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {



        List<String> errors = new ArrayList<>();
        Product product1 = new Product();
        long idProduct = Long.parseLong(request.getParameter("id"));
        validateName(product1,errors,request);
        validateDescription(product1,errors,request);
        validateQuantity(product1,errors,request);
        validatePrice(product1,errors,request);
        validateIdCategory(product1,errors,request);



        if (!errors.isEmpty()){
            request.setAttribute("product1",product1);
            request.setAttribute("errors",errors);

            request.setAttribute("producttypes",productTypeService.findAll());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/update.jsp");
            requestDispatcher.forward(request,response);
        } else {
            productService.update(idProduct,product1);
            response.sendRedirect("/products?message=updsuccess");

        }
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        Product product = new Product();


        validateName(product,errors,request);
        validateDescription(product,errors,request);
        validateQuantity(product,errors,request);
        validatePrice(product,errors,request);
        validateIdCategory(product,errors,request);



        if (!errors.isEmpty()){
            request.setAttribute("product",product);
            request.setAttribute("producttypes",productTypeService.findAll());
            request.setAttribute("errors",errors);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/add.jsp");
            requestDispatcher.forward(request,response);
        } else {

            productService.add(product);

            response.sendRedirect("/products?message=addsuccess");
        }




    }

    private void validateIdCategory(Product product, List<String> errors, HttpServletRequest request) {
        long idCategory = Long.parseLong(request.getParameter("sProductTypes"));
        product.setIdCategory(idCategory);
    }

    private void validatePrice(Product product, List<String> errors, HttpServletRequest request) {
        double price = 0.0d;
        try {
             price = Double.valueOf(request.getParameter("txtPrice"));
            if (!Regex.isPrice(String.valueOf(price))){
                errors.add("price not suitable");
            }
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
        }

        product.setPrice(price);
    }

    private void validateQuantity(Product product, List<String> errors, HttpServletRequest request) {
        int quantity = 0;
        try {
            quantity = Integer.parseInt(request.getParameter("txtQuantity"));
            if (!Regex.isQuantity(String.valueOf(quantity))){
                errors.add("quantity not suitable");
            }

        } catch (NumberFormatException exception) {
            exception.printStackTrace();
        }

        product.setQuantity(quantity);
    }


    private void validateDescription(Product product, List<String> errors, HttpServletRequest request) {
        String description = request.getParameter("txtDescription");
        if (!Regex.isDescription(description)){
            errors.add("Description not suitable");
        }
        product.setDescription(description);
    }

    private void validateName(Product product,List<String> errors, HttpServletRequest request) {
        String name = request.getParameter("txtName");
        if (!Regex.isName(name)){
            errors.add("Name not suitable");
        }
        product.setName(name);
    }
}
