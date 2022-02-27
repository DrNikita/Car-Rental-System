<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Passport data</title>
</head>
<body>
	<div class="products" style="background-color: #E6F9E6;">

		<p class="tab" align="center" style="color: brown;" id="message">Login
			Here</p>
		<br>
		<div class="tab" align="center">

			<div style="margin: 5px">

				<form action="/passportData" method="post">
					Passport number: <input type="text" name="passportNumber"
						style="font-size: 15px; font-weight: normal;"
						placeholder="Enter Email-Id" required><br /> <br />
					Passport identification number: <input type="text"
						name="passportIdentificationNumber"
						style="font-size: 15px; font-weight: normal;"
						placeholder="Enter Password" required><br /> <br />
				</form>
			</div>
		</div>
		<div class="tab" style="margin-top: 10px; text-align: center">
			<a href="<c:url value="/passportData" />">Next</a>
		</div>
	</div>
</body>
</html>