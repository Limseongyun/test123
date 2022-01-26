<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>

<layout:layout titleName="인덱스">
	인덱스 
	<button onclick="location.href='/logout'" class="btn btn-primary">로그아웃</button>
	<hr/>
	<button onclick="ajaxtest1()" class="btn btn-warning">AJAX테스트</button>
	<hr/>
	<br/>
	<br/>
	<br/>
	<c:forEach items="${merchants }" var="i">
		${i.merchantNm }
		<br/>
	</c:forEach>
<script>
function ajaxtest1(){
	var url = '/ajaxTest'
	$.get(url,function(data){
		cmm.isJSON(data)
		console.log(data)
	})
}
</script>
</layout:layout>