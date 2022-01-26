<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>

<layout:layout titleName="loginPage">
로그인!
<div>
	<form action="/login" method="post">
		<label for="id">ID</label>
		<input id="id" name="username">
		<label for="pw">PW</label>
		<input id="pw" name="password">
		
		<button type="submit" class="btn btn-info">login</button>
		<hr>
		username, password 를 변경하시 마세요.
	</form>
</div>
</layout:layout>