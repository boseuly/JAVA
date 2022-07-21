<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 추가</title>
	<%@include file="../module/head.jsp" %>
</head>
<body>
	<section>
		<c:url var="deptAddUrl" value="./depts/add"/>
		<form  action="${deptAddUrl}" method="post">
			<div>
				<label class="input-label">부서 ID</label>
				<input type="text" name="deptId" value="${deptData.deptId}" readonly>
			</div>
			<div>
				<label class="input-label">부서명</label>
				<input type="text" name="deptName" value="${deptData.deptName}">
			</div>
			<div>
				<label class="input-label">관리자 ID</label>
				<input type="text" name="mngId" value="${deptData.mngId}">
			</div>
			<div>
				<label class="input-label">지역 ID</label>
				<input type="text" name="locId" value="${deptData.locId}">
			</div>
			<div>
				<button type="submit">저장</button>
			</div>
		</form>
	</section>
</body>
</html>