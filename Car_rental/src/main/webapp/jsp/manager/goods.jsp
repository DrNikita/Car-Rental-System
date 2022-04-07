<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resourses" />

<title><fmt:message key="title.goods" /></title>
</head>
<body>

	<ctg:submitCommand key="title.add" command="to_add_page" />

	<c:forEach var="goods" items="${cars}" varStatus="status">
		<div class="image">
			<img src="<c:url value="${goods.getImageLink()}"/>" width="350px"
				height="350px">
		</div>
		<div>${goods.getBrand().getBrand()}${goods.getBrand().getModel()}
		</div>
		<div>

			<ctg:submitCommand key="title.info" command="goods_info">
				<input type="hidden" name="carId" value="${goods.getId()}">
			</ctg:submitCommand>
		</div>
	</c:forEach>
</body>
</html>