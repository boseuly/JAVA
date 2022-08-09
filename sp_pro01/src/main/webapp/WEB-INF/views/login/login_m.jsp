<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:url var="loginUrl" value="/login"/>
<form action="${loginUrl}" method="post">
	<div>
		<label>직원 ID</label>
		<input type="text" name="empId" data-required="직원 ID를 입력하세요.">
		<c:if test="${not empty error}"> <!--  에러가 발생하면 -->
			<label>${error}</label>
		</c:if>
	</div>
	<div>
		<label>부서명</label> <!-- 부서명은 선택하는 걸로 할 거임 -->
		<select name="deptId" data-required="부서명을 선택하세요.">
			<c:forEach items="${deptDatas}" var="deptDto"> <!-- 부서명을 저장한 객체를 가져와서 for문을 돌려서 보여줘야 한다.  -->
				<c:choose>
					<c:when test="${empty error and cookie.deptRe.value == deptDto.deptId}"> <!-- 만약 부서 기억하기 체크가 되어있다면-->
						<option value="${deptDto.deptId}" selected> <!--  해당 부서는 selected를 추가해라 -->
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
	<div>
		<label>이름</label>
		<input type="text" name="empName" data-required="이름을 입력하세요.">
	</div>
	<div>
		부서기억하기<input type="checkbox" name="deptRe" ${not empty cookie.deptRe.value ? 'checked' : ''}> <!--  만약 이미 checked 한 상태라면 계속 checked, 아니라면 '' -->
		<button type="submit">로그인</button>
	</div>
</form>
