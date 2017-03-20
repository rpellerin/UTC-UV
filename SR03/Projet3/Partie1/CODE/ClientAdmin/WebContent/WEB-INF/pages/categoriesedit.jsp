<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link rel="stylesheet" media="all" href="${pageContext.request.contextPath}/resources/css/global.css" />
    <title>Edit a category</title>
</head>
<body>
	<a class="home" href="${pageContext.request.contextPath}/categories">&lt; BACK</a>
    <h1>Edit a category</h1>
    
	<form method="POST" action="">
		<table>
			<tr><td><label for="name">Name</label>:</td><td><input required type="text" name="name" id="name" value="${name}" /></td></tr>
			<tr><td style="text-align:center" colspan="2"><input style="width: 50%" type="submit" value="Submit" /></td></tr>
		</table>
	</form>
	<span class="err">${message_error}</span>

</body>
</html>