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

<script type="text/javascript">
	function infoValidation(_form) {

		markErrorField(false);

		var errMess = "";

		if (!_form.elements["passportNumber"].value) {
			errMess += "<fmt:message key='title.incorrect.passport_number' /> ";
			markErrorField(_form.elements["passportNumber"]);
		}

		if (!_form.elements["passportIdNumber"].value) {
			errMess += "<fmt:message key='title.incorrect.passport_id_number' /> ";
			markErrorField(_form.elements["passportIdNumber"]);
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
<title><fmt:message key="title.input_passpotr_data" /></title>
</head>
<body>

	<p id="errorText"></p>

	<ctg:submitCommand key="title.ok" command="add_passport_data"
		submitAction="return infoValidation(this)">

		<table>

			<tr>
				<td><label for="passportNumber"><fmt:message
							key="title.passport_number" />:</label>
				<td><input type="text" name="passportNumber"
					id="passportNumber" style="font-size: 15px; font-weight: normal;">
			</tr>

			<tr>
				<td><label for="passportIdNumber"><fmt:message
							key="title.passport_id" />:</label>
				<td><input type="text" name="passportIdNumber"
					id="passportIdNumber" style="font-size: 15px; font-weight: normal;">
			</tr>

		</table>

	</ctg:submitCommand>

</body>
</html>