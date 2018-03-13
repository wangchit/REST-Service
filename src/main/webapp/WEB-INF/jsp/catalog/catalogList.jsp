<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Catalog List</title>
</head>
<body>
    Catalog List
<br>
<div> 
    <c:forEach var="catalog" items="${catalogList}"> 
        <li><c:out value="${catalog.catalogName}" /></li> 
    </c:forEach> 
</div>    
<a href="add.html">Add New Catalog</a>
</body>
</html>