<%--
  Created by IntelliJ IDEA.
  User: nhi
  Date: 17/11/2023
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<jsp:include page="//WEB-INF/assets/navbar.jsp"></jsp:include>
<h1 class="text-center bg-success">Add new product</h1>
<div class="container-fluid">
  <form class="row g-3" action="/administration/add-product" method="POST" enctype="multipart/form-data">
    <div class="col-md-12">
      <label for="name" class="form-label">Product name</label>
      <input type="text" class="form-control" id="name" name="name">
    </div>
    <div class="col-12">
      <label for="image" class="form-label">Upload Image</label>
      <input type="file" class="form-control" id="image" name="image">
    </div>
    <div class="col-6">
      <label for="price" class="form-label">Price</label>
      <input type="number" class="form-control" id="price" name="price">
    </div>
    <div class="col-6">
      <label for="stock" class="form-label">Stock</label>
      <input type="number" class="form-control" id="stock" name="stock">
    </div>
    <div class="col-12">
      <button type="submit" class="btn" style="color: #1a8754">Add Product</button>
    </div>
  </form>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
