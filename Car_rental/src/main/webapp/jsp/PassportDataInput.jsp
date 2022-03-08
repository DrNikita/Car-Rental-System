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
	<div style="margin: 5px">

		<form action="<c:url value="/controller"/>">
			<input type="hidden" name="command" value="add_passport_data">
			Passport number: <input type="text" name="passportNumber"
				style="font-size: 15px; font-weight: normal;" required><br />
			<br /> Passport identification number: <input type="text"
				name="passportIdNumber"
				style="font-size: 15px; font-weight: normal;" required><br />
			<br /> <input type="submit" value="Next">
		</form>
	</div>

	<div class="tab" style="margin-top: 10px; text-align: center"></div>

</body>
</html>