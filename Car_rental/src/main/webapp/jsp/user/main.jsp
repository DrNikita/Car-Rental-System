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
<title><fmt:message key="title.main" /></title>
<link href="<c:url value="/css/main.css" />" rel="stylesheet">
</head>
<body>

	<ctg:submitCommand key="title.log_out" command="logout" />
	<br>
	<br>

	<div class="goods">
		<c:forEach var="car" items="${cars}" varStatus="status">
			<div class="good">
				<div class="image">
					<img src="<c:url value="${car.getImageLink()}"/>" width="350px"
						height="350px">
				</div>

				<div class="cars">
					<ctg:submitCommand key="title.more" command="car_description_page">
						<input type="hidden" name="carId" value="${car.getId()}">
					</ctg:submitCommand>
				</div>
			</div>
		</c:forEach>
		<ctg:submitCommand key="title.cart" command="user_orders" />
	</div>
</body>
</html>