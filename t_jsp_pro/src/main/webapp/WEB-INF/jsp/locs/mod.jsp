<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>지역 수정</title>
</head>
<c:url value="/ajax/exists" var="existsUrl"/>
<script type="text/javascript">
function sendAjaxExists(element){
	// 해당 데이터와 요청할 서블릿 url을 전달해야 한다. 
	// 다른 곳에서는 확인 게 두 개여서 따로 분류했지만 여기서는 필요 없다.
	// value와 name을 전달
	$.ajax({
		type : "get",
		url : "${existsUrl}",
		data : {
			name : element.name,
			value : element.value
		},
		success : function (data, status){
			if(data.code === "success"){
				element.innerText = data.message;
				element.setAttribute("class", "input-lable-ok");
			}else if(data.code === "error"){
				element.innerText = data.message;
				element.setAttribute("class", "input-lable-error");
			}
		}
		
		
	});
}
</script>
<body>
	<section class="container">
		<form class="small-form" action="./mod" method="post">
			<input type="hidden" name="locId" value="${data.locId}" readonly> <!-- id는 오직 정보를 찾기 위해서 전달 -->
			<div class="input-form wide">
				<label class="input-label">주소</label>
				<input class="input-text" type="text" name="stAddr" value="${data.stAddr}" data-required="주소를 입력하세요.">
			</div>
			<div class="input-form wide">
				<label class="input-label">우편</label>
				<input class="input-text" type="text" name="postal" value="${data.postal}" data-required="우편을 입력하세요.">
			</div>
			<div class="input-form wide">
				<label class="input-label">시</label>
				<input class="input-text" type="text" name="city" value="${data.city}" data-required="시를 입력하세요.">
			</div>
			<div class="input-form wide">
				<label class="input-label">주</label>
				<input class="input-text" type="text" name="state" value="${data.state}" data-required="주를 입력하세요.">
			</div>
			<div class="input-form wide">
				<label class="input-label">국가ID</label>
				<input class="input-text" type="text" name="ctyId" onblur="existsCheck(this);"
				 value="${data.ctyId}" data-required="국가ID를 입력하세요.">
				<c:if test="${data.ctyId == '-1' ? '' : data.ctyId}"> <!-- 해당 data의 ctyId의 값이 -1인지 확인  -->
					<label class="input-label-error"></label>
				</c:if>
			</div>
			
			<div class="input-form wide form-right">
				<button class="btn btn-outline btn-ok" type="submit">저장</button>
				<button class="btn btn-outline btn-cancel" type="button"
				onclick="location.href='${pageContext.request.contextPath}/locs'">취소</button>
			</div>
		</form>
	</section>
</body>
</html>