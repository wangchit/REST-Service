<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Add Catalog</title>
</head>
<body>
    Add New Catalog:
<br>
<sf:form method="POST" modelAttribute="catalog">
<table border="0">
	<tr> 
		<th><label for="catalog_name">Catalog Name:</label></th>
    	<td><sf:input path="catalogName" size="20" id="catalog_name" /><br/>
        	<sf:errors path="catalogName" cssClass="error" /> 
    	</td> 
	</tr> 
	<tr>
		<th></th>
		<td><input type="submit" value="Save" /></td>
	</tr>
</table>
</sf:form>
    
</body>
</html>