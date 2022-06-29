<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSP - request 객체</title>
</head>
<body>
	<h1>JSP - request 객체</h1>
	<h2>getMethod()</h2>
	<p>request.getMethod() : <%=request.getMethod() %></p>
	<form action="./jsp_request" method="get">
		<button type="submit">GET 전송</button>
	</form>
	<form action="./jsp_request" method="POST">
		<button type="submit">POST 전송</button>
	</form>
	<hr>
	
	<h2>getParameter(name) : name 은 input 태그 등에 사용하는 name 속성의 값을 지칭한다. </h2>
	<p>
		request.getParameter(name) : <%=request.getParameter("param_name") %><br>
		name 은 input 태그 등에 사용하는 name 속성의 값을 지칭한다.
	</p>
	<form action="./jsp_request" method="get">
		<div>
			<input type="text" name="param_name">
		</div>
		<div>
			<button type="submit">전송</button>
		</div>
	</form>
	<hr>
	<h2>	request.getParameterValues(name)  </h2>
	<p>
		request.getParameterValues(name) : 
		<%
			if(request.getParameterValues("param_chk") != null){
		%>
				<%= Arrays.asList(request.getParameterValues("param_chk")) %>	<!--여기에 param_chk의 값이 오도록 해라. ( 배열이니까 Arrays.asList에 담아주면 좋다.) -->
		<%
			}
		%>	
		<br>
		 <%-- request.getParameter(name) : <%= request.getParameter("param_name")  %> --%><br> <!-- request.getParameter : 여기에  출력하라고 해서 출력인 된 것이다.  -> 서버에 이런 식으로 데이터 전달 가능-->
		<!-- request.getParameter(name) : request.getParameterValues("param_name")[0]  %><br>  Values는 체크박스에서 많이 사용된다.  --> 
	
	<form action="./jsp_request" method="get">
		<div>
			<input type="checkbox" value="a" name="param_chk"><!--  checkbox 같은 경우에는 request.getParameter("name")[index]사용 -->
			<input type="checkbox" value="b" name="param_chk">
			<input type="checkbox" value="c" name="param_chk">
			<input type="checkbox" value="d" name="param_chk">
		</div>
		<div>
			<button type="submit">전송</button>
		</div>
		<hr>
		<h2>getParameterNames() </h2>
		<p>
			request.getParameterNames() :
			<%
				Iterator<String> iter = request.getParameterNames().asIterator();
				while(iter.hasNext()) {
			%>
					<%=iter.next() %>
		<%} %>
			
			input 태그 등에 사용하는 name 속성의 값을 반환한다.
		</p>
		</form>
		<form action="./jsp_request" method="get">
			<div>
				<input type="text" name="username">
				<input type="text" name="password">
			</div>
			<div>
				<button type="submit">전송</button>
			</div>
		</form>
		<hr>
		<h2>setCharacterEncoding</h2>
		<p>
			클라이언트가 서버에 보낸 한글이 깨질 때 사용한다.
			<%=request.getParameter("username") %>
		</p>
		<form action="./jsp_request" method="post">
				<div>
					<input type="text" name="username">
				</div>
				<div>
					<button type="submit">전송</button>
				</div>
		</form>
	
		<hr>
		<h2>getSession</h2>
		<p>
				세션객체 : <%=request.getSession() %> <br>
				세션 ID : <%=request.getSession().getId() %>
				해당 세션 ID를 저장해 두고, 세션 아이디에 저장되어 있는 사용자 정보를 찾아서 보내준다.	 ->  session을 삭제하면 저장되어 있던 로그인 정보를 다 지워준다.
</body>
</html>