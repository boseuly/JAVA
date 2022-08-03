<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
	<meta charset="UTF-8">
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
					<label id="id_like" class="text-secondary text-opacity-75">${data.like}</label>
				</div>
			</div>			<!-- 작성자  =  로그인 유저 -> 수정 가능하도록 해야 함  -->
			<div class="mb-1 text-end">
				<c:url value="/board" var="boardUrl"/>
				<button class="btn btn-primary" type="button" onclick="location.href='${boardUrl}'">목록</button>
				<c:if test="${data.empId eq sessionScope.loginData.empId}">
					<c:url value="/board/mod" var="boardModUrl">
						<c:param name="id" value="${data.id}"/> <!-- 게시글 id 전달 -->
					</c:url>
					<button class="btn btn-success" type="button" onclick="location.href='${boardModUrl}'">수정</button> 
					<button class="btn btn-danger" type="button" data-bs-tartget="#deleteModal" data-bs-toggle="modal">삭제</button> <!--  삭제할 게시물 아이디 전달 -->
				</c:if>
			</div>
		</div>
		<div class="modal fade" tabindex="-1" id="deleteModal">
		  <div class="modal-dialog modal-dialog-centered"> <!-- 화면 가운데 나오도록 함 -->
			    <div class="modal-content">
				      <div class="modal-header">
					        <h5 class="modal-title">글 삭제</h5>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				       	 	<p>해당 게시글을 삭제하시겠습니까?</p> 
				      </div>
				      <div class="modal-footer">
					  		<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
					        <button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="boardDelete(${data.id});">삭제</button>
				      </div>
			   	 </div>
		 	 </div>
		</div>
	
		<div class="modal" tabindex="-1" id="resultModal">
		  	<div class="modal-dialog modal-dialog-centered"> <!-- 화면 가운데 나오도록 함 -->
			    <div class="modal-content">
				      <div class="modal-header">
					        <h5 class="modal-title">결과 확인</h5>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				       	 	<p>삭제되었습니다.</p> 
				      </div>
				      <div class="modal-footer">
					  		<button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="location.href='${boardUrl}'">확인</button> <!-- 확인 버튼을 누르면 emp 페이지로 이동 -->
				      </div>
			    </div>
		  	</div>
		</div>
	</section>
	<footer></footer>
	
	<script type="text/javascript">
		function incLike(element, id){ // 여기서 element는 label임 
			$.ajax({
				url : "/board/detail", // 이번에는 ajax 전용 서블릿을 만들지 않고 그냥 /board/detail로 보냄
				type : "post",
				data : {
					id : id // 데이터를 바꿀 아이디를 전달
				}, 
				success : function (data){
					if(data.code === "success"){ // 성공이면 +1
						element.innerText = data.likeCnt; 
					}
				}
			});
		}
		
		function boardDelete(id){ // 삭제할 게시물 id
			$.ajax({
				type : "post",
				url : "/board/delete",  // 강사님은 board.controller.EmpBoardDeleteController에 있고, 나는 ajax.controller.AjaxBoardDeleteController에 있음
				data : {
					id: id // 삭제할 게시물 id 전달
				}, 
				dataType : "json",
				success : function (data){ // 성공시 모달을 띄워야 한다.
					var myModal = new Bootstrap.Modal(document.getElementById("resultModal"), {
						keyboard : false
					});
					var title = myModal._element.querySelector(".modal-title");
					var body = myModal._element.querySelector(".modal-body");
					
					
					title.innerText = data.title; 	// title의 값을 변경해줘야 한다.
					body.innerHTML = "<p>" + data.message + "</p>" // 모달에 메시지를 넣어야 한다. 
					myModal.show();
				}
			});
		}
	</script>
</body>
</html>
