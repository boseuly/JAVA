<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
	<meta charset=UTF-8"/>
	<title>게시글 제목 : ${data.title}</title>
	<link rel="stylesheet" type="text/css" href="/static/bs5/css/bootstrap.min.css">
	<link rel="stylesheet" href="http://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
	<script type="text/javascript" src="/static/bs5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/static/bs5/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<header></header>
	<section class="container">
		<div class="mt-3">
			<div class="mb-1 border-bottom border-2 border-secondary">
				<h1>${data.title}</h1>
			</div>
			<div class="mb-3">
				<label class="pe-3 text-secondary text-opacity-75">${empData.empName}</label>
				<fmt:formatDate value="${data.createDate}" var="createDate" dateStyle="long"/>
				<label class="pe-3 text-secondary text-opacity-75">${createDate}</label>
				<label class="pe-3 text-secondary text-opacity-75">조회수: ${data.viewCnt}</label>
			</div>
			<div class="mb-1 border-bottom border-2 border-secondary">
				<p>${data.content}</p>
			</div>
			<div class="mb-1">
				<div onclick="incLike(id_like, ${data.id});"><!-- id_like 는 아래 label의 id임 -->
					<i class="bi bi-hand-thumbs-up text-secondary text-opacity-50"></i>
					<label id="id_like" class="text-secondary text-opacity-75">${data.like }</label>
				</div>
			</div>
			<div class="mb-1 text-end">
				<button class="btn btn-primary" type="button">수정</button>
			</div>
		</div>
	</section>
	<footer></footer>
	<script type="text/javascript">
		function incLike(element, id){
			$.ajax({
				url : "/board/detail", // 이번에는 ajax 전용 서블릿을 만들지 않고 그냥 /board/detail로 보냄
				type : "post",
				data : {
					id : id // 데이터를 바꿀 아이디를 전달
				}, 
				success : function (data){
					if(data.code === "success"){ // 성공이면 +1
						element.innerText = parseInt(element.innerText) + 1; // 화면상에서만 +1 된 걸로 보인다.
					}
				}
			});
		}
	</script>
</body>
</html>
