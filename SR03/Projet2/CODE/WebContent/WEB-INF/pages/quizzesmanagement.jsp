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
    
   	<h2>Current quizzes</h2>
      <table border="1">
      	<thead>
      		<tr>
      			<th>Subject</th>
      			<th>Active?</th>
      			<th>Author</th>
      			<th>Edit</th>
      			<th>Delete</th>
      		</tr>
      	</thead>
      	<c:forEach items="${quizzes}" var="quiz">
	    <tr>
	    	<td>${quiz.getSubject()}</td>
	    	<td><input type="checkbox" <c:if test="${quiz.isActive()}">checked</c:if> disabled readonly></td>
	    	<td>${quiz.getCreator()}</td>
	    	<td><a href="${pageContext.request.contextPath}/dashboard/quizzes/${quiz.getQuiz_id()}">Edit</a></td>
	    	<td><a href="?delete=${quiz.getQuiz_id()}">Delete</a></td>
	    </tr>
	</c:forEach>
      </table>
       
      <div>
      <c:forEach begin="1" end="${countPages}" varStatus="loop">
      		<a <c:if test="${loop.index == currentPage}">style="font-size: 1.5em"</c:if> href="?page=${loop.index}">${loop.index}</a>
      </c:forEach>
      </div>
       
	<h2>Create a new quiz</h2>
		<form method="POST" action="">
			<table>
				<tr><td><label for="subject">Subject</label>:</td><td><input required type="text" name="subject" id="subject" /></td></tr>
				<tr><td style="text-align:center" colspan="2"><input style="width: 50%" type="submit" value="Submit" /></td></tr>
			</table>
		</form>
		<span class="err">${message_error}</span>
</body>
</html>