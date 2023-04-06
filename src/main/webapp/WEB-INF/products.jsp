<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 3/26/2023
  Time: 6:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.7.3/sweetalert2.all.js" integrity="sha512-5gz/at+6LT6tuaX/ritelLOHwVc0CoXsspPbUBPdoIrOLshcpguRTMBAKVZCdG//YdwyYP2c4n7NMaDqVXWTJQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<html>
<head>
    <title>Title</title>
    <STYLE>
        body{
            background-color:lightgoldenrodyellow;

        }
        .firstPart{
            display: flex;
            justify-content: center;

        }
        .bodyPart{
            display: flex;
            justify-content: center;
            background-color:lightgoldenrodyellow;
        }
        table{
            background-color:lightgoldenrodyellow;
            padding: 10px;
            width: 100%;
        }
        th,td{
            border: 1px solid;
        }
        th{
            text-align: center;
        }
        td{
            padding: 5px;
        }
        h1{
            color: red;

        }
        a{
            color: blue;
        }
        .btnaction{
            margin: 5px;
        }
    </STYLE>
</head>
<body>
    <div class="">

    <div class="table-hover col-10 offset-1">

        <div class="firstPart">
            <h1>LIST OF PRODUCT</h1>
        </div>
        <c:if test="${requestScope.message != null}">
            <script>
                let message = '<%= request.getAttribute("message")%>';

                let action = message.substring(0, 3);
                console.log(action);

                let titleAction = "";

                switch (action){
                    case 'del':
                        titleAction = "Xóa";
                        break;
                    case 'add':
                        titleAction = "Thêm";
                        break;
                    case 'upd':
                        titleAction = "Sửa";
                        break;
                }
                window.onload = function () {
                    Swal.fire({
                        position: 'top-end',
                        icon: 'success',
                        title: titleAction + " thành công",
                        showConfirmButton: false,
                        timer: 1500
                    })
                };
            </script>
        </c:if>
        <div class="btnaction d-flex justify-content-end">
            <a href="/products?action=add" class="btn btn-primary">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                    <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                </svg></a>
        </div>
        <div class="bodyPart">
            <table>

            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Description</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Category</th>
                <td>Action</td>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.products}" var="p">
                <tr>
                    <td>${p.getId()}</td>
                    <td>${p.getName()}</td>
                    <td>${p.getDescription()}</td>
                    <td>${p.getQuantity()}</td>
                    <td>${p.getPrice()}</td>

                    <td>
                        <c:forEach items="${requestScope.producttypes}" var="pt">
                            <c:if test="${pt.getId() == p.getIdCategory()}">
                                ${pt.getName()}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>

                        <a href="/products?action=update&id=${p.getId()}">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                                <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                            </svg>
                        </a>
                        <a href="/products?action=delete&id=${p.getId()}">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
                                <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
                            </svg>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            </table>
        </div>
    </div>
    </div>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
</body>
</html>
