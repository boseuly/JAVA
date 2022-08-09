<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:url var="loginUrl" value="/login"/>
<form class="small-form" action="${loginUrl}" method="post">
	<div class="form-floating">
		<input type="text" class="form-control" id="id_empId" name="empId" value="" placeholder="직원 ID를 입력하세요.">
		<label>직원 ID</label>
		<c:if test="${not empty error}"> <!--  에러가 발생하면 -->
			<label>${error}</label>
		</c:if>
	</div>
	<div class="form-floating m-2" >
		<select class="form-select" name="deptId" id="id_deptId">
			<c:forEach items="${deptDatas}" var="deptDto"> <!-- 부서명을 저장한 객체를 가져와서 for문을 돌려서 보여줘야 한다.  -->
				<option>부서 선택</option>
				<c:choose>
					<c:when test="${empty error and cookie.deptRe.value == deptDto.deptId}"> <!-- 만약 부서 기억하기 체크가 되어있다면-->
						<option value="${deptDto.deptId}" selected> <!--  해당 부서는 selected를 추가해라 -->
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
		<label for="id_deptId">부서 선택</label> <!-- 부서명은 선택하는 걸로 할 거임 -->
	</div>
	<div class="form-floating mb-2">
		<input type="text" class="form-control" id="id_empName" name="empName" 
		value="${param.empName}" placeholder="이름">
		<label for="id_empName">이름</label>
	</div>
	<div class="mb-2">
		<div class="form-check">
			<input class="form-check-input" role="switch" id="id_deptRe" type="checkbox" 
			name="deptRe" ${not empty cookie.deptRe.value ? 'checked' : ''}> <!--  만약 이미 checked 한 상태라면 계속 checked, 아니라면 '' -->
			<label class="form-check-label" for="id_deptRe">부서기억하기</label>
		</div>
		<button class="btn btn-outline-primary" type="submit">로그인</button>
	</div>
</form>
