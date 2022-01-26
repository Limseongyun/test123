<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>

<layout:layout titleName="error">
error page,
errorCode: ${errCode }
<br/>
exType: ${exType }
<br/>
exMsg: ${exMsg }
</layout:layout>