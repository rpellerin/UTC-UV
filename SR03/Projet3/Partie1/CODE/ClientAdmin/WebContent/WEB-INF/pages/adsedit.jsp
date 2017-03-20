<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link rel="stylesheet" media="all" href="${pageContext.request.contextPath}/resources/css/global.css" />
    <title>Edit an ad</title>
</head>
<body>
	<a class="home" href="${pageContext.request.contextPath}/ads">&lt; BACK</a>
    <h1>Edit an ad</h1>
    
	<form method="POST" action="">
		<table>
			<tr><td><label for="name">Name</label>:</td><td><input required type="text" name="name" id="name" value="${ad.getNom() }"/></td></tr>
			<tr><td><label for="category">Category</label>:</td><td>
				<select name="category">
				<c:forEach items="${categories}" var="category">
				  <option <c:if test="${category.getCategorie_id() == ad.getCategorie_id()}">selected</c:if> value="${category.getCategorie_id()}">${category.getNom()}</option> 
				</c:forEach>
				</select>
				</td></tr>
			<tr><td><label for="street">Street</label>:</td><td><input required type="text" name="street" id="street" value="${ad.getAdresseObjet().getRue() }"/></td></tr>
			<tr><td><label for="city">City</label>:</td><td><input required type="text" name="city" id="city" value="${ad.getAdresseObjet().getVille() }"/></td></tr>
			<tr><td><label for="postcode">Postcode</label>:</td><td><input required type="text" name="postcode" id="postcode" value="${ad.getAdresseObjet().getCodepostal() }" /></td></tr>
			<tr><td><label for="telephone">Telephone</label>:</td><td><input required type="text" name="telephone" id="telephone" value="${ad.getTelephone() }"/></td></tr>
			<tr><td style="text-align:center" colspan="2"><input style="width: 50%" type="submit" value="Submit" /></td></tr>
		</table>
	</form>
	<span class="err">${message_error}</span>

</body>
</html>