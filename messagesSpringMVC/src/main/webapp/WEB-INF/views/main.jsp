<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Comments</h1>
	<br>

	<form:form method="GET" action="add-com">
		<input type="submit" value="New msg">
	</form:form>

	<table>
		<c:forEach var="comments" items="${listComm}">
				<br>
				<tr>
					<td>Name:</td>
					<td>${comments.name}&nbsp;${comments.email}</td>
				</tr>
				<tr>
					<td>Message:</td>
					<td>${comments.comment}</td>
				</tr>
				<br>
				<tr>
					<td><a href="quote?id=${comments.id}">Quote</a></td>
				</tr>
		</c:forEach>
	</table>

</body>
</html>