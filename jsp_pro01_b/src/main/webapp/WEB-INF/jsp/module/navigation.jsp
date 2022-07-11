<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String url = "";
	if(request.getAttribute("url") != null) {		// 현재 나의 위치를 알아낸다. 
		url = (String)request.getAttribute("url");	// request.getAttribute("url") url에 path 정보가 들어갈 수 있도록 해놓음
	}	// 모듈화 할 때 반드시 setAttribute()로 적용해주기 그냥 단순히 변수명 지정해서 하는 것보다
%>
<header>
	<nav class="top-nav center">
		<ul class="nav">
			<li class="nav-item dropdown<%=url.contains("/jsp_") ? " active" : "" %>"> <!--  contains를 통해서 /jsp_가 포함 되어있는지 확인 후 포함되어 있으면 해당 nav를 active 시키기 -->
				<a class="nav-link" href="#">JSP/Servlet</a>
				<ul class="nav dropdown-nav">
					<li class="nav-item">
						<a class="nav-link" href="./jsp_script">스크립트 태그</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./jsp_request">request 객체</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./jsp_response">response 객체</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./jsp_model2">Model2</a>
					</li>
				</ul>
			</li>
			<li class="nav-item<%=url.contains("/depts") ? " active" : "" %>"> <!--  만약 전달받은 url에 /depts라는 단어가 포함되어 있으면 active해라 -> css 인듯 -->
				<a class="nav-link" href="./depts">부서</a>
			</li>
			<li class="nav-item<%=url.contains("/locs") ? " active" : "" %>">
				<a class="nav-link" href="./locs">지역</a>
			</li>
			<li class="nav-item<%=url.contains("/emps") ? " active" : "" %>">
				<a class="nav-link" href="./emps">직원</a>
			</li>
		</ul>
	</nav>
</header>