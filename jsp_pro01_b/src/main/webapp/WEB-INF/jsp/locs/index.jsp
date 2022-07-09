<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, locs.model.LocsDTO" %>
<%@ page import="java.util.*, dept.model.DeptDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>지역 조회 결과</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/default.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/navigation.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/required.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/form.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/table.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/paging.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/required.js"></script>
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<h1>지역 조회 결과</h1>
	<div>
		<form action="./locs" method="get">
			<div>
				<button type="button" onclick="location.href='./locs/add'">지역추가</button>
			</div>
			<div>
				<input type="text" name="search" data-required="지역코드를 입력하세요.">
				<button type="submit">조회</button>
			</div>
		</form>
	</div>
	<table>
		<tr>
			<th>LocId</th>
			<th>StAddr</th>
			<th>Postal</th>
			<th>City</th>
			<th>State</th>
			<th>CtyId</th>
		</tr>
	<c:if test="${not empty locsDatas}">
		<c:forEach var="data" items="${locsDatas}">
			<td>${data.locId}</td>
			<td>${data.stAddr}</td>
			<td>${data.postal}</td>
			<td>${data.city}</td>
			<td>${data.state}</td>
			<td>${data.ctyId}</td>
			<c:url var="modUrl" value="./locs/mod">
				<c:param name="id" value="${data.locId}"/>
			</c:url>
			<td><button type="button" onclick="location.href='${modUrl}'">수정</button></td>
			<c:url var="delUrl" value="./locs/del">
				<c:param name="id" value="${data.locId}"/>
			</c:url>
			<td><button type="button" onclick="location.href='${delUrl}'">삭제</button></td>	
		</c:forEach>
	</c:if>
	</table>
</body>
</html>