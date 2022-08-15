<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판 등록</title>
	<jsp:include page="../module/head.jsp"></jsp:include>
	<c:url value="/static/ckeditor" var="ckedit"/>
	<script type="text/javascript" src="${ckedit}/ckeditor.js"></script>
</head>
<script type="text/javascript">
	function formCheck(form) {
		if(form.title.value === undefined || form.title.value.trim() === ""){
			// 모달 활성
			var modal = new bootstrap.Modal(document.getElementById("errorModal"), {
				keyboard: false
			})
			modal.show();
			return;
		}
		form.submit();
	}
</script>
<body>
	<header></header>
	<section class="container">
		<div class="mt-3">
			<c:url value="/board/add" var="boardAddUrl"/>
			<form action="${boardAddUrl}" method="post" enctype="multipart/form-data" >
				<div class="mb-3">
					<input class="form-control" id="id_title" name="title" placeholder="제목을 입력하세요." value="${param.title}"> <!-- 오류 났을 때 내용 유지하기 위해서 parameter 내용 가져오기 -->
				</div>
				<div class="mb-3">
					<textarea class="form-control" id="id-controller" name="content" 
					placeholder="내용을 입력하세요." rows="5">${param.content}</textarea>
				</div>
				
				<!-- 파일 올리기 --> <!-- 리스트로 보여줘야 한다. -->
				<div class="input-group input-group-sm mb-3">
					<input type="file" name="uploadFiles" class="form-control" id="inputGroupFile02" onchange="showFile(this);" multiple>
					<label class="input-group-text" for="inputGroupFile02">Upload</label>
				</div>
				<!-- 파일 리스트가 보이도록 해야 한다. -->
				<div class="input-group file-list input-group-sm mb-3">
					<!-- <input type="text" class="form-control"> <!--  올라간 파일명이 여기에 나타나야 함 -->
				</div>
				<div>
					<button class="btn btn-success" onclick="formCheck(this.form);" type="submit">저장</button>
				</div>
			</form>
		</div>
		<div class="modal" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel">
			  <div class="modal-dialog">
				    <div class="modal-content">
					      <div class="modal-header">
					        	<h5 class="modal-title" id="errorModalLabel">오류</h5>
					        	<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      <div class="modal-body">
					      		<c:choose>
					      			<c:when test="${empty errorMsg}">
							       		제목은 공란이 올 수 없습니다. 반드시 제목을 입력하세요.
					      			</c:when>
					      			<c:otherwise>
					      				${errorMsg}
					      			</c:otherwise>
					      		</c:choose>
					      </div>
					      <div class="modal-footer">
					        	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">확인</button>
					      </div>
				    </div>
			  </div>
		</div>
	</section>
	<footer></footer>
	<c:url var="imageUrl" value="/upload/image"/>
	<c:if test="${not empty errorMsg}">
		<script type="text/javascript">
			var modal = new bootstrap.Modal(document.getElementById("errorModal"), {
				keyboard: false
			})
			modal.show();
		</script>	
	</c:if>
	<!-- 파일 저장하고 input에 넣기 -->
	<script type="text/javascript">
		function showFile(element){ // input 요소
			var fileName = new Array(); // 배열 생성하기
			// input의 files 를 가져와야함
			var files = element.files;
			
			for (var i = 0; i < files.length; i++) { // 파일의 크기만큼 반복시킨다.
				fileName[i] = files[i].name; 		 // 파일명을 넣어준다. 
			}
			
			// input요소를 생성 -> div의 파일 개수만큼 input요소 생성
			// -> a 태그로 수정하기
			for(var i=0; i < fileName.length; i++){ 
				var input = document.createElement('input');
				input.className="form-control";	// class 추가
				input.value = fileName[i];		// 파일 이름 저장 -> input 요소 추가
				element.parentElement.nextElementSibling.appendChild(input);	// 이름이 담긴 input을 넣는다.
			}
		}
	
	</script>
	<!-- 
	<script type="text/javascript">
		CKEDITOR.replace("content", {
			filebrowserUploadUrl : "${imageUrl}?type=image" // path경로가 upload&ResponseType 이런식으로 되어 있기에 이렇게 해줘야 한다. 
		});			// 이미지 처리하는 url을 알려주면 여기에 이미지가 전달이 된다.
	</script>
	 -->
</body>
</html>