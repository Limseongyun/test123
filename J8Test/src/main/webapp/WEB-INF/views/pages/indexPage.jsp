<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>INDEX</title>
</head>
<body>
인덱스 
<button onclick="location.href='/logout'">로그아웃</button>
<hr>

<c:forEach items="${merchants }" var="i">
	${i.merchantNm }
	<br/>
</c:forEach>
</body>

</html>