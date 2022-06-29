<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <!--  html 태그와 jsp 태그(지시자)가 함께 사용된다. -->
   <%-- jsp의 주석 --%>
   <%-- 
   <%!  %> : 선언문
   <%=  %> : 표현식
   <%  %> : 스트립트릿
    --%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Welcome JSP/Servlet</title>
</head>
<body>
		<h1>Welcome JSP/Servlet</h1>
		<ul>
			<li><a href="./jsp_script">JSP - 스크립트 태그</a></li> <!--  해당 url 주소에 매치가 되는 서블릿을 만들어준다. -->
			<li><a href="./jsp_request">JSP/Servlet_request 객체</a></li>
			<li><a href="./jsp_response">JSP/Servlet_response 객체</a></li>
			<li><a href="./model2">JSP/Servlet - Model2</a></li>
		</ul>
</body>
</html>