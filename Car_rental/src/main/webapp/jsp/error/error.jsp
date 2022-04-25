<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>

<!DOCTYPE html>
<html>
<head>

<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resourses" />

<meta charset="UTF_8">

<title><fmt:message key="title.error" /></title>

</head>
<body>

	<table>

		<tr>
			<td><fmt:message key="title.error.application_logic" /></td>
		</tr>

		<c:if test="${servletName!=null}">
			<tr>
				<td><fmt:message key="title.error.servlet_name" /></td>
				<td>${servletName}</td>
			</tr>
		</c:if>

		<tr>
			<td><fmt:message key="title.error.exception" /></td>
			<td>${exception}</td>
		</tr>

	</table>

</body>
</html>