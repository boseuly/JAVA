<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>직원 페이지</title>
	<%@include file="../module/head.jsp"%>
</head>
<body>
	<%@include file="../module/navigation.jsp" %>
	<section>
		<c:url value="./emps" var="pageUrl"/>
		<div>
			<button type="button" onclick="location.href='./emps/add'">직원 추가</button>
		</div>
		<div>
			<form action="${pageUrl}" method="post">
				<div>
					<input type="text" >
				</div>
			</form>
		</div>
	</section>
</body>
</html>