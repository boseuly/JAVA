<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>직원 페이지</title>
	<%@include file="../module/head.jsp"%>
</head>
<body>
	<%@include file="../module/navigation.jsp" %>
	<section>
		<c:url value="./emps" var="pageUrl"/>
		<div>
			<button type="button" onclick="location.href='./emps/add'">직원 추가</button>
		</div>
		<div>
			<form action="${pageUrl}" method="post">
				<div>
					<input type="text" name="search" data-required="조회할 직원명을 입력하세요.">
					<button type="submit">조회</button>
				</div>
				<div>
					<select onchange="location.href='${pageUrl}?pageCount=' + this.value">
						<option value="5" ${pageCount == 5 ? 'selected' : ''}>5개</option>
						<option value="10" ${pageCount == 10 ? 'selected' : ''}>10개</option>
						<option value="15" ${pageCount == 15 ? 'selected' : ''}>15개</option>
						<option value="20" ${pageCount == 20 ? 'selected' : ''}>20개</option>
					</select>
				</div>
			</form>
			<div>
				<table class="table wide vertical-hidden hover">
					<colgroup>
						<col class="col-120">
						<col class="col-240">
						<col class="col-240">
						<col class="col-240">
						<col class="col-240">
						<col class="col-120">
					</colgroup>
					<thead>
						<tr>
							<th>직원 ID</th>
							<th>이름</th>
							<th>이메일</th>
							<th>직급</th>
							<th>부서</th>
						</tr>
					</thead>
					<tbody>		<!--  List<DeptDTO> datas로 넘김 -->
						<c:if test="${not empty datas}">
							<c:forEach var="data" items="${datas}"><!-- datas로 sevlet에서 저장했기 때문에 items는 해당 속성명이다. -->
									<tr>
										<td>${data.empId}</td>
										<td>${data.empName}</td>
										<td>${data.email}</td>
										<td>${data.jobName}</td>
										<td>${data.deptName}</td>
										<c:url var="modUrl" value="./emps/mod">
											<c:param name="id" value="${data.empId}"/>
										</c:url>
										<td><button type="button" onclick="location.href='${modUrl}'">수정</button></td>
										<c:url var="delUrl" value="./emps/del">
											<c:param name="id" value="${data.empId}"/>
										</c:url>
										<td><button type="button" onclick="location.href='${delUrl}'">삭제</button></td>
									</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			<c:choose>
				<c:when test="${not empty pageList}"> <!-- 해당 pageList라는 -->
					<%@include file="../module/paging.jsp" %> <!-- 페이지 리스트이다. -->
				</c:when>
				<c:otherwise> <!--  만약 pageList 가 존재하지 않는다면 -> search 즉, 특정 id를 검색한 경우 -->
					<div>
						<button type="button" onclick="location.href='${pageUrl}'">전체보기</button>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</section>
</body>
</html>