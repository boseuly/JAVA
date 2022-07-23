<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 페이지</title>
	<%@include file="../module/head.jsp" %>
</head>
<body>
	<section>
		<c:url var="deptUrl" value="./depts"/>
		<form action="${deptUrl}" method="get"> 
			<div>
				<input type="text" name="search" data-required="부서 아이디 입력">
				<button type="submit">조회</button>
				<c:url var="deptAddUrl" value="./depts/add"/>
				<button type="button" onclick="location.href='${deptAddUrl}'">추가</button>
				<select onchange="location.href='${deptUrl}?pgc=' + this.value">
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
						<th>부서 ID</th>
						<th>부서명</th>
						<th>관리자 ID</th>
						<th>지역 ID</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="data" items="${deptDatas}">
						<tr>
							<td>${data.deptId}</td>
							<td>${data.deptName}</td>
							<td>${data.mngId}</td>
							<td>${data.locId}</td>
							<c:url var="deptModUrl" value="/depts/mod"/>
							<c:url var="deptDelUrl" value="/depts/del">
								<c:param name="deptId" value="${data.deptId}"></c:param>
							</c:url>
							<td><button type="button" onclick="location.href='${deptModUrl}?deptId=' + ${data.deptId}">edit</button></td> <!-- 수정 -> 해당 deptId -->
							<td><button type="button" onclick="location.href='${deptDelUrl}'">delete</button></td> <!-- 삭제 -->
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:choose>
				<c:when test="${not empty pageList}"> <!--  pageList 가 존재한다면 즉 전체 페이지라면 -->
					<%@include file="../module/paging.jsp" %>
				</c:when>
				<c:otherwise> <!--  만약 전체페이지가 아니라면 pageList는 필요 없음 -->
					<c:url var="pageUrl" value="/depts"/> <!-- 얘가 너무 멀리 있으면 적용이 안 됨,,, 왜인지는 몰겠음 -->
					<button type="button" onclick="location.href='${pageUrl}'">전체보기</button>
				</c:otherwise>
			</c:choose>
		</div>
	</section>
</body>
</html>