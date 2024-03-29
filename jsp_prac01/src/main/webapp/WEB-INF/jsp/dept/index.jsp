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
<%@ include file="../module/navigation.jsp"%>
<section>
	<c:url value="./dept" var="pageUrl"/>
		<div class="outside-controller">
			<div class="add-button-">
				<button type="button" onclick="location.href='./dept/add'">부서 추가</button> 
			</div>
			<form action="./dept" method="get">
				<div>
					<input type="text" name="search" data-required="부서코드를 입력하세요.">
					<button type="submit">조회</button>
					<select onchange="location.href='./dept?pgc=' + this.value">
						<option value="5" ${sessionScope.pageCount == 5 ? 'selected' : ''}>5개</option>
						<option value="10" ${sessionScope.pageCount == 10 ? 'selected' : ''}>10개</option>
						<option value="15" ${sessionScope.pageCount == 15 ? 'selected' : ''}>15개</option>
						<option value="20" ${sessionScope.pageCount == 20 ? 'selected' : ''}>20개</option>
					</select>
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
						<c:forEach items="${datas}" var="data" ><!-- datas로 sevlet에서 저장했기 때문에 items는 해당 속성명이다. -->
								<tr>
									<td>${data.deptId}</td>
									<td>${data.deptName}</td>
									<td>${data.mngId}</td>
									<td><a href="./locs?search=${data.locId}">${data.locId}</a></td>
									<td class="border-hidden-right">
										<c:url var="modUrl" value="./depts/mod">
											<c:param name="id" value="${data.deptId}" />
										</c:url>
										<button type="button" class="btn btn-icon" onclick="location.href='${modUrl}'">
											<span class="material-symbols-outlined">edit</span>
										</button>
										<c:url var="delUrl" value="./depts/del">
											<c:param name="id" value="${data.deptId}" />
										</c:url>
										<button type="button" class="btn btn-icon" onclick="location.href='${delUrl}'">
											<span class="material-symbols-outlined">delete</span>
										</button>
									</td>
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
					<button type="button" onclick="location.href='${pageContext.request.contextPath}/dept'">전체보기</button>
				</div>
			</c:otherwise>
		</c:choose>
	</section>
</body>
</html>