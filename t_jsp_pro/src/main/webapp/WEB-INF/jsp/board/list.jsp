<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>사내 게시판</title>
	<%@include file="../module/head.jsp" %>
</head>
<body>
	<section>
		<div>
			<c:url value="/board" var="boardUrl"/>
			<form action="${boardUrl}" method="get">
				<div class="input-form form-left" >
					<button class="btn btn-outline" type="button" onclick="location.href='/board/add">추가</button>
				</div>
				<div class="input-form form-right">
					<input name="search"> <!-- 조회할 때는 어떤 것을 조회할지 선택할 수 있도록 하고 싶음 -->
					<button class="btn btn-outline" type="submit">조회</button>
					<select class="select-form" onchange="location.href='/board?pageCount=' + this.value"> <!-- 여기서 값이 선택되면 값이 다시 /board로 전달되어야 한다.  -->
						<option value="5" ${sessionScope.pageCount == '5' ? selected : ''}>5</option>
						<option value="10" ${sessionScope.pageCount == '10' ? selected : ''}>10</option>
						<option value="15" ${sessionScope.pageCount == '15' ? selected : ''}>15</option>
						<option value="20" ${sessionScope.pageCount == '20' ? selected : ''}>20</option>
					</select>
				</div>
			</form>
		</div>
		<table class="table wide vertical-hidden hover">
			<colgroup>
				<col class="col-120">
				<col class="col-480">
				<col class="col-60">
				<col class="col-120">
				<col class="col-60">
				<col class="col-60">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>게시글 제목</th>
					<th>작성자</th>
					<th>작성날짜</th>
					<th>조회수</th>
					<th>추천수</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty boardDatas}">
					<c:forEach items="${boardDatas}" var="data"> <!-- items는 게시판의 정보 -->
						<tr>
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
	</section>

</body>
</html>