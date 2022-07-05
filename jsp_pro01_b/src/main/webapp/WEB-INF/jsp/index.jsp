<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Welcome JSP/Servlet</title>			
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/default.css">
</head>
<body>
	<div class="outside-container">
		<div class="head-container">
			<h1>Welcome JSP/Servlet</h1>
		</div><br><hr>
		<div class="ul-container">
			<ul class="ul-list">
				<li><a href="./jsp_script">JSP - 스크립트 태그</a></li>
				<li><a href="./jsp_request">JSP/Servlet - request 객체</a></li>
				<li><a href="./jsp_response">JSP/Servlet - response 객체</a></li>
				<li><a href="./model2">JSP/Servlet - Model2</a></li>
				<li><a href="./depts">부서 조회</a></li>
				<li><a href="./locs">지역 조회</a></li>
			</ul>
		</div>
	</div>
</body>
</html>