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
    <h1><a style="color: inherit; text-decoration: none" href="${pageContext.request.contextPath}/dashboard/quizzes/${question.getQuiz()}" title="Back">&lt; </a>Dashboard</h1>
    
   	<h2>Question: ${question.getQuestion()}</h2>
   	
   	<h3>Current answers</h3>
       <table border="1">
       	<thead>
       		<tr>
       			<th>Order</th>
       			<th>Answer</th>
       			<th>Active?</th>
       			<th>Is correct?</th>
       			<th>Delete</th>
       		</tr>
       	</thead>
       	<c:forEach items="${answers}" var="answer">
		    <tr>
		    	<td>${answer.get_order()}</td>
		    	<td>${answer.getAnswer()}</td>
		    	<td><input type="checkbox" <c:if test="${answer.isActive()}">checked</c:if> disabled readonly></td>
		    	<td><input type="checkbox" <c:if test="${answer.isCorrect()}">checked</c:if> disabled readonly></td>
		    	<td><a href="?delete=${answer.getAnswer_id()}">Delete</a></td>
		    </tr>
		</c:forEach>
       </table>
   	
   	<h3>Add an answer</h3>
	<form method="POST" action="">
		<input required type="hidden" name="question" value="${question.getQuestion_id()}" />
		<table>
			<tr><td><label for="answer">Answer</label>:</td><td><input required type="text" name="answer" id="answer" /></td></tr>
			<tr><td><label for="correctness">Is the good answer?</label>:</td><td><input type="checkbox" name="isCorrect" id="correctness" ></td></tr>
			<tr><td style="text-align:center" colspan="2"><input style="width: 50%" type="submit" value="Submit" /></td></tr>
		</table>
	</form>
	<span class="err">${message_error}</span>

</body>
</html>