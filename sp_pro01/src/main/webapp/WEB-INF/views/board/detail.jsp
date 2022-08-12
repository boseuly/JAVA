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
	<jsp:include page="../module/head.jsp"></jsp:include>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
</head>
<body>
	<header>
		<jsp:include page="../module/navigation.jsp"></jsp:include>
	</header>
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
			<div class="mb-1">
				<div onclick="incLike(id_like, ${data.id});">
					<i class="bi bi-hand-thumbs-up text-secondary text-opacity-50"></i>
					<label id="id_like" class="text-secondary text-opacity-75">${data.like}</label>
				</div>
			</div>
			<div class="mb-3 text-end">
				<c:url var="boardUrl" value="/board" />
				<button class="btn btn-primary" type="button" onclick="location.href='${boardUrl}'">목록</button>
				<c:if test="${data.empId eq sessionScope.loginData.empId}">
					<button class="btn btn-success" type="button" onclick="location.href='${boardUrl}/modify?id=${data.id}'">수정</button>
					<button class="btn btn-danger" type="button" data-bs-toggle="modal" data-bs-target="#deleteModal">삭제</button>
				</c:if>
			</div>
		</div>
		
		
		<!-- 댓글 -->
		<div class="mb-3">
			<c:forEach items="${commentPage.pageDatas}" var="comment">
				<div class="mb-1"><!-- 댓글 삭제 하기 위해서는 여기까지 찾은 다음에 삭제해야 한다.  --> <!-- 댓글이 여러개 나와야 하니까 여기서부터 반복되어야 함 -->
					<div class="card border-light">
						<div class="card-header">
							<div class="d-flex justify-content-between">
								<span><small>${comment.empName}</small></span>
								<span><small>${comment.createDate}</small></span>
							</div>
						</div>
						<div class="card-body">
							<input type="hidden" name="cid" value="${comment.id}"> <!-- comment id를 숨겨두기 -> 어떤 댓글인지 알아야 해서  -->
							<p class="card-text">${comment.content}</p>
							<c:if test="${sessionScope.loginData.empId eq comment.empId}"> <!-- 본인이 작성한 게시글에 대해서만 수정 삭제 나오도록  -->
								<div class="text-end">
									<button class="btn btn-sm btn-outline-dark" type="button" onclick="changeModify(this);">수정</button>
									<button class="btn btn-sm btn-outline-dark" type="button" onclick="commentDelete(this);">삭제</button>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
			<div class="mb-3">
				<c:url var="pageUrl" value="/board/detail">
					<c:param name="id">${data.id}</c:param>
				</c:url>
				<ul class="pagination justify-content-center">
					<c:choose>
						<c:when test="${commentPage.hasPrevPage()}">
							<li class="page-item">
								<a class="page-link bi bi-caret-left-fill" href="${pageUrl}&page=${commentPage.prevPage}"></a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item disabled">
								<a class="page-link bi bi-caret-left-fill" href="#"></a>
							</li>
						</c:otherwise>
					</c:choose>
					<c:forEach items="${commentPage.getPages(commentPage.currentPage - 2, commentPage.currentPage + 2)}" var="item">
						<li class="page-item ${commentPage.currentPage == item ? ' active' : ''}">
							<a class="page-link" href="${pageUrl}&page=${item}">${item}</a>
						</li>
					</c:forEach>
					<c:choose>
						<c:when test="${commentPage.hasNextPage()}">
							<li class="page-item">
								<a class="page-link bi bi-caret-right-fill" href="${pageUrl}&page=${commentPage.nextPage}"></a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item disabled">
								<a class="page-link bi bi-caret-right-fill" href="#"></a>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			<div class="mb-1">
				<c:url var="commentUrl" value="/board/comment" /> <!-- boardController에서 한꺼번에 처리하기 -->
				<form action="${commentUrl}/add" method="post">
					<input type="hidden" name="bid" value="${data.id}">
					<div class="input-group">
						<textarea class="form-control" name="content" rows="3" placeholder="댓글 작성"></textarea>
						<button class="btn btn-outline-dark" type="button" onclick="formCheck(this.form)">작성</button>
					</div>
				</form>
			</div>
		</div>
		<!-- 올린 파일 나올 자리 없으면 그냥 빈공간으로 -->
		<c:if test="${not empty files}"> <!-- 만약 저장해둔 files가 있다면 -->
			<div class="mb-1" id="file-upload"> <!-- 없으면 div의 id를 찾아서 div를 완전히 없애버려야 한다. -->
				<!-- <input type="text" class="form-control" /> --> <!-- 여러개를 올릴 수 있어야 하니까 multiple 추가 -->
			</div>
		</c:if>
		
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
		// 댓글 작성 안 하고 작성 누르는 경우 대비해서 만든 함수 // 이건 모달로 바꿔보기
		function formCheck(form) {
			if(form.content.value.trim() === "") { 
				alert("댓글을 입력하세요."); // 만약 내용이 없다면 이걸 띄워라 
			} else {
				form.submit(); 
			}
		}
		
		// 댓글 수정
		function changeModify(element) {  	// 수정 버튼 요소가 전달된다.
			element.innerText = "확인";		// 수정 버튼을 확인으로 변경해라
			element.nextElementSibling.remove(); // 수정 버튼의 다음 요소를 지워라
			
			var content = element.parentElement.previousElementSibling.innerText;
			var textarea = document.createElement("textarea"); 				// textarea를 태그를 새로 만들어준다. 변경해줘야 하기 때문에 
			textarea.value = content;
			textarea.setAttribute("class", "form-control");
			element.parentElement.previousElementSibling.innerText = ""; 	// 기존에 있던 내용 지우기
			element.parentElement.previousElementSibling.append(textarea); 	// textarea 요소 추가
			
			element.addEventListener("click", commentUpdate);  				// 바뀐 확인버튼에 이벤트를 등록한다. 
		}
		function changeText(element, value) {
			element.innerText = "수정";			// 버튼을 수정 버튼으로 변경하기
			
			var btnDelete = document.createElement("button");  	// delete 버튼 생성하기 
			btnDelete.innerText = "삭제";
			btnDelete.setAttribute("type", "button"); 			// 삭제 버튼으로 변경
			btnDelete.setAttribute("class", "btn btn-sm btn-outline-dark");
			btnDelete.setAttribute("onclick", "commentDelete(this);");		// 삭제를 하기위해 id를 전달해주는 것이다.
			
			element.parentElement.append(btnDelete);
			
			element.parentElement.previousElementSibling.children[0].remove();
			element.parentElement.previousElementSibling.innerText = value;
		}
		// 업데이트 성공시
		function commentUpdate(e) {
			var cid = e.target.parentElement.parentElement.firstElementChild.value;
			var value = e.target.parentElement.previousElementSibling.children[0].value;
			
			$.ajax({
				url: "${commentUrl}/modify",
				type: "post",
				data: {
					id: cid,
					content: value
				},
				success: function(data) {
					// 만약 아무런 수정이 없는 경우 그대로 보여줘야 한다.
					changeText(e.target, data.value);
				},
				complete: function() {
					// 버튼을 수정해주고, textarea를 다시 p로 변경해줘야 한다.
					e.target.removeEventListener("click", commentUpdate); // 이벤트 추가
				}
			});
		}
		// 댓글 삭제하기
		function commentDelete(element) { // 삭제 버튼 요소
			var cid = element.parentElement.parentElement.firstElementChild.value;
			var card = element.parentElement.parentElement.parentElement.parentElement;
			
			$.ajax({
				url: "${commentUrl}/delete",
				type: "post",
				data: {
					id: cid
				},
				success: function(data) {
					// 서버에 데이터 전송 후 삭제 성공 하면 화면 상에서도 삭제.
					if(data.code === "success") {
						card.remove();
					}
				}
			})
		}
		
		// 추천 눌를 시
		function incLike(element, id) {
			$.ajax({
				url: "${boardUrl}/like",
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
		
		// 게시글 삭제
		function boardDelete(boardId) {
			$.ajax({
				type: "post",
				url: "${boardUrl}/delete",
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
					title.innerText = "오류";
					body.innerHTML = "<p>" + ${sessionScope.commentError} + "</p>"
						
					myModal.show();
				}
			})
		}
	</script>
	<c:if test="${sessionScope.commentError}">
		<script type="text/javascript">
			var myModal = new bootstrap.Modal(document.getElementById("resultModal"), {
				keyboard: false
			});
			
			var title = myModal._element.querySelector(".modal-title");
			var body = myModal._element.querySelector(".modal-body");
			title.innerText = data.title;
			body.innerHTML = "<p>" + data.message + "</p>"
				
			myModal.show();
		</script>
		<c:remove var="commentError" scope="session"/>
	</c:if>
	<c:if test="${sessionScope.error}">
		<script type="text/javascript">
			alert("${sessionScope.error}");
		</script>
	</c:if>
</body>
</html>