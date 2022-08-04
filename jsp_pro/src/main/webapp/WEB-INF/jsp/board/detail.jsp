<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${data.title}</title>
	<link rel="stylesheet" type="text/css" href="/static/bs5/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
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
				<fmt:formatDate value="${data.createDate}" var="createDate" dateStyle="long" />
				<label class="pe-3 text-secondary text-opacity-75">${createDate}</label>
				<label class="pe-3 text-secondary text-opacity-75">조회수: ${data.viewCnt}</label>
			</div>
			<div class="mb-1 border-bottom border-2 border-secondary">
				<p>${data.content}</p>
			</div>
			 <!-- 댓글 -->
			 <div class="mb-3">
				<div class="mb-1"><!-- 댓글 삭제 하기 위해서는 여기까지 찾은 다음에 삭제해야 한다.  -->
					<div class="card border-light"> 
						<div class="card-header">
							<div class="d-flex justify-content-between">
								<span><small>Steven King</small></span>
								<span><small>2022년 8월 04일</small></span>
							</div>
						</div>
						<div class="card-body">
							<input type="hidden" name="cid" value="1"> <!-- comment id를 숨겨두기  -->
							<p class="card-text">댓글 양식 확인 중</p>
							<div class="text-end">
								<button class="btn btn-sm btn-outline-dark" type="button" onclick="changeModify(this)">수정</button>
								<button class="btn btn-sm btn-outline-dark" type="button" onclick="changeModify(this)">삭제 </button>
							</div>
						</div>
					</div>
				</div>
				<div class="mb-1">
					<div class="input-group">
						<textarea class="form-control" rows="3" placeholder="댓글 작성"></textarea>
						<button class="btn btn-online-dark" type="button">작성</button>
					</div>
				</div>
			 </div>
			<div class="mb-1">
				<div onclick="incLike(id_like, ${data.id});">
					<i class="bi bi-hand-thumbs-up text-secondary text-opacity-50"></i>
					<label id="id_like" class="text-secondary text-opacity-75">${data.like}</label>
				</div>
			</div>
			<div class="mb-1 text-end">
				<c:url var="boardUrl" value="/board" />
				<button class="btn btn-primary" type="button" onclick="location.href='${boardUrl}'">목록</button>
				<c:if test="${data.empId eq sessionScope.loginData.empId}">
					<button class="btn btn-success" type="button" onclick="location.href='${boardUrl}/mod?id=${data.id}'">수정</button>
					<button class="btn btn-danger" type="button" data-bs-toggle="modal" data-bs-target="#deleteModal">삭제</button>
				</c:if>
			</div>
		</div>
		
		<!-- 모달 -->
		<div class="modal fade" tabindex="-1" id="deleteModal">
			<div class="modal-dialog modal-dialog-centered">
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
		<div class="modal fade" tabindex="-1" id="resultModal">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">결과 확인</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<p>삭제되었습니다.</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="location.href='${boardUrl}'">확인</button>
					</div>
				</div>
			</div>
		</div>
	</section>
	<footer></footer>
	<script type="text/javascript">
		function changeModify(element){ // 수정 버튼 요소가 전달된다. 
			element.innerText = "확인"; 	// 수정 버튼을 확인으로 변경해라
			element.nextElementSibling.remove(); // 수정 버튼의 다음 요소를 지워라
			
			var content = element.parentElement.previousElementSibling.innerText;
			var textarea = document.createElement("textarea"); // textarea를 태그를 새로 만들어준다. 변경해줘야 하기 때문에 
			textarea.value = content;
			textarea.setAttribute("class", "form-control");
			element.parentElement.previousElementSibling.innerText = ""; // 
			element.parentElement.previousElementSibling.append(textarea);
			// onclick 을 null로 했던 이유
			element.addEventListener("click", commentUpdate); // 바뀐 확인버튼에 이벤트를 등록한다. 
		}
			
		// 수정이 완료되면 확인 버튼을 
		function changeText(element){
			element.innerText = "수정"; 	// 버튼을 수정 버튼으로 변경하기 
			element.removeEventListner("click", commentUpdate); 
			
			var btnDelete = document.createElement("button"); // delete 버튼 생성하기 
			btnDelete.innerText= "삭제"; 	// 삭제 버튼으로 변경
			btnDelete.setAttribute("type", "button");
			btnDelete.setAttribute("class", "btn btn-sm btn-outline-dark");
			btnDelete.setAttribute("onclick", "commentDelete("this");"); // 삭제를 하기위해 id를 전달해주는 것이다.
			
			element.append(btnDelete); // 
			
			var value = element.parentElement.previousElementSibling.children[0].value;
			
			element.parentElement.previousElementSibling.children[0].remove();
			element.parentElement.previousElementSibling.innerText = value;
		}
		
		// 댓글 삭제하기
		function commentDelete(element){ // 삭제 버튼 요소
			var cid= element.parentElement.parentElement.firstElementChild.value;
			var card = element.parentElement.parentElement.parentElement.parentElement;
			
			$.ajax({
				url: "/comment/delete",
				type : "post",
				data: {
					id : cid
				},
				// 서버에 데이터 전송 후 삭제 성공 하면 화면 상에서도 삭제.
				success : function (data){
					if(data.code === "success"{
					card.remove();
						
					}
				}
			});
			
			card.remove();
		}
		

		
		// 성공시 
		function commentUpdate(e){
			var cid = e.target.parentElement.parentElement.firstElementChild.value;
			var value = e.target.parentElement.previousElementSibling.children[0].value;// 수정한 데이터를 추출함
			console.log(cid);
			$.ajax({
				url : "/comment/modify",
				type : "post",
				data :{
					id : cid,
					content : value
				}
				success : function (data){
					if(data.code === "success"){
						alert("수정되었습니다.");
					}else {
						alert("수정에 실패하였습니다.");
					}
				},
				complete : function (){ // 성공하든 실패하든 무조건 실행
					changeText(e.target); // 버튼을 수정해주고, textarea를 다시 p로 변경해줘야 한다.
					e.target.removeEventListener("click", commentUpdate); // 클릭하면 commentUpdate 이벤트 작동하도록 한다.
				}
			});
		}
		
		function incLike(element, id) {
			$.ajax({
				url: "/board/detail",
				type: "post",
				data: {
					id: id
				},
				success: function(data) {
					if(data.code === "success") {
						element.innerText = data.likeCnt;
					}
				}
			});
		}
		function boardDelete(boardId) {
			$.ajax({
				type: "post",
				url: "/board/delete",
				data: {
					id: boardId
				},
				dataType: "json",
				success: function(data) {
					var myModal = new bootstrap.Modal(document.getElementById("resultModal"), {
						keyboard: false
					});
					var title = myModal._element.querySelector(".modal-title");
					var body = myModal._element.querySelector(".modal-body");
					title.innerText = data.title;
					body.innerHTML = "<p>" + data.message + "</p>"
						
					myModal.show();
				}
			})
		}
	</script>
</body>
</html>