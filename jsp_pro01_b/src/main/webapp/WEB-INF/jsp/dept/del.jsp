<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dept.model.DeptDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 확인</title> <!--  이건 삭제할 데이터 확인 페이지임 -->
</head>
<body>
	<%
		DeptDTO data = (DeptDTO)request.getAttribute("data");
	%>
		<ul>
			<li>부서명 : <%=data.getDeptId() %></li>
			<li>부서코드 : <%=data.getDeptName() %></li>
		</ul>
		<p>삭제할 데이터가 맞습니까?</p>
		<div>
			<button type="submit" form="deleteForm">삭제</button>
			<button type="button" onclick="history.back();">취소</button>
		</div>
		<form id="deleteForm" action="./del" method="post"> <!--  삭제 요청을 보내면 -->
			<input type="hidden" name="deptId" value="<%=data.getDeptId()%>">
			<!--  원래는 버튼은 form 안에 넣어줘야 하지만  -->
		</form>
</body>
</html>