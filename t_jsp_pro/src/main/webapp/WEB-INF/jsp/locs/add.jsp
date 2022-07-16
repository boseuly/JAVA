<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>지역 추가</title>
	<%@include file="../module/head.jsp" %>
</head>
<!-- 서블릿 주소가 서로 다르다. -->
<c:url value="/ajax/duplicate" var="duplicateUrl"/>
<c:url value="/ajax/exists" var="existsUrl"/>

<script type="text/javascript">
function duplicateCheck(element) {
	sendElementDataValid(element, "${duplicateUrl}");
}
function duplicateCheck(element) {
	sendElementDataValid(element, "${existsUrl}");
}

function sendElementDataValid(element, url) {
	$.ajax({
		type: "get",
		data: {
			// 어떤 정보인지를 알려줘야 한다 -> locId or ctyId
			name : element.name
			value : 
		}
	});
	
}
</script>
<body>
	<%@include file="../module/navigation.jsp" %>
	<c:url value="/locs/add" var="addUrl"/>
		<section class="container">
		<form class="small-form" action="${addUrl}" method="post">
			<div class="input-form wide">
				<label class="input-label">지역ID</label>	<!-- blur 말고도 oninput을 사용해도 된다. 근데 blur가 더 낫다. input은 값을 추가하거나 살짝 변경할때마다 바로바로 서버에 데이터를 주기 때무넹 과부하 올 수도 있음 -->
				<input type="text" class="input-text" name="locId" onblur="duplicateCheck(this);" 
				value="${data.locId == -1 ? '' : data.locId}" data-required="부서 ID를 입력하세요.">
				<label class="input-label-error"></label> <!-- -1 -> 에러 -1이 아니면 정상 -->
			</div>
			<div class="input-form wide">
				<label class="input-label">주소</label>
				<input type="text" class="input-text" name="stAddr"
				value="${data.stAddr}" data-required="부서명을 입력하세요.">
				<label class="input-label-error"></label>
			</div>
			<div class="input-form wide">
				<label class="input-label">우편</label>
				<input type="text" class="input-text" name="postal"
				value="${data.postal}" data-required="부서명을 입력하세요.">
				<label class="input-label-error"></label>
			</div>
			<div class="input-form wide">
				<label class="input-label">시</label>
				<input type="text" class="input-text" name="city" 
				value="${data.city}" data-required="관리자 ID를 입력하세요.">
				<label class="input-label-error"></label>
			</div>
			<div class="input-form wide">
				<label class="input-label">주</label>
				<input type="text" class="input-text" name="state" 
				value="${data.state}" data-required="지역 ID를 입력하세요.">
				<label class="input-label-error"></label>
			</div>
			<div class="input-form wide">
				<label class="input-label">국가</label>
				<input type="text" class="input-text" name="ctyId" onblur="existsCheck(this);" 
				value="${data.ctyId == '-1' ? '' : data.ctyId}" data-required="지역 ID를 입력하세요.">
				<label class="input-label-error"></label>
			</div>
			<div class="input-form wide form-right">
				<button class="btn btn-outline btn-ok" type="submit">저장</button>
				<button class="btn btn-outline btn-cancel" type="button" onclick="location.href='<%=request.getContextPath() %>/locs'">취소</button>
			</div>
		</form>
	</section>
</body>
</html>