<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link rel="stylesheet" media="all" href="${pageContext.request.contextPath}/resources/css/global.css" />
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" />
    <title>DASHBOARD</title>
</head>
<body>
	<a class="logout" href="${pageContext.request.contextPath}/logout">LOGOUT</a>
    <h1><a style="color: inherit; text-decoration: none" href="../" title="Back">&lt; </a>Dashboard</h1>
    
   	<h2>Current users</h2>
   	
   		<form action="" method="GET">
   			Search user: <input type="text" name="recherche" placeholder="Name" />
   			<input type="submit" value="Search" /><a href="users">Reset</a>
   		</form>
   		<br /><br />
   		
   		</form>
       <table border="1">
       	<thead>
       		<tr>
       			<th>Email</th>
       			<th>Name</th>
       			<th>Company</th>
       			<th>Phone</th>
       			<th>Created</th>
       			<th>Active?</th>
       			<th>Admin?</th>
       			<th>Delete</th>
       		</tr>
       	</thead>
       	<c:forEach items="${users}" var="user">
		    <tr>
		    	<td>${user.getEmail()}</td>
		    	<td>${user.getName()}</td>
		    	<td>${user.getCompany()}</td>
		    	<td>${user.getPhoneNumber()}</td>
		    	<td>${user.getCreation()}</td>
		    	<td><input type="checkbox" <c:if test="${user.isActive()}">checked</c:if> disabled readonly></td>
		    	<td><input type="checkbox" <c:if test="${user.isAdmin()}">checked</c:if> disabled readonly></td>
		    	<td><a href="?delete=${user.getEmail()}">Delete</a></td>
		    </tr>
		</c:forEach>
       </table>
       
       <div>
       <c:forEach begin="1" end="${countPages}" varStatus="loop">
       		<a <c:if test="${loop.index == currentPage}">style="font-size: 1.5em"</c:if> href="?page=${loop.index}">${loop.index}</a>
       </c:forEach>
       </div>
       
	<h2>Create a new user</h2>
		<form method="POST" action="">
			<table>
				<tr><td><label for="email">Email</label>:</td><td><input required type="email" name="email" id="email" /></td></tr>
				<tr><td><label for="password">Password</label>:</td><td><input pattern=".{6,}" required type="password" name="password" id="password"/> (6 char. at least)</td></tr>
				<tr><td><label for="name">Name</label>:</td><td><input required type="text" name="name" id="name" /></td></tr>
				<tr><td><label for="company">Company</label>:</td><td><input type="text" name="company" id="company" /></td></tr>
				<tr><td><label for="phoneNumber">Phone number</label>:</td><td><input type="text" name="phoneNumber" id="phoneNumber" /></td></tr>
				<tr><td><label for="admin">Admin?</label></td><td><input type="checkbox" name="isAdmin" id="admin" /></td></tr>
				<tr><td style="text-align:center" colspan="2"><input style="width: 50%" type="submit" value="Submit" /></td></tr>
			</table>
		</form>
</body>
</html>