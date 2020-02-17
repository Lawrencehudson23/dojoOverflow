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
	<table class="table">
  <thead>
    <tr>
      <th scope="col">Question</th>
      <th scope="col">Tags</th>
    </tr>
  </thead>
  <tbody>
    <tr>
    <c:forEach items="${allQuestions}" var="question">
      <th scope="row">${question.question}</th>
      <td>${question.getTags()}</td>
     </c:forEach>
    </tr>
  </tbody>
</table>
</body>
</html>