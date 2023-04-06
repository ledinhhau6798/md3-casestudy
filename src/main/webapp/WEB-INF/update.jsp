<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 3/26/2023
  Time: 7:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/admin/layout/css_head.jsp"></jsp:include>
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.7.3/sweetalert2.all.js" integrity="sha512-5gz/at+6LT6tuaX/ritelLOHwVc0CoXsspPbUBPdoIrOLshcpguRTMBAKVZCdG//YdwyYP2c4n7NMaDqVXWTJQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" href="/bootstrap.min.css">
<html>
<head>
  <title>updete product</title>
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
  </style>
</head>
<body>
<form method="post">
<%--  <div class="col-6 offset-5 " style="color: red">--%>
<%--    <c:if test="${requestScope.message!=null}">--%>
<%--      <h4>${requestScope.message}</h4>--%>
<%--    </c:if>--%>
<%--    <c:if test="${requestScope.errors!=null}">--%>
<%--      <div>--%>
<%--        <c:forEach items="${requestScope.errors}" var="v">--%>
<%--          <p>${v}</p>--%>
<%--        </c:forEach>--%>
<%--      </div>--%>
<%--    </c:if>--%>
<%--  </div>--%>



  <div class="container">
    <div class="row">
      <div class="col-6 offset-3">
        <c:if test="${requestScope.errors!=null}">
          <div>
            <c:forEach items="${requestScope.errors}" var="v">
              <p>${v}</p>
            </c:forEach>
          </div>
        </c:if>
        <h1>Update List</h1>
        <div class="row mb-1">
          <label class="col-4">Name</label>
          <input class="col-8" name="txtName" value="${requestScope.product1.getName()}">
        </div>
        <div class="row mb-1" >
          <label class="col-4">Description</label>
          <input class="col-8" name= "txtDescription" value="${requestScope.product1.getDescription()}">
        </div>
        <div class="row mb-1">
          <label class="col-4">Quantity</label>
          <input class="col-8" name= "txtQuantity" value="${requestScope.product1.getQuantity()}">
        </div>
        <div class="row mb-1">
          <label class="col-4">Price</label>
          <input class="col-8" name="txtPrice" value="${requestScope.product1.getPrice()}">
        </div>
        <div class="row mb-1">
          <label class="col-4">Product Types</label>
          <select class="col-8" name="sProductTypes">
            <c:forEach items="${requestScope.producttypes}" var="p">
              <option value="${p.getId()}"
                      <c:if test="${p.getId() == requestScope.product1.getIdCategory()}">
                        selected
                      </c:if>
              >${p.getName()}</option>
            </c:forEach>
          </select>
        </div>
        <div>
          <label></label>
          <button class="col-8 offset-4 add" >Update</button>
        </div>
      </div>
    </div>
  </div>

</form>
<%--<c:if test="${requestScope.message!=null}">--%>
<%--  <h4>${requestScope.message}</h4>--%>
<%--</c:if>--%>
<%--<form method="post">--%>
<%--  <div>--%>
<%--    <label>Name</label>--%>
<%--    <input name="txtName" value="${requestScope.product1.getName()}">--%>
<%--  </div>--%>
<%--  <div>--%>
<%--    <label>Description</label>--%>
<%--    <input name="txtDescription" value="${requestScope.product1.getDescription()}">--%>
<%--  </div>--%>
<%--  <div>--%>
<%--    <label>Quantity</label>--%>
<%--    <input name="txtQuantity" value="${requestScope.product1.getQuantity()}">--%>
<%--  </div>--%>
<%--  <div>--%>
<%--    <label>Price</label>--%>
<%--    <input name="txtPrice" value="${requestScope.product1.getPrice()}">--%>
<%--  </div>--%>
<%--  <div>--%>
<%--    <label>Product Type </label>--%>
<%--    <select name="sProductTypes">--%>
<%--      <c:forEach items="${requestScope.producttypes}" var="p">--%>
<%--        <option value="${p.getId()}"--%>
<%--                <c:if test="${p.getId() == requestScope.product1.getIdCategory()}">--%>
<%--                  selected--%>
<%--                </c:if>--%>
<%--        >${p.getName()}</option>--%>
<%--      </c:forEach>--%>
<%--    </select>--%>
<%--  </div>--%>
<%--  <div>--%>
<%--    <label></label>--%>
<%--    <button>Update</button>--%>
<%--  </div>--%>
<%--</form>--%>
</body>
</html>
