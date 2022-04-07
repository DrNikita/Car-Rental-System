<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF_8">
<title><fmt:message key="title.error" /></title>
</head>
<body>
	Request from ${pageContext.errorData.requestURI} is failed
	<br /> Servlet name or type: ${pageContext.errorData.servletName}
	<br /> Status code: ${pageContext.errorData.statusCode}
	<br /> Exception: ${error}
</body>
</html>