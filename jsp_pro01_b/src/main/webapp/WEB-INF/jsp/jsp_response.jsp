<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
		<h1>JSP - Response 객체</h1>
		<h2>sendRedirect(String url)</h2>
		<%
			//response.sendRedirect("./"); // reflash와 같은 기능(페이지 납치)
		%>
		<p>
			클라이언트에게 다른 주소로 재요청을 하게 만들기 위해 사용하는 기능<br>
			http 상태코드 : 302
		</p>
		<hr>
		<h2>setStatus(int statusCode)</h2>
		<%
			//response.setStatus(HttpServletResponse.SC_ACCEPTED); // 해당 상태코드로 바꾸겠다.
		%>
		<p></p>
		<hr>
		<h2>sendError(int statusCode)</h2>
		<p></p>
		<%
			//response.sendError(HttpServletResponse.SC_NOT_FOUND, "잘못된 URL 주소로 요청 하였습니다."); // 에러 페이지를 보내는 거 setStatus는 상태코드만 바뀌지만 얘는 페이지까지 에러남
		%>
		
		<hr>
		<h2>setContentType(String mimeType)</h2>
		<p></p>
		<%
			// response.setContentType("text/javascript"); // html코드를 해석하지 않고 javascript코드로 보게 한 거다.  -> 브라우저에는 코드가 그대로 화면에 나온다. (AJAX에서 사용 됨)
		%>
		<hr>
		
</body>
</html>