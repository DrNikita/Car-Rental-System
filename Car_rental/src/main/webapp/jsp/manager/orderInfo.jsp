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
<title><fmt:message key="title.order_info" /></title>
<link href="<c:url value="/css/orderInfo.css" />" rel="stylesheet">
</head>
<body>

	<table>

		<tr>
			<td><img
				src="<c:url value="${order.getCar().getImageLink()}" />"
				width="500px" height="500px"></td>
		</tr>

		<tr>
			<td>${order.getCar().getBrand().getModel()}</td>
		</tr>

		<tr>
			<td><fmt:message key="title.price" /> : ${order.getPrice()}$$</td>
		</tr>

		<tr>
			<c:if test="${order.getIsPaidStatus()}">
				<td><fmt:message key="title.order_is_paid" /></td>
			</c:if>
		</tr>

		<tr>
			<c:if test="${!order.getIsPaidStatus()}">
				<td><fmt:message key="title.order_is_not_paid" /></td>
			</c:if>
		</tr>

		<tr>
			<td><fmt:message key="title.rental_dates" /> :
				${order.getStartDate()} - ${order.getEndDate()}</td>
		</tr>

		<table>
			<tr>
				<td><fmt:message key="title.user_info" />:</td>
			</tr>

			<tr>
				<td>${user.getName()}${user.getSecondName()}</td>
			</tr>

			<tr>
				<td>${user.getPhone()}</td>
			</tr>

			<tr>
				<td>${user.getEmail()}</td>
			</tr>

			<tr>
				<td><fmt:message key="title.passport_number" /> :
					${user.getPassportNumber()}</td>
			</tr>

			<tr>
				<td><fmt:message key="title.passport_id" /> :
					${user.getPassportIdentificationNumber()}</td>
			</tr>
		</table>

		<tr>
			<td><ctg:submitCommand key="title.accept" command="accept_order"
					method="post" /></td>
		</tr>

		<table id="reason">
			<tr>
				<td><input type="submit" onclick="inputReason();"
					value="<fmt:message key="title.reject" />"></td>
			</tr>
		</table>

		<table id="damage">
			<tr>
				<td><input type="submit" onclick="isCarDamaged();"
					value="<fmt:message key="title.return" />"></td>
			</tr>
		</table>
	</table>

</body>

<script type="text/javascript">
	function inputReason() {

		var table = document.getElementById('reason');

		var tr = document.createElement('tr');
		var labelTr = document.createElement('tr');
		var labelTd = document.createElement('td');
		var td = document.createElement('td');
		var form = document.createElement('form');
		var inputCommand = document.createElement('input');
		var inputReason = document.createElement('input');
		var label = document.createElement('label');
		var submitInput = document.createElement('input');
		var p = document.createElement('p');

		p.setAttribute("id", "errorText");

		inputCommand.setAttribute("type", "hidden");
		inputCommand.setAttribute("name", "command");
		inputCommand.setAttribute("value", "reject_order");

		form.appendChild(inputCommand);

		label.setAttribute("for", "reason");
		label.innerHTML = '<fmt:message key="title.input_reason" />:';

		labelTd.appendChild(label);
		labelTr.appendChild(labelTd);
		form.appendChild(labelTr);

		inputReason.setAttribute("type", "text");
		inputReason.setAttribute("id", "reason");
		inputReason.setAttribute("name", "reason");

		form.appendChild(inputReason);
		form.appendChild(p);

		submitInput.setAttribute("type", "submit");
		submitInput.setAttribute("value", '<fmt:message key="title.ok"/>');

		form.appendChild(submitInput);

		form.setAttribute("action", '<c:url value="/controller"/>');
		form.setAttribute("onSubmit", "return infoValidation(this)");
		form.setAttribute("method", "post");

		td.appendChild(form);
		tr.appendChild(td);
		table.appendChild(tr);

		console.log(table);
	}

	function isCarDamaged() {

		var table = document.getElementById('damage');

		var textTr = document.createElement('tr');
		var textTd = document.createElement('td');
		var text = document.createElement('p');

		text.innerHTML = '<fmt:message key="title.is_damaged" />';

		textTd.appendChild(text);
		textTr.appendChild(textTd);
		table.appendChild(textTr);

		var tr = document.createElement('tr');

		var yesTd = document.createElement('td');
		var noTd = document.createElement('td');

		var yesForm = document.createElement('form');
		var noForm = document.createElement('form');
		yesForm.setAttribute("action", '<c:url value="/controller" />');
		yesForm.setAttribute("method", "post");
		noForm.setAttribute("action", '<c:url value="/controller" />');
		noForm.setAttribute("method", "post");

		var yesCommand = document.createElement('input');
		var noCommand = document.createElement('input');
		yesCommand.setAttribute("type", "hidden");
		noCommand.setAttribute("type", "hidden");
		yesCommand.setAttribute("name", "command");
		noCommand.setAttribute("name", "command");
		yesCommand.setAttribute("value", "to_damage_registration");
		noCommand.setAttribute("value", "return_car");
		yesForm.appendChild(yesCommand);
		noForm.appendChild(noCommand);

		var submitYes = document.createElement('input');
		var submitNo = document.createElement('input');
		submitYes.setAttribute("type", "submit");
		submitNo.setAttribute("type", "submit");
		submitYes.setAttribute("value", '<fmt:message key="title.yes" />');
		submitNo.setAttribute("value", '<fmt:message key="title.no" />');
		yesForm.appendChild(submitYes);
		noForm.appendChild(submitNo);
		yesTd.appendChild(yesForm);
		noTd.appendChild(noForm);

		tr.appendChild(yesTd);
		tr.appendChild(noTd);

		table.appendChild(tr);
	}

	function infoValidation(_form) {

		markErrorField(false);

		var errMess = "";

		if (!_form.elements["reason"].value) {
			errMess += "<fmt:message key='title.incorrect.reason' /> ";
			markErrorField(_form.elements["reason"]);
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