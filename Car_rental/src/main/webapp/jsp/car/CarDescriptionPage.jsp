<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>

<!DOCTYPE html>
<html>
<head>

<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resourses" />

<meta charset="UTF-8">
<title><fmt:message key="title.car_description" /></title>
<link href="<c:url value="${contextPath}/css/main.css" />">
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
</head>
<body>
	<div>
		<div class="imgBox">
			<img src="<c:url value="${car.getImageLink()}" />">
		</div>
		<div class="description">
			<h1>${car.getYearOfIssue()}</h1>
			<h1>${car.getPrice()}$$</h1>
		</div>

		<c:if test="${user.isPassportDataExists()}">
			<ctg:submitCommand key="title.book" command="to_input_dates" />
		</c:if>

		<c:if test="${!user.isPassportDataExists()}">
			<ctg:submitCommand key="title.book" command="to_passport_data_input" />
		</c:if>

	</div>
</body>
</html>