<%@ tag language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="titleName" required="true" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>${titleName }</title>
	<link rel="stylesheet" href="/webjars/jquery-ui/themes/base/jquery-ui.min.css">
	<link rel="stylesheet" href="/webjars/bootstrap/5.1.3/dist/css/bootstrap.min.css">
	<script src="/webjars/jquery/3.6.0/dist/jquery.min.js"></script>
	<script src="/webjars/bootstrap/5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="/webjars/jquery-ui/jquery-ui.min.js"></script>
</head>
<body>
	
	<jsp:doBody/>
	
</body>
</html>