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

	<h1 title="Форма аренды авто">Rental dates</h1>

	<form name="BookingOrederForm" method="get"
		action="<c:url value="/controller" />">
		<input type="hidden" name="command" value="book_car"> Start
		date: <input type="date" name="startDate" required> End date:
		<input type="date" name="endDate" required> <input
			type="submit" value="Book">
	</form>
</body>
</html>