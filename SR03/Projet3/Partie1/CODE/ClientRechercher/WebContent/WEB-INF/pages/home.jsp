<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link rel="stylesheet" media="all" href="${pageContext.request.contextPath}/resources/css/global.css" />
    <title>Search engine for ads</title>
</head>
<body>
    <h1>Search engine for ads</h1>
   	
	<form method="GET" action="home">
		<table>
			<tr><td><label for="name">Name</label>:</td><td><input type="text" name="name" id="name" value="${name}"/></td></tr>
			<tr><td><label for="category">Category</label>:</td><td><input type="text" name="category" id="category" value="${category}"/></td></tr>
			<tr><td><label for="street">Street</label>:</td><td><input type="text" name="street" id="street" value="${street}"/></td></tr>
			<tr><td><label for="city">City</label>:</td><td><input type="text" name="city" id="city" value="${city}"/></td></tr>
			<tr><td><label for="postcode">Postcode</label>:</td><td><input type="text" name="postcode" id="postcode" value="${postcode}"/></td></tr>
			<tr><td style="text-align:center" colspan="2"><input style="width: 50%" type="submit" value="Submit" /><input style="width: 50%" type="reset" onClick="location='home';" value="Reset" /></td></tr>
		</table>
	</form>
	
	<c:if test="${null != ads}">
		<h3>Results</h3>
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
					</tr>
				</c:forEach>
		</table>
    </c:if>
	<span class="err">${message_error}</span>

</body>
</html>