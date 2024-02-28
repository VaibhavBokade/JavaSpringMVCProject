<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./base.jsp" %>
<html>
<head>
    <title>Delete Product</title>
</head>
<body>
<div class="container">
    <h2>Delete Product</h2>
    <p>Are you sure you want to delete this product?</p>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
        </tr>
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
        </tr>
    </table>
    <form action="/delete-product/${product.id}" method="post">
        <button type="submit">Delete Product</button>
        <a href="/">Cancel</a>
    </form>
</div>
</body>
</html>
