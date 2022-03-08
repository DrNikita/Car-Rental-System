<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
<link href="<c:url value="${contextPath}/css/main.css" />">
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
</head>
<body>
	<div>
		<div class="imgBox">
			<img src="<c:url value="${car.getImageLink()}" />">
		</div>

		<div class="description">
			<h1>${user.getId()}</h1>
			<h2>${car.getYearOfIssue()}</h2>
			<h2>${car.getPrice()}$$</h2>
		</div>
		<form action="<c:url value="/controller" />">
			<input type="hidden" name="command" value="check_passport_data">
			<input type="submit" value="Book car">
		</form>
	</div>
</body>
</html>