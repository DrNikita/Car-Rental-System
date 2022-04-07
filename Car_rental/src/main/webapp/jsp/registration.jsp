<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>

<!DOCTYPE html>
<html>
<head>

<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resourses" />

<meta charset="UTF-8">

<title></title>
<fmt:message key="title.registration" />
<link rel="stylesheet" href="<c:url value="/css/style.css"/>">
</head>
<body>
	<div class="products" style="background-color: #E6F9E6;">
		<tr>
			<td><br></td>
			<td><br></td>
		</tr>
		<div class="tab" align="center">

			<div style="margin: 5px">

				<p id="errorText"></p>

				<ctg:submitCommand key="title.register" command="user_registration"
					submitAction="return infoValidation(this)">

					<table>
						<tr>
							<td><label for="name"><fmt:message key="title.name" />:</label>
							<td><input type="text" name="name" id="name"
								style="font-size: 15px; font-weight: normal; width: 100%">
						</tr>
						<tr>
							<td><br></td>
						</tr>
						<tr>
							<td><label for="secondName"><fmt:message
										key="title.second_name" />:</label>
							<td><input type="text" name="secondName" id="secondName"
								style="font-size: 15px; font-weight: normal; width: 100%">
						</tr>
						<tr>
							<td><br></td>
						</tr>
						<tr>
							<td><label for="email"><fmt:message
										key="title.email" />:</label>
							<td><input type="email" name="email" id="email"
								style="font-size: 15px; font-weight: normal; width: 100%">
								${existedUser}
						</tr>
						<tr>
							<td><br></td>
						</tr>
						<tr>
							<td><label for="phone"><fmt:message
										key="title.phone" />:</label>
							<td><input type="phone" name="phone" id="phone"
								style="font-size: 15px; font-weight: normal; width: 100%">
						</tr>
						<tr>
							<td><br></td>
						</tr>
						<tr>
							<td><label for="password"><fmt:message
										key="title.password" />:</label>
							<td><input type="password" name="password" id="password"
								style="font-size: 15px; font-weight: normal; width: 100%">
						</tr>
						<tr>
							<td><br></td>
						</tr>
						<tr>
							<td><label for="repeatedPassword"><fmt:message
										key="title.repeated_password" />:</label>
							<td><input type="password" name="repeatedPassword"
								id="repeatedPassword"
								style="font-size: 15px; font-weight: normal; width: 100%">
						</tr>
						<tr>
							<td><br></td>
						</tr>
					</table>
				</ctg:submitCommand>
			</div>
		</div>

		<div class="tab" style="margin-top: 10px; text-align: center">
			<a href="<c:url value="/controller?command=to_login_page" />"><fmt:message
					key="title.existing_user" /></a>
		</div>

	</div>
</body>

<script type="text/javascript">
	function infoValidation(_form) {

		markErrorField(false);

		var errMess = "";

		if (!_form.elements["name"].value) {
			errMess += "<fmt:message key='title.incorrect.name' /> ";
			markErrorField(_form.elements["name"]);
		}

		if (!_form.elements["secondName"].value) {
			errMess += "<fmt:message key='title.incorrect.second_name' /> ";
			markErrorField(_form.elements["secondName"]);
		}

		if (!_form.elements["email"].value) {
			errMess += "<fmt:message key='title.incorrect.email' /> ";
			markErrorField(_form.elements["email"]);
		}

		if (!_form.elements["phone"].value) {
			errMess += "<fmt:message key='title.incorrect.phone' /> ";
			markErrorField(_form.elements["phone"]);
		}

		if (!_form.elements["password"].value) {
			errMess += "<fmt:message key='title.incorrect.password' /> ";
			markErrorField(_form.elements["password"]);

		} else if (!_form.elements["repeatedPassword"].value) {
			errMess += "<fmt:message key='title.incorrect.repeated_password' /> ";
			markErrorField(_form.elements["repeatedPassword"]);

		} else if (_form.elements["password"].value != _form.elements["repeatedPassword"].value) {
			errMess += "<fmt:message key='title.incorrect.password_match' /> ";
			markErrorField(_form.elements["password"]);
			markErrorField(_form.elements["repeatedPassword"]);
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