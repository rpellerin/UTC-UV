<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link rel="stylesheet" media="all" href="${pageContext.request.contextPath}/resources/css/global.css" />
    <title>Lancer une requête</title>
</head>
<body class=flex-container>
	<div class="left">
		<h1><a href="home">Accueil</a></h1>
		<form action="home" method="POST">
		<label for="request">Requête :</label><textarea id="request" name="request" rows="5" cols="30">${request}</textarea>
		<input type="submit" value="Recherche"><input type="reset" value="Reset">
		</form>
		<c:if test="${null != suggestions}">
			<h3>Suggestions</h3>
			<ul>
			<c:forEach items="${suggestions}" var="suggestion">
				<c:set var="string1" value="${fn:replace(suggestion, '<strong>', '')}"/>
				<c:set var="string2" value="${fn:replace(string1, '</strong>', '')}" />
				<li><a href="home?request=${string2}">${suggestion}</a></li> 
	     	</c:forEach>
		     </ul>
		</c:if>
	</div>
	
	<div class="right">
		<h2>Stacktrace</h2>
			
			<p class="stack">
				<c:forEach items="${stacktrace}" var="line">
					<span class="line
						<c:if test="${line.isErrorLine()}">
							 error
						</c:if>
						">
						${line.getLine()}
					</span>
		     	</c:forEach>
			</p>
		
		<c:if test="${null != results_headers}">
			<h2>Résultats</h2>
			
			<table border="1">
		     	<thead>
		     		<tr>
		     			<c:forEach items="${results_headers}" var="results_header">
		     				<th>${results_header}</th>
		     			</c:forEach>
		     		</tr>
		     	</thead>
			    <c:forEach items="${results}" var="line">
					<tr>
						<c:forEach items="${line}" var="cell">
							<td>
								<c:choose>
								    <c:when test="${fn:containsIgnoreCase(cell, '.htm')}">
								       <a href="BULLETINS/${cell}" target="_blank">
								       		${cell}
								       </a>
								    </c:when>
								    <c:otherwise>
								        ${cell}
								    </c:otherwise>
								</c:choose>
							</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>