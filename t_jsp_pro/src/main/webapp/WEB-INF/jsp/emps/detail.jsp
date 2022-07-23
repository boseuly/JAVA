<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../module/head.jsp" %>
	<meta charset="UTF-8">
	<title>직원 추가</title> <!--  여기 완성 안 됨 7.21 -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
	rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
		<div class="large-form">
			<div class="img-form left">
				<c:url var="imgUrl" value="/static/img/emp/${data.empId}.png" />
				<img class="img-360" alt="여기에는 증명 사진이 배치됩니다." src="/static/img/emp/default.png">
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">ID</label>
					<input class="input-text w-auto" type="text" name="empId" value="${data.empId}" disabled>
				</div>
				<div class="input-form">
					<label class="input-label w-100">이름</label>
					<input class="input-text w-auto" type="text" name="empName" value="${data.empName}" disabled>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">직급</label>
						<select class="select-form w-auto" name="jobId" disabled>
							<c:forEach items="${jobDatas}" var="job">
								<c:choose>
									<c:when test="${job.jobId eq data.jobId}">
										<option value="${job.jobId}" selected>${job.jobName}</option>
									</c:when>
								</c:choose>
								<c:otherwise>
									<option value="${job.jobId}">${job.jobName}</option>
								</c:otherwise>
							</c:forEach>
						</select>
					</div>
					<div class="input-form">
						<label class="input-label w-100">부서</label>
						<select class="select-form w-auto" name="deptId" >
							<c:forEach items="${deptDatas}" var="dept">
								<c:choose>
									<c:when test="${dept.deptId == data.deptId}"> <!--  eq 랑 == 같음 거임 -->
										<option value="${dept.deptId}" selected>[${dept.deptId}] ${dept.deptName}</option>
									</c:when>
									<c:otherwise>
										<option value="${dept.deptId}">[${dept.deptId}] ${dept.deptName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">이메일</label>
					<input class="input-text w-auto" type="text" name="email" value="${data.email}"  disabled>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<fmt:formatDate var="fmtHireDate" value="$(dataDetail.hireDate}" pattern="YYYY-MM-dd" dateStyle="long" />
					<label class="input-label w-100">입사일</label> 	<!--  에러를 피하기 위해서 미리 값을 넣어준다. -> 오늘 날짜로!! -->
					<input class="input-text w-auto" type="text" name="hireDate" value="${fmtHireDate}" disabled >
				</div>
				<div class="input-form">
					<label class="input-label w-100">전화번호</label>
					<input class="input-text w-auto" type="text" name="phone" value="${dataDetail.phone}"  disabled>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">급여액</label>
					<input class="input-text w-auto" type="text" name="salery" value="${dataDetail.salary}" disabled >
				</div>
				<div class="input-form">
					<label class="input-label w-100">커미션</label>
					<input class="input-text w-auto" type="text" name="commission" value="${dataDetail.commission}" disabled>
				</div>
			</div>
			<div class="input-form form-right">
				<c:url var="empModUrl" value="/emps/modify">
					<c:param name="id" value="${data.empId}"/>
				</c:url>
				<c:url var="empDelUrl" value="/emps/delete">
					<c:param name="id" value="${data.empId}"/>
				</c:url>
				<button class="btn btn-outline btn-ok" type="button" onclick="location.href='${empModUrl}'">수정</button>
				<button class="btn btn-outline btn-ok disable" type="button"onclick="location.href='${empDelUrl}'"  data-bs-tartget="#deleteModal" data-bs-toggle="modal" >삭제</button>
			</div>
		</div>
	</section> <!--  삭제 버튼에 대한  -->
	<div class="modal" tabindex="-1" id="deleteModal">
		  <div class="modal-dialog modal-dialog-centered"> <!-- 화면 가운데 나오도록 함 -->
			    <div class="modal-content">
				      <div class="modal-header">
					        <h5 class="modal-title">직원 삭제</h5>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				       	 	<p>해당 직원의 정보를 삭제하시겠습니까?</p> 
				      </div>
				      <div class="modal-footer">
					  		<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
					        <button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="empDelete(${data.empId});">삭제</button>
				      </div>
			    </div>
		  </div>
	</div>
	
	<div class="modal" tabindex="-1" id="resultModal">
		  <div class="modal-dialog modal-dialog-centered"> <!-- 화면 가운데 나오도록 함 -->
			    <div class="modal-content">
				      <div class="modal-header">
					        <h5 class="modal-title">직원 삭제</h5>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				       	 	<p>해당 직원의 정보를 삭제하시겠습니까?</p> 
				      </div>
				      <div class="modal-footer">
					  		<button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="location.href='/emps'">확인</button> <!-- 확인 버튼을 누르면 emp 페이지로 이동 -->
				      </div>
			    </div>
		  </div>
	</div>
</body>
<script type="text/javascript">
function empDelete(empId) {
	$.ajax({
		type: "post", 
		url : "/ajax/delete",
		data : {
			id : empId, 
			type : "emp"
		},
		dataType : "json", // 안 써도 됨
		success : function (data) {  // myModal의 속성을 알고 싶으면 complete로 바꾸고 콘솔에 찍어보기
			var myModal = new Bootstrap.Modal(document.getElementById("resultModal"), {
				keyboard : false
			});
			var title = myModal._element.querySelector(".modal-title");
			var body = myModal._element.querySelector(".modal-body");
			
			console.log(myModal._element); // 이렇게 찍어보면서 확인을 한 다음에 내가 어디에 메시지를 넣을 건지 정해야 한다.
			
			// 우선 잘 되는지 확인 -> 한 번에 하는 것보다는 단계단계 확인 하면서 하기
//			title.innerText = "값 변경 확인"; 
//			body.innerHTML = "<p>" + "데이터 변경 확인을 하였습니다." + "</p>"
			
			// 잘 되는지 확인 후에는 서버에서 전달한 메시지를 body에 넣는다. 
			title.innerText = data.title;
			body.innerHTML = "<p>" + data.message + "</p>"
			myModal.show();
		}
	})
}
</script>
</html>