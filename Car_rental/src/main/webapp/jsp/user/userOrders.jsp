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
<title><fmt:message key="title.cart" /></title>
</head>
<body>
	<div class="orders">
		<c:forEach var="order" items="${orders}" varStatus="status">
			<div class="good">
				<div class="image">
					<img src="<c:url value="${order.getCar().getImageLink()}"/>"
						width="500px" height="500px">
				</div>

				<div class="order">
					<c:if test="">
						<form action="<c:url value="/controller"/>">
							<input type="hidden" name="command" value="pay_order"> <input
								type="hidden" name="carId" value="${car.getId()}"> <input
								type="submit" value="<fmt:message key="title.pay"/>">
						</form>
					</c:if>

					<ctg:submitCommand key="title.info" command="user_order_info">
						<input type="hidden" name="orderId" value="${order.getId()}">
					</ctg:submitCommand>

					<ctg:submitCommand key="title.cancel" command="cancel_order">
						<input type="hidden" name="orderId" value="${order.getId()}">
					</ctg:submitCommand>

				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>