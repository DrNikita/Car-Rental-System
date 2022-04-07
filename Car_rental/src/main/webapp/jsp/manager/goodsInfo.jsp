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
<title>Insert title here</title>
</head>
<body>
	<div>
		<ctg:submitCommand key="title.change"
			command="goods_info_modification">
			<input type="hidden" name="parameter" value="brand_model">
			<input type="text" name="brand" required>
			<input type="text" name="model" required>
		</ctg:submitCommand>
	</div>

	<div>
		<ctg:submitCommand key="title.change"
			command="goods_info_modification">
			<input type="hidden" name="parameter" value="year">
			<input type="text" name="year">
		</ctg:submitCommand>
	</div>

	<div>
		<ctg:submitCommand key="title.change"
			command="goods_info_modification">
			<input type="hidden" name="parameter" value="price">
			<input type="text" name="price">
		</ctg:submitCommand>
	</div>

	<div>
		<ctg:submitCommand key="title.change"
			command="goods_info_modification">
			<input type="hidden" name="parameter" value="image">
			<input type="text" name="image" value="/images/cars/">
		</ctg:submitCommand>
	</div>
</body>
</html>