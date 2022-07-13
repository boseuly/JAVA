<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="locs.model.*, java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>지역 조회</title>
	<%@ include file="../module/head.jsp"%>
</head>
<body>
<c:url value="./locs" var="pageUrl"/> <!-- url 변수 지정하기 -->
	<%@include file="../module/navigation.jsp"%>
	<div>
		<button type="button" onclick="location.href='${pageContext.request.contextPath}/locs/add'">지역 추가</button>
	</div>
	<section>
		<form action="${pageUrl}" method="get">
			<input type="text" name="search" data-required="지역 ID를 입력하세요.">
			<button type="submit">조회</button>
		</form>
		<table>
			<thead>
				<tr>
					<th>지역ID</th>
					<th>주소</th>
					<th>우편</th>
					<th>시</th>
					<th>주</th>
					<th>국가</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty datas}"> <!-- datas가 존재한다면 -->
					<c:forEach items="${datas}" var="data">
							<tr>
								<td>${data.locId}</td>
								<td>${data.stAddr}</td>
								<td>${data.postal}</td>
								<td>${data.city}</td>
								<td>${data.state}</td>
								<td>${data.ctyId}</td>
							</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</section>
	<c:choose>
		<c:when test="${not empty pageList}"> <!-- 해당 pageList라는 -->
			<%@include file="../module/paging.jsp" %> <!-- 페이지 리스트이다. -->
		</c:when>
		<c:otherwise> 
			<div>
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/locs'">전체보기</button>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>