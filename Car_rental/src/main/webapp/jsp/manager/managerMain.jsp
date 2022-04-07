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
<link href="<c:url value="/css/orders.css" />" rel="stylesheet">
</head>

<body>

	<ctg:submitCommand key="title.log_out" command="logout" />
	<br>
	<br>

	<div class="orders">

		<c:forEach var="order" items="${orders}" varStatus="status">

			<div class="order">

				<img src="<c:url value="${order.getCar().getImageLink()}"/>"
					width="350px" height="350px"> <br>${order.getCar().getBrand().getBrand()}
				${order.getCar().getBrand().getModel()} <br>${order.getConfirmationStatus()}

				<ctg:submitCommand key="title.order_info"
					command="order_information">
					<input type="hidden" name="orderId" value="${order.getId()}">
				</ctg:submitCommand>

			</div>
		</c:forEach>

		<ctg:submitCommand key="title.goods" command="get_goods" />
	</div>
</body>
</html>