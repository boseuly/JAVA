<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 수정</title>
</head>
<body>
	<section>
		<c:url var="deptModUrl" value="/depts/mod"/>
		<form  action="${deptModUrl}" method="post">
			<div>
				<label class="input-label">부서 ID</label> <!-- 여기서 url도 전달해줘야 한다. js 에서는 el 사용 불가 -->
				<input type="text" name="deptId" value="${deptData.deptId}" readonly >
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