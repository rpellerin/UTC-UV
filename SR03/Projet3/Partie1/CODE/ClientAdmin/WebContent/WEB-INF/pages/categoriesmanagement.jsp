<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link rel="stylesheet" media="all" href="${pageContext.request.contextPath}/resources/css/global.css" />
    <title>Categories management</title>
</head>
<body>
	<a class="home" href="${pageContext.request.contextPath}/">&lt; HOME</a>
    <h1>Categories</h1>
    
   	
   	<h3>Current categories</h3>
       <table border="1">
       	<thead>
       		<tr>
       			<th>ID</th>
       			<th>Nom</th>
       			<th>Edit</th>
       			<th>Delete</th>
       		</tr>
       	</thead>
       	<c:forEach items="${categories}" var="category">
		    <tr>
		    	<td>${category.getCategorie_id()}</td>
		    	<td>${category.getNom()}</td>
		    	<td><a href="?edit=${category.getCategorie_id()}">Edit</a></td>
		    	<td><a href="?delete=${category.getCategorie_id()}">Delete</a></td>
		    </tr>
		</c:forEach>
       </table>
   	
   	<h3>Add a category</h3>
	<form method="POST" action="">
		<table>
			<tr><td><label for="name">Name</label>:</td><td><input required type="text" name="name" id="name" /></td></tr>
			<tr><td style="text-align:center" colspan="2"><input style="width: 50%" type="submit" value="Submit" /></td></tr>
		</table>
	</form>
	<span class="err">${message_error}</span>

</body>
</html>