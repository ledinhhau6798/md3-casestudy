<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 3/26/2023
  Time: 7:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>Form Elements | Zircos - Responsive Bootstrap 4 Admin Dashboard</title>
<jsp:include page="/WEB-INF/admin/layout/css_head.jsp"></jsp:include>
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.7.3/sweetalert2.all.js" integrity="sha512-5gz/at+6LT6tuaX/ritelLOHwVc0CoXsspPbUBPdoIrOLshcpguRTMBAKVZCdG//YdwyYP2c4n7NMaDqVXWTJQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<link rel="stylesheet" href="/bootstrap.min.css">
<html>
<head>
    <title>add product</title>
    <style>

    h1{
        justify-content: center;
        display: flex;
        color: red;
    }
    body{
        background-color: lightgoldenrodyellow;
    }
    .add{
        background-color: #b1dfbb;
    }


    </style>
</head>
<body>


<form method="post">
    <div class="container justify-content-center">
        <div class="row justify-content-center">
            <div class="col-6">
                <c:if test="${requestScope.errors!=null}">
                    <div class="alert alert-danger" role="alert">
                        <ul>
                            <c:forEach items="${requestScope.errors}" var="e">
                                <li>${e}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>

                <h1>Add List</h1>
                <div class="row mb-1">
                    <label class="col-4">Name</label>
                    <input class="col-8" name="txtName" value="${requestScope.product.getName()}">
                </div>
                <div class="row mb-1">
                    <label class="col-4">Description</label>
                    <input class="col-8" name= "txtDescription" value="${requestScope.product.getDescription()}">
                </div>
                <div class="row mb-1">
                    <label class="col-4">Quantity</label>
                    <input class="col-8" name= "txtQuantity" value="${requestScope.product.getQuantity()}">
                </div>
                <div class="row mb-1">
                    <label class="col-4">Price</label>
                    <input class="col-8" name="txtPrice" value="${requestScope.product.getPrice()}">
                </div>
                <div class="row mb-1">
                    <label class="col-4">Product Types</label>
                    <select class="col-8" name="sProductTypes">
                        <c:forEach items="${requestScope.producttypes}" var="p">
                            <option value="${p.getId()}">${p.getName()}</option>
                        </c:forEach>
                    </select>

                </div>
                <div>
                    <label></label>
                    <button class="col-8 offset-4 add" >Add</button>
                </div>
            </div>
    </div>
    </div>
</form>

<%--<c:if test="${requestScope.message!=null}">--%>
<%--    <h4>${requestScope.message}</h4>--%>
<%--</c:if>--%>
<%--<c:if test="${requestScope.errors!=null}">--%>
<%--    <div>--%>
<%--        <c:forEach items="${requestScope.errors}" var="v">--%>
<%--            <p>${v}</p>--%>
<%--        </c:forEach>--%>
<%--    </div>--%>
<%--</c:if>--%>

<%--<form method="post">--%>
<%--<div class="col-lg-6">--%>

<%--    <h4 class="header-title">Add List</h4>--%>

<%--    <div class="p-4">--%>
<%--        <form action="#" data-parsley-validate="" novalidate="">--%>
<%--            <div class="form-group">--%>
<%--                <label >Name<span class="text-danger"></span></label>--%>
<%--                <input type="text" name="txtName" parsley-trigger="change" required="" placeholder="name" class="form-control" >--%>
<%--            </div>--%>
<%--            <div class="form-group">--%>
<%--                <label >Description<span class="text-danger"></span></label>--%>
<%--                <input type="text" name="txtDescription" parsley-trigger="change" required="" placeholder="Description" class="form-control" >--%>
<%--            </div>--%>
<%--            <div class="form-group">--%>
<%--                <label >Quantity<span class="text-danger"></span></label>--%>
<%--                <input type="text" name="txtQuantity" parsley-trigger="change" required="" placeholder="Quantity" class="form-control" >--%>
<%--            </div>--%>
<%--            <div class="form-group">--%>
<%--                <label >Price<span class="text-danger"></span></label>--%>
<%--                <input type="text" name="txtPrice" parsley-trigger="change" required="" placeholder="Price" class="form-control" >--%>
<%--            </div>--%>
<%--            <div>--%>
<%--                <label>Product Types</label>--%>
<%--                <select name="sProductTypes">--%>
<%--                    <c:forEach items="${requestScope.producttypes}" var="p">--%>
<%--                        <option value="${p.getId()}">${p.getName()}</option>--%>
<%--                    </c:forEach>--%>
<%--                </select>--%>

<%--            </div>--%>


<%--            <div class="form-group text-right mb-0">--%>
<%--                <button class="btn btn-primary waves-effect waves-light" type="submit">--%>
<%--                    ADD--%>
<%--                </button>--%>
<%--            </div>--%>

<%--        </form>--%>
<%--    </div>--%>

<%--</div>--%>

<%--  </form>--%>
</div>
</body>
</html>
