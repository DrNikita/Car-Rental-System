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
<title><fmt:message key="title.app_name" /></title>
<link rel="stylesheet" href="<c:url value="/css/login.css" />">

</head>
<body>

	${registrationError}

	<table>
		<tr>
			<td><ctg:submitCommand key="title.en" command="set_locale">
					<input type="hidden" name="language" value="en">
				</ctg:submitCommand>
			<td><ctg:submitCommand key="title.ru" command="set_locale">
					<input type="hidden" name="language" value="ru">
				</ctg:submitCommand>
		<tr>
	</table>

	<tr>
		<h1>
			<td><fmt:message key="title.app_name" />
		</h1>
	<tr>

		<table>
			<tr>
				<td><ctg:submitCommand key="title.log_in" command="login">

						<table>

							<tr>
								<td><fmt:message key="title.email" /> :
								<td><input type="text" name="email" />
							</tr>

							<tr>
								<td><fmt:message key="title.password" /> :
								<td><input type="password" name="password" />
							</tr>

							<table>
								<tr>
									<td>${errorLoginPassMessage}
								<tr>
									<td>${wrongAction}
								<tr>
									<td>${nullPage}
							</table>

						</table>

					</ctg:submitCommand>
				<td><ctg:submitCommand key="title.registration"
						command="to_registration_page" />
			</tr>
		</table>
</body>
</html>