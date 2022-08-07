<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판</title>
	<link rel="stylesheet" type="text/css" href="/jsp/static/bs5/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
	<script type="text/javascript" src="/jsp/static/bs5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/jsp/static/bs5/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<%@include file="../module/navigation.jsp" %>
	<section class="container">
		<div class="mt-3 mb-1">
			<form action="./board" method="get">
				<div class="row g-1">
					<div class="col-8">
						<c:url value="/board/add" var="boardAddUrl"/>
						<button class="btn btn-outline" type="button" onclick="location.href='${boardAddUrl}'">추가</button>
					</div>
				</div>
				<div class="col-3">
					<div>
						<input class="input-text" type="text" name="search" data-required="조회할 게시판 어쩌고">
						<button class="btn btn-outline" type="submit">조회</button>
						<c:url value="/board" var="boardUrl">
							<c:param name="pageCount"/>
						</c:url>
					</div>
					<div class="col-1">
						<select class="select-form" onchange="location.href='${boardUrl}' + this.value">
							<option value="5" ${sessionScope.pageCount == 5 ? 'selected' : ''}>5 개</option>
							<option value="10" ${sessionScope.pageCount == 10 ? 'selected' : ''}>10 개</option>
							<option value="15" ${sessionScope.pageCount == 15 ? 'selected' : ''}>15 개</option>
							<option value="20" ${sessionScope.pageCount == 20 ? 'selected' : ''}>20 개</option>
						</select>
					</div>
				</div>
			</form>
		</div>
		<table class="table table-hover">
			<colgroup>
				<col class="col-60">
				<col class="col-auto">
				<col class="col-120">
				<col class="col-60">
				<col class="col-60">
				<col class="col-120">
			</colgroup>
			<thead>
				<tr>
					<th class="col-1">번호</th>
					<th class="col-5">제목</th>
					<th class="col-2">작성자</th>
					<th class="col-2">작성일</th>
					<th class="col-1">조회수</th>
					<th class="col-1">추천수</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty datas.pageDatas}"> <!-- paging.java 객체 안에 있는 pageDatas를 가져온다. -> 한 페이지내에 나와야할 객체 정보들 -->
					<c:forEach items="${datas.pageDatas}" var="data">
						<c:url var="detailUrl" value="/board/detail">
							<c:param name="id" value="${data.id}" /> <!-- 게시판 id로 게시판 상세 이동 -->
						</c:url>
						<tr style="cursor: pointer;" onclick="location.href='${detailUrl}'">
							<td>${data.id}</td>
							<td>${data.title}</td>
							<td>${data.empId}</td>
							<td>${data.createDate}</td>
							<td>${data.viewCnt}</td>
							<td>${data.like}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<nav>
			<c:url var="pageUrl" value="/board"/>
			<%@include file="../module/paging.jsp" %>
		</nav>
	</section>
</body>
</html>