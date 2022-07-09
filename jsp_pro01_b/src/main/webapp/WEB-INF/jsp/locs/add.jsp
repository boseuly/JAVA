<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	<!--  태그라이브러리 추가 안 하면 사용 못 함 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>지역 추가</title>
	<%@include file="../module/head.jsp" %>
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
		<form class="small-form" action="./add" method="post">
			<div class="input-form wide">
				<label class="input-label">지역 ID</label>
				<input type="text" class="input-text" name="locId">
				<c:if test="${not empty error.locsId }">
					<label class="input-label-error">${error.deptName} </label>
				</c:if> <!--  getAttribute 을 사용하는 대신 ${}  -->
			</div>
			<div class="input-form wide">
				<label class="input-label">주소</label>
				<input type="text" class="input-text" name="stAddr">
				<c:if test="${not empty error.stAddr}">
					<label class="input-label-error">${error.locId }</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">우편</label>
				<input type="text" class="input-text" name="postal">
				<c:if test="${not empty error.postal }">
					<label class="input-label-error">${error.postal}</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">도시</label>
				<input type="text" class="input-text" name="city">
				<c:if test="${not empty error.city }">
					<label class="input-label-error">${error.city}</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">주</label>
				<input type="text" class="input-text" name="state">
				<c:if test="${not empty error.state }">
					<label class="input-label-error">${error.state}</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">국가 ID</label>
				<input type="text" class="input-text" name="ctyId">
				<c:if test="${not empty error.ctyId} ">
					<label class="input-label-error">${error.ctyId }</label>
				</c:if>
			</div>
			<div class="input-form wide form-right">
				<button class="btn btn-outline btn-ok" type="submit">저장</button>
				<button class="btn btn-outline btn-cancel" type="button" onclick="location.href='<%=request.getContextPath() %>/depts'">취소</button>
			</div>
		</form>
	</section>
</body>
</html>