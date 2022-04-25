<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resourses" />

<title><fmt:message key="title.payment" /></title>

</head>
<body>

	<table>

		<tr>
			<td><fmt:message key="title.total_price" />:</td>
			<td><c:if test="${damage==null}">
			${order.getPrice()} $$
			</c:if> <c:if test="${damage!=null}">
			${damage.getPrice()} $$
			</c:if></td>
		</tr>

		<tr>
			<td><label for="card_number"> <fmt:message
						key="title.card_number" /></label></td>
			<td><input type="number" name="card_number" id="card_number"
				required="required"></td>
		</tr>
		<tr>
			<td><label for="validity_date"> <fmt:message
						key="title.validity_date" /></label></td>
			<td><input type="number" name="validity_date"
				id="validity_month" required="required"></td>
			<td>/</td>
			<td><input type="number" name="validity_date" id="validity_year"
				required="required"></td>
		</tr>
		<tr>
			<td><label for="full_name"> <fmt:message
						key="title.full_name" /></label></td>
			<td><input type="text" name="full_name" id="full_name" required="required"></td>
		</tr>

		<tr>
			<td><c:if test="${damage==null}">
					<ctg:submitCommand key="title.pay" command="pay_order"
						method="post" />
				</c:if> <c:if test="${damage!=null}">
					<ctg:submitCommand key="title.pay" command="pay_damage"
						method="post" />
				</c:if></td>
		</tr>
	</table>
</body>
</html>