<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Product List</title>
</head>
<body>
	Products:
	<sf:form method="POST" commandName="selectedProducts" action="list.html">
		<div>
			<c:forEach var="prdt" items="${productList}">
				<sf:checkbox path="itemList" value="${prdt.productId}" />
				<a href="view.html?prdtId=${prdt.productId}">
					<c:out value="${prdt.name}" />
				</a>
				<br>
			</c:forEach>
		</div>
 		<input type="submit" value="Delete" />
	</sf:form>
	<br>
	<a href="add.html">Add New Product</a>
</body>
</html>