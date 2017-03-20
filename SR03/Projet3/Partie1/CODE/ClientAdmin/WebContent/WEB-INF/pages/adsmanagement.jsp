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
    <h1>Ads</h1>
    
   	
   	<h3>Current ads</h3>
       <table border="1">
       	<thead>
       		<tr>
       			<th>ID</th>
       			<th>Nom</th>
       			<th>Category</th>
       			<th>Street</th>
       			<th>City</th>
       			<th>Postcode</th>
       			<th>Phone number</th>
       			<th>Edit</th>
       			<th>Delete</th>
       		</tr>
       	</thead>
       	<c:forEach items="${ads}" var="ad">
		    <tr>
		    	<td>${ad.getAnnonce_id()}</td>
		    	<td>${ad.getNom()}</td>
		    	<td>${ad.getCategorieOjet().getNom()}</td>
		    	<td>${ad.getAdresseObjet().getRue()}</td>
		    	<td>${ad.getAdresseObjet().getVille()}</td>
		    	<td>${ad.getAdresseObjet().getCodepostal()}</td>
		    	<td>${ad.getTelephone()}</td>
		    	<td><a href="?edit=${ad.getAnnonce_id()}">Edit</a></td>
		    	<td><a href="?delete=${ad.getAnnonce_id()}">Delete</a></td>
		    </tr>
		</c:forEach>
       </table>
   	
   	<h3>Add an ad</h3>
	<form method="POST" action="">
		<table>
			<tr><td><label for="name">Name</label>:</td><td><input required type="text" name="name" id="name" /></td></tr>
			<tr><td><label for="category">Category</label>:</td><td>
				<select name="category">
				<c:forEach items="${categories}" var="category">
				  <option value="${category.getCategorie_id()}">${category.getNom()}</option> 
				</c:forEach>
				</select>
				</td></tr>
			<tr><td><label for="street">Street</label>:</td><td><input required type="text" name="street" id="street" /></td></tr>
			<tr><td><label for="city">City</label>:</td><td><input required type="text" name="city" id="city" /></td></tr>
			<tr><td><label for="postcode">Postcode</label>:</td><td><input required type="text" name="postcode" id="postcode" /></td></tr>
			<tr><td><label for="telephone">Telephone</label>:</td><td><input required type="text" name="telephone" id="telephone" /></td></tr>
			<tr><td style="text-align:center" colspan="2"><input style="width: 50%" type="submit" value="Submit" /></td></tr>
		</table>
	</form>
	<span class="err">${message_error}</span>

</body>
</html>