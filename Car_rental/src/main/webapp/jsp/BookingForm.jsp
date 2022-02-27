<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking form</title>
</head>
<body>
	<form name="BookingOrederForm" method="get"
		action="<c:url value="/order" />">
		<h1 title="Форма аренды авто">Rent form</h1>
		<center>
			<button type="submit">Send</button>
		</center>
	</form>
</body>
</html>