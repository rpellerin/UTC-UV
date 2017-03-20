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
    <h1><a style="color: inherit; text-decoration: none" href=".." title="Back">&lt; </a>Dashboard</h1>

	<h2>${quiz.getSubject()}</h2>
	<o>Created by ${quiz.getCreator()}</p>
	<h1 class="score">SCORE: ${score}%</h1>
	<h1>DURATION: ${duration} seconds</h1>
	<c:forEach items="${quiz.getQuestions()}" var="question">
		<h3 class=question>Question: ${question.getQuestion()}</h3>
		<div class="answer">
			<table class="answers">
				<thead>
					<tr>
						<td>Your answer</td>
						<td>Correct answer</td>
						<td>Statement</td>
					</tr>
				</thead>
				<c:forEach items="${question.getAnswers()}" var="answer">
					<tr class="${(user_answers.get(answer.getQuestion()) == answer.getAnswer_id() && answer.isCorrect()) || (user_answers.get(answer.getQuestion()) != answer.getAnswer_id() && !answer.isCorrect()) ? 'greenRow' : 'redRow'}">
						<td><input type="checkbox" read-only disabled <c:if test="${user_answers.get(answer.getQuestion()) == answer.getAnswer_id()}">checked</c:if> /></td>
						<td><input type="checkbox" read-only disabled <c:if test="${answer.isCorrect()}">checked</c:if> /></td>
						<td><label>${answer.getAnswer()}</label></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:forEach>
</body>
</html>