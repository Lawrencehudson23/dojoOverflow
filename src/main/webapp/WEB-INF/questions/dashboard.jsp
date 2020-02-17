<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Questions Dashboard</h1>

	<table class="table">
		<thead>
			<tr>
				<th scope="col">Question</th>
				<th scope="col">Tags</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${allQuestions}" var="question">
				<tr>
					<th scope="row">${question.question}</th>
					<td><c:forEach items="${question.getTags()}" var="t">
      					${t.subject}
          			</c:forEach></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<br>
	<a href="/questions/new">New Question</a>


</body>
</html>