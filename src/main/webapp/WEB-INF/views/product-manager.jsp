<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nhi
  Date: 17/11/2023
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<jsp:include page="//WEB-INF/assets/navbar.jsp"></jsp:include>
<h1 class="text-center bg-success">Product List</h1>

<div class="container-fluid">
    <table class="table">
        <thead>
        <tr>
            <th scope="col" class="text-center">ID</th>
            <th scope="col" class="text-center">&nbsp;</th>
            <th scope="col" class="text-center">Name</th>
            <th scope="col" class="text-center">Price</th>
            <th scope="col" class="text-center">Stock</th>
            <th scope="col" class="text-center">Status</th>
            <th scope="col" colspan="2" class="text-center">Action</th>
        </tr>
        </thead>
        <tbody style="line-height: 100px">
        <c:forEach items="${products}" var="p" varStatus="loop">
            <tr>
                <th scope="row" class="text-center">${p.id}</th>
                <td>
                    <img width="100" src="${p.imgUrl}" alt="#">
                </td>
                <td class="text-center">${p.name}</td>
                <td class="text-center">${p.price}</td>
                <td class="text-center">${p.stock}</td>
                <td class="text-center">${p.status?'Actice':'Hidden'}</td>
                <td class="text-center">
                    <a href="" class="text-dark">
                        <i class="fa-solid fa-pen-to-square"></i>
                    </a>
                </td>
                <td class="text-center">
                    <a href="" class="text-dark">
                        <i class="fa-solid fa-trash"></i>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div style="
    padding: 10px;
    width: 100px;
    background: #1a8754;
    border-radius: 5px;
    margin: 0 50%;
    text-align: center;
    "
>
    <a href="/administration/form-add" style="text-decoration: none;color: white">ADD NEW</a>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
