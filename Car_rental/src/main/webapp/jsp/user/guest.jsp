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

<meta charset="UTF-8">
<title><fmt:message key="title.guest_title" /></title>
</head>
<link href="<c:url value="/css/main.css"/>" rel="stylesheet">
<body>

	<div class="language">
		<ctg:submitCommand key="title.en" command="set_locale">
			<input type="hidden" name="language" value="en">
		</ctg:submitCommand>

		<ctg:submitCommand key="title.ru" command="set_locale">
			<input type="hidden" name="language" value="ru">
		</ctg:submitCommand>
	</div>

	<ctg:submitCommand key="title.log_in" command="to_login_page" />

	<ctg:submitCommand key="title.registration"
		command="to_registration_page" />
</body>
</html>