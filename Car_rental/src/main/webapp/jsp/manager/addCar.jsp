<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>

<!DOCTYPE html>
<html>
<head>

<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resourses" />

<meta charset="UTF-8">
<title><fmt:message key="title.add" /></title>
</head>
<body>

	<p id="errorText"></p>

	<ctg:submitCommand key="title.add" command="add_car"
		submitAction="return infoValidation(this)" method="post">

		<table>
			<tr>
				<td><label for="brand"><fmt:message
							key="title.car.brand" />:</label>
				<td><input type="text" name="brand" id="brand"
					style="font-size: 15px; font-weight: normal; width: 100%">
			</tr>
			<tr>
				<td><label for="model"><fmt:message
							key="title.car.model" />:</label>
				<td><input type="text" name="model" id="model"
					style="font-size: 15px; font-weight: normal; width: 100%">
			</tr>
			<tr>
				<td><label for="year"><fmt:message key="title.car.year" />:</label>
				<td><input type="text" name="year" id="year"
					style="font-size: 15px; font-weight: normal; width: 100%">
			</tr>
			<tr>
				<td><label for="price"><fmt:message
							key="title.car.price" />:</label>
				<td><input type="text" name="price" id="price"
					style="font-size: 15px; font-weight: normal; width: 100%">
			</tr>
			<tr>
				<td><label for="image"><fmt:message
							key="title.car.image" />:</label>
				<td><input type="text" name="image" id="image"
					style="font-size: 15px; font-weight: normal; width: 100%">
			</tr>
		</table>

	</ctg:submitCommand>

</body>

<script type="text/javascript">
	function infoValidation(_form) {

		markErrorField(false);

		var errMess = "";

		if (!_form.elements["brand"].value) {
			errMess += "<fmt:message key='title.incorrect.brand' /> ";
			markErrorField(_form.elements["brand"]);
		}

		if (!_form.elements["model"].value) {
			errMess += "<fmt:message key='title.incorrect.model' /> ";
			markErrorField(_form.elements["model"]);
		}

		if (!_form.elements["year"].value) {
			errMess += "<fmt:message key='title.incorrect.year' /> ";
			markErrorField(_form.elements["year"]);
		}

		if (isNaN(_form.elements["year"].value)) {
			errMess += "<fmt:message key='title.incorrect.year' /> ";
			markErrorField(_form.elements["year"]);
		}

		if (!_form.elements["price"].value) {
			errMess += "<fmt:message key='title.incorrect.price' /> ";
			markErrorField(_form.elements["price"]);
		}

		if (isNaN(_form.elements["price"].value)) {
			errMess += "<fmt:message key='title.incorrect.price' /> ";
			markErrorField(_form.elements["price"]);
		}

		if (!_form.elements["image"].value) {
			errMess += "<fmt:message key='title.incorrect.image' /> ";
			markErrorField(_form.elements["image"]);

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

</html>