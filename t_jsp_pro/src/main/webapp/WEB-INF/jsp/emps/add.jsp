<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../module/head.jsp" %>
	<meta charset="UTF-8">
	<title>직원 추가</title>
</head>
<body>
	<c:url value="/emps/add" var="addUrl"/>
	<section class="container">
		<form class="small-form" action="${addUrl}" method="post">
			<div class="input-form wide">
				<label class="input-label">직원ID</label>	
				<input type="text" class="input-text" name="empId" onblur="duplicateCheck(this);" 
				value="${data.empId == -1 ? '' : data.empId}" data-required="직원 ID를 입력하세요.">
				<label class="input-label-error"></label> <!--  해당 데이터를 추가하지 못하면 error 메시지를 띄운다. -->
			</div>
			<div class="input-form wide">
				<label class="input-label">이름</label>
				<input type="text" class="input-text" name="empName"
				value="${data.empName}" data-required="이름을 입력하세요.">
				<label class="input-label-error"></label>
			</div>
			<div class="input-form wide">
				<label class="input-label">이메일</label>
				<input type="text" class="input-text" name="email"
				value="${data.email}" data-required="이메일을 입력하세요.">
				<label class="input-label-error"></label>
			</div>
			<div class="input-form wide">
				<label class="input-label">직급</label>
				<input type="text" class="input-text" name="jobName"
				value="${data.jobName}" data-required="직급을 입력하세요.">
				<label class="input-label-error"></label> <!-- JOBS 테이블의 JOB_TITLE에 존재하는지 확인 -->
			</div>
			<div class="input-form wide">
				<label class="input-label">부서</label>
				<input type="text" class="input-text" name="deptName" onblur="existsCheck(this);" 
				value="${data.deptName == -1 ? '' : data.deptName}" data-required="부서를 입력하세요.">
				<label class="input-label-error"></label> <!-- DEPARTMENT테이블의 DEPARTMENT_NAME에 존재하는지 확인 -->
			</div>
			
			
			<div class="input-form wide">
				<label class="input-label">전화번호</label>
				<input type="text" class="input-text" name="phone" onblur="existsCheck(this);" 
				value="${data.phone == -1 ? '' : data.phone}" data-required="부서를 입력하세요.">
				<label class="input-label-error"></label> <!-- DEPARTMENT테이블의 DEPARTMENT_NAME에 존재하는지 확인 -->
			</div>
			<div class="input-form wide">
				<label class="input-label">고용일</label>
				<input type="text" class="input-text" name="hireDate" onblur="existsCheck(this);" 
				value="${data.hireDate == -1 ? '' : data.hireDate}" data-required="부서를 입력하세요.">
				<label class="input-label-error"></label> <!-- DEPARTMENT테이블의 DEPARTMENT_NAME에 존재하는지 확인 -->
			</div>
			<div class="input-form wide">
				<label class="input-label">급여</label>
				<input type="text" class="input-text" name="salary" onblur="existsCheck(this);" 
				value="${data.salary == -1 ? '' : data.salary}" data-required="부서를 입력하세요.">
				<label class="input-label-error"></label> <!-- DEPARTMENT테이블의 DEPARTMENT_NAME에 존재하는지 확인 -->
			</div>
			<div class="input-form wide">
				<label class="input-label">커미션</label>
				<input type="text" class="input-text" name="commission" onblur="existsCheck(this);" 
				value="${data.commission == -1 ? '' : data.commission}" data-required="부서를 입력하세요.">
				<label class="input-label-error"></label> <!-- DEPARTMENT테이블의 DEPARTMENT_NAME에 존재하는지 확인 -->
			</div>
			<div class="input-form wide">
				<label class="input-label">관리자</label>
				<input type="text" class="input-text" name="mngId" onblur="existsCheck(this);" 
				value="${data.mngId == -1 ? '' : data.mngId}">
				<label class="input-label-error"></label> <!-- DEPARTMENT테이블의 DEPARTMENT_NAME에 존재하는지 확인 -->
			</div>
			
			<div class="input-form wide form-right">
				<button class="btn btn-outline btn-ok" type="submit">저장</button>
				<button class="btn btn-outline btn-cancel" type="button" onclick="location.href='<%=request.getContextPath() %>/emps'">취소</button>
			</div>
		</form>
	</section>
</body>
</html>