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
	<title>직원 수정</title>
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
		<c:url var="empsModUrl" value="/emps/modify" />
		<form class="large-form" action="${empsModUrl}" method="post">
			<div class="img-form left">
				<c:url var="imgUrl" value="/static/img/emp/${sessionScope.loginData.empId}.png" />
				<img class="img-360" alt="여기에는 증명 사진이 배치됩니다." src="/static/img/emp/default.png">
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">ID</label>
					<input class="input-text w-auto" type="text" name="empId" value="${data.empId}" readonly> <!--  id는 전달해야 하니까 -->
				</div>
				<div class="input-form">
					<label class="input-label w-100">이름</label>
					<input class="input-text w-auto" type="text" name="empName" value="${data.empName}" disabled>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">직급</label>
					<select class="select-form w-auto" name="jobId" disabled>
						<c:forEach items="${jobDatas}" var="job">
							<c:choose>
								<c:when test="${job.jobId eq data.jobId}">
									<option value="${job.jobId}" selected>${job.jobName}</option>
								</c:when>
							</c:choose>
							<c:otherwise>
								<option value="${job.jobId}">${job.jobName}</option>
							</c:otherwise>
						</c:forEach>
					</select>
				</div>
				<div class="input-form">
					<label class="input-label w-100">부서</label>
					<select class="select-form w-auto" name="deptId" >
						<c:forEach items="${deptDatas}" var="dept">
							<c:choose>
								<c:when test="${dept.deptId == data.deptId}"> <!--  eq 랑 == 같음 거임 -->
									<option value="${dept.deptId}" selected>[${dept.deptId}] ${dept.deptName}</option>
								</c:when>
								<c:otherwise>
									<option value="${dept.deptId}">[${dept.deptId}] ${dept.deptName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">이메일</label>
					<input class="input-text w-auto" type="text" name="email" value="${data.email}">
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<fmt:formatDate var="hireDate" value="${dataDetail.hireDate}" pattern="YYYY-MM-dd" dateStyle="long" />
					<label class="input-label w-100">입사일</label> 	<!--  에러를 피하기 위해서 미리 값을 넣어준다. -> 오늘 날짜로!! -->
					<input class="input-text w-auto" type="text" name="hireDate" value="${hireDate}" >
				</div>
				<div class="input-form">
					<label class="input-label w-100">전화번호</label>
					<input class="input-text w-auto" type="text" name="phone" value="${dataDetail.phone}">
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">급여액</label>
					<input class="input-text w-auto" type="text" name="salery" value="${dataDetail.salary}" >
				</div>
				<div class="input-form">
					<label class="input-label w-100">커미션</label>
					<input class="input-text w-auto" type="text" name="commission" value="${dataDetail.commission}" >
				</div>
			</div>
			<div class="input-form form-right">
				<c:url>
				
				</c:url>
				<c:url var="empdetailUrl" value="/emps/detail"> <!-- 취소를 누르면 detail 화면으로 이동 -->
					<c:param name="id" value="${data.empId}"/>
				</c:url>
				<button class="btn btn-outline btn-ok " type="button" onclick="location.href=''">저장</button> 
				<button class="btn btn-outline btn-ok disable" type="button" onclick="location.href='${empdetailUrl}'">취소</button>
			</div>
		</form>
	</section>
</body>
</html>