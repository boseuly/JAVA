<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dept.model.DeptDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 추가</title>
	<%@ include file="../module/head.jsp" %>
</head>
<c:url var="ajaxDuplicateUrl" value="/ajax/duplicate"/>
<c:url var="ajaxExistsUrl" value="/ajax/exists"/>
<script type="text/javascript">
function sendElementDataValid(element, url) {
	$.ajax({
		type: "get",
		url: url,
		data: {
			name: element.name,
			value: element.value
		},
		success: function(data, status) {	// 서버로부터 받은 메시지를 처리
			setLabelState(element.nextElementSibling, data.code, data.message);
		},
		// 서버가 응답 메시지를 보내는 거고, 오류날 상황이 아니기 때문에 error는 필요 없음
		complete: function() { // input에 대한 값이 없어야 하니까 value === "" 를 해줌
			if(element.value === "" || element.value === undefined) {  // 그냥 text를 지운 거임
				element.nextElementSibling.innerText = "";
			}
		}
	});
}
// 기본키(deptId) 확인
function duplicateCheck(element) {
	sendElementDataValid(element, "${ajaxDuplicateUrl}")
}
// 외래키(locId, mngId) 있는지 확인
function existsCheck(element) {
	sendElementDataValid(element, "${ajaxExistsUrl}")
}
function setLabelState(element, code, message) { // 선택요소의 다음 요소, ajax에서 받은 
	if(code === "success") {
		// 정상처리 메시지
		element.innerText = message;
		element.setAttribute("class", "input-label-ok");
	} else if(code === "error") {
		// 오류 메시지
		element.innerText = message;
		element.setAttribute("class", "input-label-error");
	}
}
</script>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
		<form class="small-form" action="./add" method="post">
			<div class="input-form wide">
				<label class="input-label">부서ID</label>	<!-- blur 말고도 oninput을 사용해도 된다. 근데 blur가 더 낫다. input은 값을 추가하거나 살짝 변경할때마다 바로바로 서버에 데이터를 주기 때무넹 과부하 올 수도 있음 -->
				<input type="text" class="input-text" name="deptId" onblur="duplicateCheck(this);" 
				value="${data.deptId == -1 ? '' : data.deptId}" data-required="부서 ID를 입력하세요.">
				<label class="input-label-error"></label> <!--  해당 데이터를 추가하지 못하면 error 메시지를 띄운다. -->
			</div>
			<div class="input-form wide">
				<label class="input-label">부서명</label>
				<input type="text" class="input-text" name="deptName"
				value="${data.deptName}" data-required="부서명을 입력하세요.">
				<label class="input-label-error"></label>
			</div>
			<div class="input-form wide">
				<label class="input-label">관리자ID</label>
				<input type="text" class="input-text" name="mngId" onblur="existsCheck(this);" 
				value="${data.mngId == -1 ? '' : data.mngId}" data-required="관리자 ID를 입력하세요.">
				<label class="input-label-error"></label>
			</div>
			<div class="input-form wide">
				<label class="input-label">지역ID</label>
				<input type="text" class="input-text" name="locId" onblur="existsCheck(this);" 
				value="${data.locId == -1 ? '' : data.locId}" data-required="지역 ID를 입력하세요."> <!--  얘는 제약조건을 확인해야 하기 때문에 검사하는 거임 -->
				<label class="input-label-error"></label>
			</div>
			<div class="input-form wide form-right">
				<button class="btn btn-outline btn-ok" type="submit">저장</button>
				<button class="btn btn-outline btn-cancel" type="button" onclick="location.href='<%=request.getContextPath() %>/depts'">취소</button>
			</div>
		</form>
	</section>
</body>
</html>