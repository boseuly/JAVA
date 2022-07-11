<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.*, dept.model.DeptDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 조회 결과</title>
	<%@ include file="../module/head.jsp" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/default.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/navigation.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/required.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/form.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/table.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/paging.css">
	<script type="text/javascript" src="./static/js/required.js"></script>
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<h1>부서 조회 결과</h1>
	<div>
		<button type="button" onclick="location.href='./depts/add'">추가</button>
	</div>
	<div>
		<form action="./depts" method="get">
			<div>
				<input type="text" name="search" data-required="부서코드를 입력하세요.">
				<button type="submit">조회</button>
			</div>
			<div class="input-form form-right">
				<select class="select-form" onchange="location.href='./depts?pgc=' + this.value"> <!-- 선택이 되면 바뀌도록 설정해둠 여기서 this.는 select 태크 요소를 지칭 -> select요소에서 value라는 값 -->
					<option value="5" ${sessionScope.pageCount == 5 ? 'selected' : ''}>5개</option>				<!--  pgc 파라미터에 value 값 저장 -->
					<option value="10" ${sessionScope.pageCount == 10 ? 'selected' : ''}>10개</option>	<!--  이거 왜 pageCount가 10개면  -->
					<option value="15" ${sessionScope.pageCount == 15 ? 'selected' : ''}>15개</option>
					<option value="20" ${sessionScope.pageCount == 20 ? 'selected' : ''}>20개</option>
				</select>
			</div>
		</form>
	</div>
	<table>
		<tr>
			<th>DeptId</th>
			<th>DeptName</th>
			<th>MngId</th>
			<th>LocId</th>
			<th></th>
		</tr>
		
		<c:if test="${not empty deptDatas}"> <!-- 설정했을 때의 속성명 -->
		
			<c:forEach items="${deptDatas}" var="data">
					<tr>
					<td>${data.deptId}</td>
					<td>${data.deptName}</td>
					<td>${data.mngId}></td>
					<td><a href="./locs?search=${data.locId}">${data.locId}</a></td>
					<td>
						<c:url var="modUrl" value="./depts/mod">
							<c:param name="id" value="${data.deptId}"></c:param>
						</c:url>
						<button type="button" onclick="location.href='${modUrl}'">수정</button>
						<c:url var="delUrl" value="./depts/del">
							<c:param name="id" value="${data.deptId}"></c:param>
						</c:url>
						<button type="button" onclick="location.href='${delUrl}'">삭제</button>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	<c:choose>
		<c:when test="${not empty pageList}">
			<c:url var="pageUrl" value="./depts"/>
			<%@include file="../module/paging.jsp" %>
		</c:when>	
		<c:otherwise>
			<div class="input-form wide form-left">
					<button class="btn btn-outline" onclick="'${pageContext.request.contextPath}/depts'">돌아가기</button> 
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>