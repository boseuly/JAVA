<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>메인 화면</title>
	<%@ include file="./module/head.jsp"%>
</head>
<body>
<%@ include file="./module/navigation.jsp"%>
	<h1>메인 화면</h1>
	<hr>
	<div>
		<ul>
			<li><a href="${pageContext.request.contextPath}/dept">부서 조회</a></li>
			<li><a href="${pageContext.request.contextPath}/locs">지역 조회</a></li>
		</ul>
	</div>
</body>
</html>