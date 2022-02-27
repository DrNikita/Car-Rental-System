<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>main</title>
<link href="style.css" rel="stylesheet">
</head>
<body>
	<c:forEach var="car" items="${cars}" varStatus="status">
		<div class="card">
			<div class="imgBox">
				<img src="${car.getImageLink()}">
			</div>
			<div class="contentBox">
				<h3>
					<p>${car.getBrand()}${car.getModel()}</p>
				</h3>
				<a href="<c:url value="/carDescription?carId=${car.getId()}"/>">More</a>
			</div>
		</div>
	</c:forEach>
</body>
</html>