<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${question.question}</h1>

	<h2>
		Tags:
		<c:forEach items="${qt}" var="tag">
	${tag.subject}
	</c:forEach>


	</h2>
	<table>
		<thead>
			<tr>
				<th scope="col">Answers</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<c:forEach items="${answers}" var="answer">
					<th scope="row">${answer.answer}</th>
				</c:forEach>
			</tr>
		</tbody>
	</table>
	
	
			<form:form action="/questions/${question.id}" method="post" modelAttribute="newAnswer">

		<p>
			<form:label path="answer">Answer:</form:label>
			<form:errors path="answer" />
			<form:textarea path="answer" />
		</p>



		<input type="submit" value="Answer it!!" />

		</form:form>
</html>