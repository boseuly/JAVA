<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>상세 페이지</title>
</head>
<script type="text/javascript">
	window.onload = function() {
		var form = document.forms[0];
		form.email.addEventListener("input", enableSaveButton);
		form.phone.addEventListener("input", enableSaveButton);
		
		prevImage.addEventListener("click", function(e) {
			btnImage.click();
		});
		
		btnImage.addEventListener("change", ajaxUploadImage);
	}
	
	function ajaxUploadImage(e) {
		var file = e.target.files[0];
		var fData = new FormData();
		fData.append("uploadImage", file, file.name);
		console.log(fData);
		$.ajax({
			type: "post",
			url: "/ajax/imageUpload",
			enctype: "multipart/form-data",
			data: fData,
			processData: false,
			contentType: false,
			success: function(data, status) {
				prevImage.src = data.loc;
			},
			error: function(data, status) {
				prevImage.src = data.loc;
			}
		});
	}
	
	function showPreview(e) {
		var file = e.target.files[0];
		var imgUrl = URL.createObjectURL(file);
		prevImage.src = imgUrl;
	}
	
	function enableSaveButton(e) {
		var submit = document.querySelector("button[type='submit']");
		var enable = submit.getAttribute("class").replace("disable", "");
		submit.setAttribute("class", enable);
	}
</script>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section>
		<c:url var="updateUrl" value="/myinfo"/>
		<form action="${updateUrl}" method="post" enctype="multipart/form-data">
			<div>
				<c:url var="imgUrl" value="${imagePath}"/> <!-- 서블릿에서 찾아둔 사원 이미지 경로 -->
				<img alt="prevImage" alt="여기에는 증명 사진이 배치됩니다." src="${imgUrl}">
				<br>
				<input type="file" id="btnImage" name="uploadImage"> <!-- 이미지를 변경할 수도 있으니까  -->
			</div>
			<div> <!-- ID&이름 -->
				<div>
					<label>ID</label>
					<input type="text" name="empId" value="${sessionScope.loginData.empId}"> <!-- user의 empID -> session에 로그인 할 때 저장해둠 -->
				</div>
				<div>
					<label>이름</label>
					<input type="text" name="empName" value="${sessionScope.loginData.empName}">
				</div>
			</div>
			<div> <!-- 부서&직급 -->
				<div>
					<label>직급</label>
					<select name="jobId" disabled> <!-- disabled -> 사용하지 못하게 , readOnly -> 읽기만 가능 -->
						<c:forEach var="job" items="${jobDatas}"> <!-- 저장해둔 데이터를 뽑아내야 함 -->
							<c:choose>
								<c:when test="${job.jobId == sessionScope.loginData.jobId}"> <!-- 세션에 저장된 jobId 와 같으면 selected!! -->
									<option value="${job.jobId}" selected>[${job.jobId}] ${job.jobName}</option> <!-- value는 jobId이지만 보이는 건 jobName임  -->
								</c:when>
								<c:otherwise>
									<option value="${job.jobId}">[${job.jobId}] ${job.jobName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
				<div>
					<label>부서</label>
					<select name="deptId" disabled>
						<c:forEach items="${deptDatas}" var="dept">
							<c:choose>
								<c:when test="${dept.deptId == sessionScope.loginData.deptId}">
									<option value="${dept.deptId}" selected >${dept.deptName}</option>
								</c:when>
								<c:otherwise>
									<option value="${dept.deptId}">${dept.deptName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			<div>
				<div>
					<label>이메일</label>
					<input type="text" name="email" value="${sessionScope.loginData.email}">
				</div>
			</div>
			<div>
				<div>
					<fmt:formatDate value="${empDetail.hireDate}" var="fmtHireDate" dateStyle="long"/>
					<label>입사일</label>
					<input type="text" name="hireDate" value="${fmtHireDate}" disabled> <!--  수정하지 못하도록 -->
				</div>
				<div>
					<label>전화번호</label>
					<input type="text" name="phone" value="${empDetail.phone}">
				</div>
			</div>
			<div>
				<div>
					<fmt:formatNumber value="${empDetail.salary}" var="fmtSalary"/>
					<label>급여</label>
					<input type="text" name="salary" value="${fmtSalary}" disabled>
				</div>
				<div>
					<fmt:formatNumber var="fmtcommission" value="${empDetail.commission}" type="percent" />
					<label>커미션</label>
					<input type="text" name="commission" value="${fmtcommission}" disabled>
				</div>
			</div>
			<div>
				<button type="submit">저장</button>
			</div>
		</form>
	</section>
</body>
</html>