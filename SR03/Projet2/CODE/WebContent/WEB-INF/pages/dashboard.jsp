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
    <h1>Dashboard</h1>
    
    <c:choose>
	    <c:when test="${user.isAdmin()}">
	        <a href="dashboard/users">Users management</a><br />
	        <a href="dashboard/quizzes">Quizzes management</a><br />
	        <a href="dashboard/courses">Courses management</a>
	    </c:when>    
	    <c:otherwise>
	    	<p>Please take a quiz.</p>
	        <table border="1">
	        	<thead>
	        		<tr>
	        			<th>Subject</th>
	        			<th>Action</th>
	        		</tr>
	        	</thead>
	        	<c:forEach items="${quizzes}" var="quiz">
				    <tr>
				    	<td>${quiz.subject}</td>
				    	<td>
				    		<c:choose>
							   <c:when test="${courses.containsKey(quiz.getQuiz_id())}"><a href="${pageContext.request.contextPath}/dashboard/finishquiz?id=${courses.get(quiz.getQuiz_id())[1]}">Already taken</a>. Your score: ${courses.get(quiz.getQuiz_id())[0]}%</c:when>
							   <c:otherwise><a href="${pageContext.request.contextPath}/dashboard/takequiz?id=${quiz.quiz_id}">Take the quiz</a></c:otherwise>
							</c:choose>
						</td>
				    </tr>
				</c:forEach>
	        </table>
	    </c:otherwise>
	</c:choose>
</body>
</html>