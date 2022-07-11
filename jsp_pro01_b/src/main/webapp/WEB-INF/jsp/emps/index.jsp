<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>직원 조회</title>
	<%@ include file="../module/head.jsp" %>
</head>
<body>
	<section>
		<div>
			<button type="button" onclick="location.href='./emps/add'">추가</button>
		</div>
		<div>
			<form action="./emps" method="get">
				<div>
					<input type="text" name="search" data-required="직원 이름을 입력하세요.">
					<button type="submit">조회</button>
				</div>
				<div class="input-form form-right">
					<select class="select-form" onchange="location.href='./emps?pageCount=' + this.value"> <!-- 선택이 되면 바뀌도록 설정해둠 여기서 this.는 select 태크 요소를 지칭 -> select요소에서 value라는 값 -->
						<option value="5" ${sessionScope.pageCount == 5 ? 'selected' : ''}>5개</option>				<!--  pgc 파라미터에 value 값 저장 -->
						<option value="10" ${sessionScope.pageCount == 10 ? 'selected' : ''}>10개</option>	<!--  이거 왜 pageCount가 10개면  -->
						<option value="15" ${sessionScope.pageCount == 15 ? 'selected' : ''}>15개</option>
						<option value="20" ${sessionScope.pageCount == 20 ? 'selected' : ''}>20개</option>
					</select>
				</div>
			</form>
		</div>
		<table class="table wide vertical-hidden hover">
			<colgroup>
				<col class="col-120">
				<col class="col-240">
				<col class="col-240">
				<col class="col-240">
				<col class="col-240">
			</colgroup>
			<thead>
				<tr>
					<th>직원 ID</th>
					<th>이름</th>
					<th>이메일</th>
					<th>직급</th>
					<th>부서</th>
					<th class="border-hidden-right"></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty datas}">
					<c:forEach items="${datas}" var="data">
						<tr>
							<td>${data.empId}</td>
							<td>${data.empName}</td>
							<td>${data.email}</td>
							<td>${data.jobName}</td>
							<td>${data.deptName}</td>
							<td class="border-hidden-right">
								<c:url var="modUrl" value="./emps/mod">
									<c:param name="id" value="${data.empId}" />
								</c:url>
								<button type="button" class="btn btn-icon" onclick="location.href='${modUrl}'">
									<span class="material-symbols-outlined">edit</span>
								</button>
								<c:url var="delUrl" value="./emps/del">
									<c:param name="id" value="${data.empId}" />
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
		<c:choose>
			<c:when test="${not empty pageList}"><!-- 총 몇 페이지인지 알아내야 한다. -->
				<c:url var="pageUrl" value="./emps"/>
				<%@ include file="../module/paging.jsp" %>
			</c:when> 
			<c:otherwise>	<!--  만약 특정 id만 검색한 경우 -->
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/emps'">돌아가기</button>
			</c:otherwise>
		</c:choose>
	</section>
</body>
</html>