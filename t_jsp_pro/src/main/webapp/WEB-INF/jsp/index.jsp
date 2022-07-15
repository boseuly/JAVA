<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Welcome JSP/Servlet</title>
	<%@ include file="./module/head.jsp" %>
</head>
<script type="text/javascript">
	function sendAjax() {
		$.ajax({
			type: "get",
			url: "/ajax/test",
			data: {
				x: 1, y: "A"
			},
			dataType: "json",
			success: function(data, status) {
				// 응답이 성공(응답코드 200)적으로 이루어 졌을 때 동작할 함수
				console.log("success: " + data);
				for(d of data) {
					console.log("success: " + d.empId);
					console.log("success: " + d.empName);
					console.log("success: " + d.deptId);
					console.log("success: " + d.deptName);
					console.log("success: " + d.jobId);
					console.log("success: " + d.jobName);
				}
			},
			error: function(data, status) {
				// 응답코드 200 이 아닌 모든 응답일 때 동작할 함수
				console.log(data);
				console.log(status);
			},
			complete: function() {
				// 성공/실패 유무와 상관없이 동작할 함수
				console.log("complete 무조건 실행");
			},
			beforeSend: function() {
				// 서버에 데이터를 전송하기 전에 동작할 함수
				console.log("beforeSend 데이터 전송 전");
			}
		});
	}
</script>
<body>
	<%@ include file="./module/navigation.jsp" %>
	<section class="container">
		<c:url var="loginUrl" value="/login" />
		<form class="small-form" action="${loginUrl}" method="post">
			<div class="input-form wide">
				<label class="input-label">직원ID</label>
				<input type="text" class="input-text" name="empId" value="${param.empId}" data-required="직원ID를 입력하세요.">
				<c:if test="${not empty error}">
					<label class="input-label-error">${error}</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">부서명</label>
				<select class="select-form" name="deptId" data-required="부서명을 선택하세요.">
					<c:forEach items="${deptList}" var="deptDto">
						<c:choose>
							<c:when test="${empty error and cookie.deptRe.value == deptDto.deptId}">
								<option value="${deptDto.deptId}" selected>
									[${deptDto.deptId}] ${deptDto.deptName}
								</option>
							</c:when>
							<c:when test="${not empty error and param.deptId == deptDto.deptId}">
								<option value="${deptDto.deptId}" selected>
									[${deptDto.deptId}] ${deptDto.deptName}
								</option>
							</c:when>
							<c:otherwise>
								<option value="${deptDto.deptId}">
									[${deptDto.deptId}] ${deptDto.deptName}
								</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div class="input-form wide">
				<label class="input-label">이름</label>
				<input type="text" class="input-text" name="empName" value="${param.empName}" data-required="이름을 입력하세요.">
			</div>
			<div class="input-form wide form-right">
				<button class="btn btn-outline btn-ok" type="submit">로그인</button>
			</div>
		</form>
	</section>
</body>
</html>


