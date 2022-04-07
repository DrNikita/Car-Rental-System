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
<title><fmt:message key="title.damage" /></title>
</head>
<body>

	<p id="errorText"></p>

	<ctg:submitCommand key="title.register_damage"
		command="register_damage" submitAction="return infoValidation(this)">

		<table>

			<tr>
				<td><label for="description"><fmt:message
							key="title.damage_description" />:</label></td>
				<td><input type="text" name="description" id="description"></td>
			</tr>

			<tr>
				<td><label for="price"><fmt:message key="title.price" />:</label></td>
				<td><input type="text" name="price" id="price"></td>
			</tr>

		</table>

	</ctg:submitCommand>

</body>

<script type="text/javascript">
	function infoValidation(_form) {

		markErrorField(false);

		var errMess = "";

		if (!_form.elements["description"].value) {
			errMess += "<fmt:message key='title.incorrect.damage_description' /> ";
			markErrorField(_form.elements["description"]);
		}

		if (!_form.elements["price"].value) {
			errMess += "<fmt:message key='title.incorrect.price' /> ";
			markErrorField(_form.elements["price"]);

		} else if (isNaN(_form.elements["price"].value)) {
			errMess += "<fmt:message key='title.incorrect.price' /> ";
			markErrorField(_form.elements["price"]);
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