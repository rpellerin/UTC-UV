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
    
   	<h2>Quizzes taken by interns</h2>
       <table border="1">
       	<thead>
       		<tr>
       			<th>Date</th>
       			<th>Duration</th>
       			<th>Person</th>
       			<th>Score</th>
       			<th>Actions</th>
       		</tr>
       	</thead>
       	<c:forEach items="${courses}" var="course">
		    <tr>
		    	<td>${course.getDate()}</td>
		    	<td>${course.getDuration()} secondes</td>
		    	<td>${course.getUser()}</td>
		    	<td>${course.getScore()}%</td>
		    	<td>
		    		<a href="${pageContext.request.contextPath}/dashboard/finishquiz?id=${course.getCourse_id()}">Details</a>
		    		<a href="?delete=${course.getCourse_id()}">Delete</a>
		    	</td>
		    </tr>
		</c:forEach>
       </table>
	</body>
</html>