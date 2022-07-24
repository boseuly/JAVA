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
<c:url var="ajaxDuplicateUrl" value="/ajax/duplicate"/>	<!-- 이건 기본키 검사 -->
<c:url var="ajaxExistsUrl" value="/ajax/exists"/> <!-- 이건 제약 조건 검사 -->

<script type="text/javascript">
function sendElementDataValid(element, url) {
	$.ajax({
		type: "get",
		url: url,
		data: {
			name: element.name,
			value: element.value
		},
		success: function(data, status) {
			setLabelState(element.nextElementSibling, data.code, data.message);
		},
		complete: function() {
			if(element.value === "" || element.value === undefined) {
				element.nextElementSibling.innerText = "";
			}
		}
	});
}
function duplicateCheck(element, url) {
	sendElementDataValid(element, url)
}
function existsCheck(element, url) {
	sendElementDataValid(element, url)
}
function setLabelState(element, code, message) {
	if(code === "success") {
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
	<section>
		<c:url var="deptAddUrl" value="/depts/add"/>
		<form  action="${deptAddUrl}" method="post">
			<div>
				<label class="input-label">부서 ID</label> <!-- 여기서 url도 전달해줘야 한다. js 에서는 el 사용 불가 -->
				<input type="text" name="deptId" onblur="duplicateCheck(this, '${ajaxDuplicateUrl}');" 
				value="${deptData.deptId == -1 ? '' : deptData.deptId}" data-required="부서 ID를 입력하세요." >
				<label class="error-label"></label>
			</div>
			<div>
				<label class="input-label">부서명</label>
				<input type="text" name="deptName" value="${deptData.deptName}" data-required="부서명을 입력하세요.">
				<label class="error-label"></label>
			</div>
			<div>
				<label class="input-label">관리자 ID</label>
				<input type="text" name="mngId"  onblur="existsCheck(this, '${ajaxExistsUrl}');" 
				value="${deptData.mngId == -1 ? '' : deptData.mngId}" data-required="관리자 ID를 입력하세요.">
				<label class="error-label"></label>
			</div>
			<div>
				<label class="input-label">지역 ID</label>
				<input type="text" name="locId"  onblur="existsCheck(this, '${ajaxExistsUrl}');"  
				value="${deptData.locId == -1 ? '' : deptData.locId}" data-required="지역 ID를 입력하세요.">
				<label class="error-label"></label>
			</div>
			<div>
				<button type="submit">저장</button>
				<c:url var="deptUrl" value="/depts"/>
				<button type="button" onclick="location.href='${deptUrl}'">취소</button>
			</div>
		</form>
	</section>
</body>
</html>