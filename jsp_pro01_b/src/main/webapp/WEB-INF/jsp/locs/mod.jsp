<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="locs.model.LocsDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	<!--  태그라이브러리 추가 안 하면 사용 못 함 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지역 수정</title>
</head>
<body>
	<section class="container">
		<form class="" action="./mod" method="post"><!--  내가 이미 locs/mode 이기때문에 locs는 쓰면 중복된다. -->
			<input type="hidden" name="locId" value="${data.locId}" readonly>
			<div class="input-form wide">
				<label class="input-label">주소</label>
				<input type="text" name="stAddr" value="${data.stAddr }" data-required = "주소를 입력하세요."><!--  필수 잆력 -->
				<c:if test="${not empty errorMsg}">
					<label class="input-label-error">${errorMsg }</label>
				</c:if>
			</div>
			<div>
				<label class="input-label">우편</label>
				<input type="text" name="postal" value="${data.postal}" data-required = "우편을 입력하세요.">
				<c:if test="${not empty errorMsg }">
					<label class="input-label-error">${errorMsg }</label>
				</c:if>
			</div>
			<div>
				<label class="input-label">도시</label>
				<input type="text" name="city" value="${data.city}" data-required = "도시명을 입력하세요.">
				<c:if test="${not empty errorMsg }">
					<label class="input-label-error">${errorMsg }</label>
				</c:if>
			</div>
			<div>
				<label class="input-label">주</label>
				<input type="text" name="state" value="${data.state }" data-required = "주를 입력하세요.">
				<c:if test="${not empty errorMsg }">
					<label class="input-label-error">${errorMsg }</label>
				</c:if>
			</div>
			<div>
				<label class="input-label">국가</label>
				<input type="text" name="ctyId" value="${data.ctyId }" data-required = "국가명을 입력하세요.">
				<c:if test="${not empty errorMsg }">
					<label class="input-label-error">${errorMsg }</label>
				</c:if>
			</div>
			<div>
				<button class="bnt bnt-outline bnt-ok" type="submit">수정</button>
				<button class="bnt bnt-outline bnt-cancel" type="button" onclick="location.href='${pageContext.request.contextPath}/locs'">취소</button>
			</div>
		</form>
	</section>
</body>
</html>