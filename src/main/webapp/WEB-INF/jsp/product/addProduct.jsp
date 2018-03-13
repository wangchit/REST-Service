<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Add Product</title>
</head>
<body>
    Add New Product:
<br>
<sf:form method="POST" modelAttribute="product">
<table border="0">
	<tr> 
		<th><label for="product_sku">Product SKU:</label></th>
    	<td><sf:input path="sku" size="15" id="product_sku" /><br/>
        	<sf:errors path="sku" cssClass="error" /> 
    	</td> 
	</tr> 
	<tr> 
		<th><label for="product_name">Product Name:</label></th>
    	<td><sf:input path="name" size="20" id="product_name" /><br/>
        	<sf:errors path="name" cssClass="error" /> 
    	</td> 
	</tr> 
	<tr> 
		<th><label for="product_unitOfMeasure">Product UnitOfMeasure:</label></th>
    	<td><sf:input path="unitOfMeasure" size="20" id="product_unitOfMeasure" /><br/>
        	<sf:errors path="unitOfMeasure" cssClass="error" /> 
       </td>
	</tr> 
	<tr> 
		<th><label for="product_price">Product Price:</label></th>
    	<td><sf:input path="price" size="20" id="product_price" /><br/>
        	<sf:errors path="price" cssClass="error" /> 
       </td> 
    </tr> 
    <tr> 
		<th><label for="product_availableQuantity">Product AvailableQuantity:</label></th>
    	<td><sf:input path="availableQuantity" size="20" id="product_availableQuantity" /><br/>
        	<sf:errors path="availableQuantity" cssClass="error" /> 
       </td> 
    </tr> 
       
	<tr> 
		<th><label for="catalog_name">Catalog Name:</label></th>
    	<td>
    		<sf:select path="catalog.catalogId" >
    		   <sf:options items="${catalogList}" itemLabel="catalogName" itemValue="catalogId" />
    		</sf:select>
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

