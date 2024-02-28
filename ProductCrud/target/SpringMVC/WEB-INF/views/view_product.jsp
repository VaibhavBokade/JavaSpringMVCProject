<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./base.jsp" %>
<html>
<head>
    <title>View Product</title>
</head>
<body>
<div class="container">
    <h2>Product Details</h2>
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
    <a href="/">Back to Product List</a>
</div>
</body>
</html>
