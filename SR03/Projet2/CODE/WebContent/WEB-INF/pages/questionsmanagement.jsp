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
    <h1><a style="color: inherit; text-decoration: none" href="${pageContext.request.contextPath}/dashboard/quizzes" title="Back">&lt; </a>Dashboard</h1>
    
   	<h2>Quiz: ${quiz.getSubject()}</h2>
   	
   	<h3>Current questions</h3>
       <table border="1">
       	<thead>
       		<tr>
       			<th>Order</th>
       			<th>Question</th>
       			<th>Active?</th>
       			<th>Edit</th>
       			<th>Delete</th>
       		</tr>
       	</thead>
       	<c:forEach items="${questions}" var="question">
		    <tr>
		    	<td>${question.get_order()}</td>
		    	<td>${question.getQuestion()}</td>
		    	<td><input type="checkbox" <c:if test="${question.isActive()}">checked</c:if> disabled readonly></td>
		    	<td><a href="${pageContext.request.contextPath}/dashboard/questions/${question.getQuestion_id()}">Edit</a></td>
		    	<td><a href="?delete=${question.getQuestion_id()}">Delete</a></td>
		    </tr>
		</c:forEach>
       </table>
   	
   	<h3>Add a question</h3>
	<form method="POST" action="">
		<input required type="hidden" name="quiz" value="${quiz.getQuiz_id()}" />
		<table>
			<tr><td><label for="question">Question</label>:</td><td><input required type="text" name="question" id="question" /></td></tr>
			<tr><td style="text-align:center" colspan="2"><input style="width: 50%" type="submit" value="Submit" /></td></tr>
		</table>
	</form>
	<span class="err">${message_error}</span>

</body>
</html>