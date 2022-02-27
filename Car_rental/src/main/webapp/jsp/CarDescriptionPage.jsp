<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${car.getBrand()} ${car.getModel()}</title>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
</head>
<body>
	<div class="imgBox">
		<img src="${car.getImageLink()}">
	</div>
	<div></div>

	<h1>${car.getDescription()}</h1>
	<h2>${car.getRentalDates()}</h2>

	<c:set var="nextPage" scope="session"
		value="${contextPath}/jsp/BookingForm.jsp" />

	<c:if test="${!user.isPassportDataExists()}">
		<c:set var="nextPage" scope="session"
			value="${contextPath}/jsp/PassportDataInput.jsp" />
	</c:if>

	<a href="${nextPage}">Book</a>
</body>
</html>