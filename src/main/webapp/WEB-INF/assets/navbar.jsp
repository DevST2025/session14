<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="/administration">Admin Management</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link ${param.name=='home'?'active':''}" href="/administration/user-manager">User Manager</a>
        </li>
        <li class="nav-item">
          <a class="nav-link ${param.name=='product'?'active':''}" href="/administration/product-manager">Product Manager</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
