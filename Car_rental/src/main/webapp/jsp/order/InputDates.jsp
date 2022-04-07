<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>

<!DOCTYPE html>

<html>
<head>

<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resourses" />

<script type="text/javascript">
	function infoValidation(_form) {

		markErrorField(false);

		var errMess = "";

		if (!_form.elements["startDate"].value) {
			errMess += "<fmt:message key='title.incorrect.dates' /> ";
			markErrorField(_form.elements["startDate"]);

		} else if (!_form.elements["endDate"].value) {
			errMess += "<fmt:message key='title.incorrect.dates' /> ";
			markErrorField(_form.elements["endDate"]);
		}

		if (errMess) {
			var err = document.getElementById("errorText");
			err.innerHTML = errMess;
			return false;
		}
	}
	function markErrorField(_element) {

		var allLabels = document.getElementsByTagName("LABEL");

		if (_element) {
			for (var i = 0; i < allLabels.length; i++) {
				if (allLabels[i].htmlFor == _element.id)
					allLabels[i].style.color = "red";
			}

		} else {
			for (var i = 0; i < allLabels.length; i++) {
				allLabels[i].style.color = "black";
			}
		}
	}
</script>

<meta charset="UTF-8">
<title><fmt:message key="title.rental_dates" /></title>
</head>
<body>

	<h1>
		<fmt:message key="title.rental_dates" />
		:
	</h1>

	<p id="errorText"></p>

	<ctg:submitCommand key="title.book" command="book_car"
		submitAction="return infoValidation(this)">

		<table>
			<tr>
				<td><label for="startDate"><fmt:message
							key="title.start_date" />:</label>
				<td><input type="date" name="startDate" id="startDate"
					style="font-size: 15px; font-weight: normal; width: 100%">
			</tr>
			<tr>
				<td><br></td>
			</tr>
			<tr>
				<td><label for="endDate"><fmt:message
							key="title.end_date" />:</label>
				<td><input type="date" name="endDate" id="endDate"
					style="font-size: 15px; font-weight: normal; width: 100%">
			</tr>
			<tr>
				<td><br></td>
			</tr>
		</table>

	</ctg:submitCommand>

</body>
</html>