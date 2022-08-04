<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 수정</title>
	<link rel="stylesheet" type="text/css" href="/static/bs5/css/bootstrap.min.css">
	<link rel="stylesheet" href="http://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
	<script type="text/javascript" src="/static/bs5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/static/bs5/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="/static/ckeditor/ckeditor.js"></script>
</head>
<body>
	<header></header>
	<section class="container">
		<form action="/board/mod" method="post">
			<div class="mt-3">
				<div class="mb-1 border-bottom border-2 border-secondary">
					<input type="text" name="title" value="${data.title}">
				</div>
				<div class="mb-3">
					<label class="pe-3 text-secondary text-opacity-75">${empData.empName}</label>
					<fmt:formatDate value="${data.createDate}" var="createDate" dateStyle="long"/>
					<label class="pe-3 text-secondary text-opacity-75">${createDate}</label>
					<label class="pe-3 text-secondary text-opacity-75">조회수: ${data.viewCnt}</label>
					<input type="text" name="id" value="${data.id}" hidden="">
				</div>
				<div class="mb-1 border-bottom border-2 border-secondary">
					<textarea name="content" cols="150" rows="20">${data.content}</textarea>
				</div>
				<div class="mb-1">
					<div>
						<i class="bi bi-hand-thumbs-up text-secondary text-opacity-50"></i>
						<label id="id_like" class="text-secondary text-opacity-75">${data.like}</label>
					</div>
				</div>			<!-- 작성자  =  로그인 유저 -> 수정 가능하도록 해야 함  -->
				<div class="mb-1 text-end">
				<c:url value="/board" var="boardUrl"/>
				<button class="btn btn-primary" type="button" onclick="location.href='${boardUrl}'">목록</button>
				<c:url value="/board/mod" var="boardModUrl">
					<c:param name="id" value="${data.id}"/> <!-- 게시글 id 전달 -->
				</c:url>
				<c:url value="/board/del" var="boardDelUrl">
					<c:param name="id" value="${data.id}"/> <!-- 게시글 id 전달 -->
				</c:url>
				<button class="btn btn-primary" type="submit">저장</button> 
				<button class="btn btn-primary" type="button" onclick="location.href='${boardDelUrl}'">삭제</button>
				</div>
			</div>
		</form>
	</section>
	<footer></footer>
	<script type="text/javascript">
		CKEDITOR.replace("content", {
			filebrowserUploadUrl : "/image/upload?type=image" 
		});			// 이미지 처리하는 url을 알려주면 여기에 이미지가 전달이 된다.
	</script>
</body>
</html>