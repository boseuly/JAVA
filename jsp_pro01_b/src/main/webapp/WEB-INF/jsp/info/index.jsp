<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>내 정보</title>
	<%@ include file="../module/head.jsp" %>
</head>
<script type="text/javascript">
	window.onload = function(){
		var form = document.forms[0];
		form.email.addEventListener("input", enableSaveButton);
		form.phone.addEventListener("input", enableSaveButton);
		
		prevImage.addEventListener("click", function(e){
			btnImage.click();
		});
		btnImage.addEventListener("change", ajaxUploadImage); // 이미지 버튼 선택 -> 변경(change)) 이벤트 동작
	}
	// 업로드한 사진이 뜬다.
	function ajaxUploadImage(e){	// 이렇게 함수를 만들어준 다음에 함수를 
		var file = e.target.file[0];	// 해당 이벤트를 불러온 객체의 0번째 인덱스에 있는 파일을 가져와라
		var fData = new FormData();		
		fData.append("uploadImage", file, file.name);
		
		$.ajax({
			type: "post",
			url: "/ajax/imageUpload",
			enctype: "multipart/form-data",
			dataType: "json"
			data: fData, 
			processData: false, 	// 문자열 제외 데이터 전송 할 때 필요하다. 일반적인 문자열을 전달하는 것이 아니라 이미지와같은 특수한 파일을 전달할 때 false라고 해야한다.(기본 true이다.)
			contentType: false,		// 서버에 데이터를 보낼 때 컨텐츠 유형을 명시한다. 우리가 일반적으로 전달하는 타입이 아니기 때문에 false로 해준다. -> 자동으로 채워준다.
			success: function(data, status){	// 성공했을 때 어떻게 처리할지 
				prevImage.src = data.loc;
			},
			error : function (data, status){	// 실패했을 때 어떻게 처리할지
				console.log("처리 실패");
				prevImage.src = data.loc;
			}
		})
	}
	// 사진이 저장된게 아니라 파일을 prevImage를 사용해서 미리보기를 해준 거다.
	function showPreview(e) {
		var file = e.target.files[0]; // 선택한 이미지의 파일 객체 정보가 file에 저장된다. (여러이미지를 선택할 수도 있으니까 배열로 되어 있음)
		var imgUrl = URL.createObjectURL(file);	// 파일 정보를 토대로 해서 url정보를 만들어낸다. 해당 파일에 대한 url정보를 가지고 온다. 
		prevImage.src = imgUrl; // 사용자가 선택한 이미지의 실제 이미지 모습을 보여준다. prevImage를 통해 이미지 보여줄 수 있음 -> 그렇다고 선택한 이미지가 서버에 저장된 건 아님
	}
	
	function enableSaveButton(e) {
		var submit = document.querySelector("button[type='submit']");
		var enable = submit.getAttribute("class").replace("disable", "");
		submit.setAttribute("class", enable);
	}
</script>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
		<c:url var="updateUrl" value="/myinfo"/> <!-- c:url을 사용하면 contextPath 가 자동으로 등록이 된다. -->
		<form class="large-form" action="${updateUrl}" method="post" enctype="multipart/form-data"> <!-- 수정시 경로 -->
			<div class="img-form left">
				<c:url var="imgUrl" value="${imagePath}" />
				<img class="img-360" alt="여기에는 증명 사진이 배치됩니다." src="${imgUrl}"> <!--  서버에 저장된 이미지 url 경로  -->
				<br>
				<input type="file" name="uploadImage">
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">ID</label>
					<input class="input-text w-auto" type="text" name="empId" value="${sessionScope.loginData.empId}" readonly>
				</div>
				<div class="input-form">
					<label class="input-label w-100">이름</label>
					<input class="input-text w-auto" type="text" name="empName" value="${sessionScope.loginData.empName}">
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
				<label class="input-label w-100">직급</label>
					<select class="select-form w-auto" name="jobId">
						<c:forEach items="${jobDatas}" var="job">
							<c:when test="${job.name==sessionScope.job.name}">
								<option value="${job.jobId}" selected>${job.jobName}</option>
							</c:when>
							<c:otherwise>
								<option value="${job.jobId}">${job.jobName}</option>
							</c:otherwise>
						</c:forEach>
					</select>
				</div>
				<div class="input-form">
					<label class="input-label w-100">부서</label>
					<select class="select-form w-auto" name="deptId">
						<c:forEach items="${jobDatas}" var="job">
							<c:when test="${dept.deptId == sessionScope.dept.deptId}">
								<option value="${dept.deptId}" selected>${dept.deptName}</option>
							</c:when>
							<c:otherwise>
								<option value="${dept.deptId}">${dept.deptId}</option>
							</c:otherwise>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">이메일</label>
					<input class="input-text w-auto" type="text" name="email" value="${sessionScope.loginData.email}">
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form"> <!-- var와 같은 속성명을 만들겠다. -->
					<fmt:formatDate var="fmtHireDate" value="${empDetail.hireDate}" dateStyle="long"/> 
				
					<label class="input-label w-100">입사일</label>
					<input class="input-text w-auto" type="text" name="hireDate" value="${fmtHireDate}" disabled>
				</div>
				<div class="input-form">
					<label class="input-label w-100">전화번호</label>
					<input class="input-text w-auto" type="text" name="phone" value="${empDetail.phone}">
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<fmt:formatNumber var="fmtSalary" value="${empDetail.salary}"/>
					<label class="input-label w-100">급여액</label>
					<input class="input-text w-auto" type="text" name="salery" value="${fmtSalary}" disabled>
				</div>
				<div class="input-form">
					<label class="input-label w-100">커미션</label>
					<fmt:formatNumber var="fmtcommission" value="${empDetail.commission}" type="percent"/>
					<input class="input-text w-auto" type="text" name="commission" value="${fmtcommission}" disabled>
				</div>
			</div>
			<div class="input-form form-right">
				<button class="btn btn-outline btn-ok" type="submit">저장</button>
			</div>
		</form>
	</section>
</body>
</html>