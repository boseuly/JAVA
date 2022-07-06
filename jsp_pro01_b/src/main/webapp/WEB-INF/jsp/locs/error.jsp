<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오류</title>
</head>
<body>
	<%
		String errorMsg = (String)request.getAttribute("errorMsg");
	%>
	<p><%=errorMsg %></p>
	<div>
		<button type="button" onclick="location.href = '../locs'">돌아가기</button>
	</div>
</body>
</html>