<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>직원</title>
	<%@include file="../module/head.jsp"%>
</head>
<body>
	<section>
		<c:url var="empUrl" value="/emps"/>
		<form action="${empUrl}" method="get"> 
			<div>
				<input type="text" name="search" data-required="직원 아이디 입력">
				<button type="submit">조회</button>
				<c:url var="empAddUrl" value="/emps/add"/>
				<button type="button" onclick="location.href='${empAddUrl}'">추가</button>
				<select onchange="location.href='${empUrl}?pageCount=' + this.value">
					<option value="5" ${sessionScope.pageCount == 5 ? 'selected' : ''}>5</option>
					<option value="10" ${sessionScope.pageCount == 10 ? 'selected' : ''}>10</option>
					<option value="15" ${sessionScope.pageCount == 15 ? 'selected' : ''}>15</option>
					<option value="20" ${sessionScope.pageCount == 20 ? 'selected' : ''}>20</option>
				</select> <!--  아직은 session 이용 x -->
			</div>
		</form>
		<div>
			<table>
				<thead>
					<tr>
						<th>직원 ID</th>
						<th>직원명</th>
						<th>이메일</th>
						<th>직급</th>
						<th>부서명</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty datas}">
						<c:forEach var="data" items="${datas}">
							<c:url var="detailUrl" value="/emps/detail">
								<c:param name="empId" value="${data.empId}"></c:param>
							</c:url>
							<tr onclick="location.href='${detailUrl}'">
								<td>${data.empId}</td>
								<td>${data.empName}</td>
								<td>${data.email}</td>
								<td>${data.jobName}</td>
								<td>${data.deptName}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<c:choose>
				<c:when test="${not empty pageList}"> <!--  pageList 가 존재한다면 즉 전체 페이지라면 -->
					<c:url var="pageUrl" value="./emps"/> <!-- 얘가 너무 멀리 있으면 적용이 안 됨,,, 왜인지는 몰겠음 -->
					<%@include file="../module/paging.jsp" %>
				</c:when>
				<c:otherwise> <!--  만약 전체페이지가 아니라면 pageList는 필요 없음 -->
					<button type="button" onclick="location.href='${pageContext.request.contextPath}/emps'">전체보기</button>
				</c:otherwise>
			</c:choose>
		</div>
	</section>
</body>
</html>