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
    <style>
	[type="radio"], label {
		diplay: inline-block;
	}
	table  tr td [type="radio"] + label {
		width: initial !important;
	}
	.hidden {
		display: none;
	}
	.answer {
		cursor: pointer;
	}
    </style>
    <script>
    function toggleVisibility(nb) {
    	event.preventDefault();
        var id = "q"+nb;
        console.log(id);
        Array.prototype.forEach.call(document.getElementsByClassName("answer"), function(el) {
        	el.classList.remove('hidden');
        	el.classList.add('hidden');
        });
        document.getElementById(id).classList.remove('hidden');
    }
    
    function show(nb) {
    	var id = "b"+nb;
    	document.getElementById(id).classList.remove('hidden');
    }
    </script>
</head>
<body>
	<a class="logout" href="${pageContext.request.contextPath}/logout">LOGOUT</a>
    <h1><a style="color: inherit; text-decoration: none" href="${pageContext.request.contextPath}/dashboard" title="Back">&lt; </a>Dashboard</h1>

	<h2>${quiz.getSubject()}</h2>
	<p>Created by ${quiz.getCreator()}</p>
	<span class="err">${message_error}</span>
	<form method="POST" action="finishquiz">
		<input type="hidden" name="date" value="${date}" />
		<input type="hidden" name="quiz" value="${quiz.getQuiz_id()}" />
	
		<c:set var="count" value="0" scope="page" />
		<c:forEach items="${quiz.getQuestions()}" var="question">
			<c:set var="count" value="${count + 1}" scope="page"/>
			<h3 onClick="toggleVisibility(${count})" class=question>Question n°${count}: ${question.getQuestion()}</h3>
			<div id="q${count}" class="answer<c:if test="${count != 1}"> hidden</c:if>">
				<h4>Please one answer.</h4>
				<table class="answers">
					<c:forEach items="${question.getAnswers()}" var="answer">
						<tr>
							<td><input onClick="show(${count});" type="radio" name="${question.getQuestion_id()}" value="${answer.getAnswer_id()}" id="${answer.getAnswer_id()}" />
							<label for="${answer.getAnswer_id()}">${answer.getAnswer()}</label></td>
						</tr>
					</c:forEach>
				</table>
				<c:if test="${count != quiz.getQuestions().size()}">
					<button id="b${count}" class="hidden" onClick="toggleVisibility(${count+1}); return false;">Next question</button>
				</c:if>
				<c:if test="${count == quiz.getQuestions().size()}">
					<div id="b${count}" class="submit hidden">
						<c:if test="${message_error == null || message_error.isEmpty()}"><input style="width: 50%" type="submit" value="Submit" /></c:if>
					</div>
				</c:if>
			</div>
		</c:forEach>
	</form>
</body>
</html>