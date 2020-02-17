<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Question</title>
</head>
<body>
	<h1>What is your question?</h1>
	<h2 style="color: green;">
		<c:out value="${success }" />
	</h2>

	<form:form action="/questions/new" method="post"
		modelAttribute="newQuestion">
		<p>
			<form:label path="question">Question</form:label>
			<form:errors path="question" />
			<form:textarea path="question" />
		</p>

		<p>
			<form:label path="tags">Tags</form:label>
			<form:errors path="tags" />
			<form:input path="tags" />
		</p>


		<input type="submit" value="Submit" />
	</form:form>
</body>
</html>