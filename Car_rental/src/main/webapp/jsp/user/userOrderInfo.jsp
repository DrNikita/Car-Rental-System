<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>

<!DOCTYPE html>
<html>

<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resourses" />

<head>
<meta charset="UTF-8">
<title><fmt:message key="title.info" /></title>
</head>
<body>

	<table>

		<tr>
			<td><img
				src="<c:url value="${order.getCar().getImageLink()}" />"
				width="500px" height="500px">
				${order.getCar().getBrand().getBrand()}
				${order.getCar().getBrand().getModel()}</td>
		</tr>

		<tr>
			<td><fmt:message key="title.price" /> : ${order.getPrice()}$$</td>
		</tr>

		<tr>
			<td><fmt:message key="title.rental_dates" /> :
				${order.getStartDate()} - ${order.getEndDate()}</td>
		</tr>

		<tr>
			<td><fmt:message key="title.confirmation_status" /> : <c:if
					test="${order.getConfirmationStatus().ordinal()==1}">
					<fmt:message key="title.order.confirmed" />
				</c:if></td>
		</tr>

		<tr>
			<td><c:if test="${order.getConfirmationStatus().ordinal()==0}">
					<fmt:message key="title.order.concidered" />
				</c:if></td>
		</tr>

		<tr>
			<td><c:if test="${order.getConfirmationStatus().ordinal()==2}">
					<fmt:message key="title.order.rejected" />;
				${order.getRejectionReason()}.
			</c:if></td>
		</tr>

		<tr>
			<td><c:if test="${!order.getIsPaidStatus()}">
					<c:if test="${order.isConfirmed()}">
						<ctg:submitCommand key="title.pay" command="to_payment_making" />
					</c:if>
				</c:if></td>
		</tr>

	</table>

</body>
</html>