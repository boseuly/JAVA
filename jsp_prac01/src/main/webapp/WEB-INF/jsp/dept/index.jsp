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
<title>부서 조회</title>
</head>
<body>
	<div>
	
		<div>
			<h1>부서 조회</h1>
		</div>
		<div>
			<button type="button" onclick="location.href='./dept/add'">부서 추가</button> 
		</div>
		<form action="./dept" method="get">
			<div>
				<input type="text" name="search" data-required="부서코드를 입력하세요.">
				<button type="submit">조회</button>
			</div>
		</form>
		<table>
			<thead>
				<tr>
					<th>DeptId</th>
					<th>DeptName</th>
					<th>MngId</th>
					<th>LocId</th>
				</tr>
			</thead>
			<tbody>		<!--  List<DeptDTO> datas로 넘김 -->
			<c:if test="${not empty datas}">
				<c:forEach var="data" items="${datas}"><!-- datas로 sevlet에서 저장했기 때문에 items는 해당 속성명이다. -->
					<tr>
						<td>${data.deptId}</td>
						<td>${data.deptName}</td>
						<td>${data.mngId}</td>
						<td><a href="./locs?search=${data.locId}">${data.locId}</a></td>
						<td><button type="button" onclick="location.href='./dept/mod?id=${data.deptId}'">수정</button></td>
						<td><button type="button" onclick="location.href='./dept/del?id=${data.deptId}'">삭제</button></td>
					</tr>
				</c:forEach>
			</c:if>
			</tbody>
		</table>
	</div>
</body>
</html>