<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Product List</title>
</head>
<body>
    Product List
<br>
<div> 
    <c:forEach var="product" items="${productList}"> 
        <li><a href="view.html?prdtId=${product.productId}"><c:out value="${product.name}" /></a></li> 
    </c:forEach> 
</div>
<a href="add.html">Add New Product</a>
    
</body>
</html>