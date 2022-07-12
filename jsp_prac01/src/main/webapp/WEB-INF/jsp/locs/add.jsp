<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="../module/navigation.jsp" %>
	<div>
		<form action="${pageContext.request.contextPath}/locs/add" method="post">
			<div>
				<label>지역ID</label>
			</div>
			<div>
				<input type="text" name="locId" data-required="지역ID를 입력하세요." >
			</div>
			<div>
				<label>주소</label>
			</div>
			<div>
				<input type="text" name="stAddr" data-required="주소를 입력하세요.">
			</div>
			<div>
				<label>우편</label>
			</div>
			<div>
				<input type="text" name="postal" data-required="우편을 입력하세요.">
			</div>
			<div>
				<label>시</label>
			</div>
			<div>
				<input type="text" name="city" data-required="시를 입력하세요.">
			</div>
			<div>
				<label>주</label>
			</div>
			<div>
				<input type="text" name="state" data-required="주를 입력하세요.">
			</div>
			<div>
				<label>국가</label>
			</div>
			<div>
				<input type="text" name="ctyId" data-required="국가를 입력하세요.">
			</div>
			<div>
				<button type="submit">추가</button>
			</div>
		</form>
		<c:choose>
			<c:when test="${not empty pageList}"> <!-- 해당 pageList라는 -->
				<%@include file="../module/paging.jsp" %> <!-- 페이지 리스트이다. -->
			</c:when>
			<c:otherwise> 
				<div>
					<button type="button" onclick="location.href='${pageContext.request.contextPath}/dept'">전체보기</button>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>