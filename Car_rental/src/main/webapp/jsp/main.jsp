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
<link href="<c:url value="/css/main.css"/>" rel="stylesheet">
</head>
<body>
	<c:forEach var="car" items="${cars}" varStatus="status">
		<div class="card">
			<div class="imgBox">
				<img src="<c:url value="${car.getImageLink()}"/>">
			</div>
			<div class="contentBox">
				<h3>
					<p></p>
				</h3>
				<form action="<c:url value="/controller"/>">
					<input type="hidden" name="command" value="car_description_page">
					<input type="hidden" name="carId" value="${car.getId()}"> <input
						type="submit" value="More">
				</form>
			</div>
		</div>
	</c:forEach>
</body>
</html>