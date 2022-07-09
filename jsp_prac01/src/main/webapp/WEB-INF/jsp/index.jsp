<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 화면</title>
</head>
<body>
	<h1>메인 화면</h1>
	<hr>
	<div>
		<ul>
			<li><a href="${pageContext.request.contextPath}/dept">부서 조회</a></li>
			<li><a href="${pageContext.request.contextPath}/locs">지역 조회</a></li>
		</ul>
	</div>
</body>
</html>