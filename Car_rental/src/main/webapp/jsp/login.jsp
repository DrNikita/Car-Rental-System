<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Car rental</title>
<link rel="stylesheet" href="css/changes.css">
</head>
<body>

	<div class="header">
		<h1>Car rental</h1>
	</div>

	<form action="<c:url value="/controller" />">

		<input type="hidden" name="command" value="login" /> Email: <br /> <input
			type="text" name="email" value="" /> <br /> Password: <br /> <input
			type="password" name="password" value="" /> <br />
		${errorLoginPassMessage} <br /> ${wrongAction} <br /> ${nullPage} <br />

		<input type="submit" value="Log in" />
	</form>
	<hr />
</body>
</html>