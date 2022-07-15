<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>메인 화면</title>
	<%@ include file="./module/head.jsp"%>
</head>
<body>
<%@ include file="./module/navigation.jsp"%>
	<h1>메인 화면</h1>
	<section>
		<c:url value="loginUrl" var="/login" />
		<form action="${loginUrl}" method="post">
			<div>
				<label>직원 ID</label>
				<input type="text" name="empId" value="${data.empId}" data-required="직원 ID">
			</div>
			<div>
				<label>부서명</label>
				<select name="deptName" data-required="부서명">
					<c:forEach items="${deptList}" var="dept">
						<option value="${dept.deptId}">[${dept.deptId}]${dept.deptName}</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<label>이름</label>
				<input type="text" name="empName" value="${data.empName}" data-required="지역 ID">
			</div>
			<div>
				<input type="text" name="email" value="${data.mngId}" data-required="관리자 ID">
			</div>
			<div>
				<label></label>
			</div>
			<div>
				<input type="text" name="locId" value="${data.locId}" data-required="지역 ID">
			</div>
			<div>
				<label></label>
			</div>
			<div>
				<button type="submit">추가하기</button>
			</div>
		</form>
	</section>
</body>
</html>