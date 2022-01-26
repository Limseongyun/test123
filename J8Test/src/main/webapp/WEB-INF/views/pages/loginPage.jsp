<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
로그인!
<div>
	<form action="/login" method="post">
		<label for="id">ID</label>
		<input id="id" name="username">
		<label for="pw">PW</label>
		<input id="pw" name="password">
		
		<button type="submit">login</button>
		<hr>
		username, password 를 변경하시 마세요.
	</form>
</div>
</body>
</html>