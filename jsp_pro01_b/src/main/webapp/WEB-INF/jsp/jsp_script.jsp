<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%-- jsp의 주석 --%>
   <%-- 
   <%!  %> : 선언문			-> 멤버변수/멤버함수 선언 할 때 
   <%=  %> : 표현식   	 	-> 출력문(값을 출력할 때)
   <%  %> : 스트립트릿 -> 지역변수/지역로직을 구현하고자 할 때 
    이렇게 만들어주면 jsp 파일을 java 코드로 만들어주고 class 파일로 만들어준다.
    --%> 
    
    
    <%@ page import="java.util.Random" %> 
    <%!
    	public Random rd = new Random(); // 선언문 
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>JSP - Script</title>
</head>
<body>
	<h1>JSP - Script</h1>
	<ul>
	<!--  화면 로직 처리 -->
	<%
		for(int i = 0 ; i < 6; i++){
			int number = rd.nextInt(100); // 스트립트릿으로 html 에 출력하라고 명령한 거임
		%>
					<li><%=number %></li> <!--  for 반복문 안에 li태그가 사용된 것 -->
	<%	
		}
	%>
	</ul>
	<div>
			<input type="text" value="<%=rd.nextInt(100) %>"> <!-- 표현식을 이용해서 value의 속성값을 사용할 수도 있다. -->
	</div>
</body>
</html>