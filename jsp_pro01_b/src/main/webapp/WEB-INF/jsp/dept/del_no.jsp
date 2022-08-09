<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.myhome.web.dept.model.DeptDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 확인</title>
</head>
<body>
	<%
		DeptDTO data = null;
		if(request.getAttribute("data") != null){ // data가 없을 수도 있으니까
			data = (DeptDTO)request.getAttribute("data");
			
		}
		if(data == null){ // 만약 삭제할 데이터가 null이라면 아래와 같이
	%>
		<p>삭제할 데이터가 존재하지 않습니다.</p>
		<div>
			<button type="button" onclick="location.back();">돌아가기</button>
		</div>
	<%
		}else {
	%>
		<ul>
			<li>부서명 : <%=data.getDeptId() %></li>
			<li>부서코드 : <%=data.getDeptName() %></li>
		</ul>
		<p>삭제할 데이터가 맞습니까?</p>
		<div>
			<button type="button">삭제</button>
			<button type="button" onclick="history.back();">취소</button>
		</div>
	<%
		}
	%>
</body>
</html>