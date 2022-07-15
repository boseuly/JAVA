<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ page import = "java.util.*" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Welcome JSP/Servlet</title>	
	<%@ include file="./module/head.jsp" %>
</head>
<script type="text/javascript"> /*나중에는 파일 따로 만들어서 하기*/
	 function sendAjax() {
		$.ajax({ 					/*다 Object 타입이다.*/
			type:"get", 			// get 아니면 post
			url: "/ajax/test",	 	// ajax를 처리할 url은 서버 주소 
			data : { /*data Object 타입  -> 서버에 전달하고 싶은 데이터를 적는다.*/
				x: 1, y : "A"
			}, 
			dataType: "json", 		// 서버로부터 전달 받을 데이터 타입 json, text, xml, html 등의 타입으로 받을 수 있다. 
			success: function(data, status){
				// 응답이 성공적(응답코드 200인경우)으로 이루어졌을 때 동작할 함수
				console.log("sucess: " + data); // JSON 양식을 받아오고 받아온 데이터의 
				for(d of data){ // 만약 들어오는 값이 List라면 이렇게 for문을 돌려준다.
					console.log("sucess: " + data.empId);		// msg와 
					console.log("sucess: " + data.empName);		// kor 필드를 가지고 온다.
					console.log("sucess: " + data.deptId);
					console.log("sucess: " + data.deptName);
					console.log("sucess: " + data.jobId);
					console.log("sucess: " + data.jobName);
				}
			},
			error: function(data, status){
				// 응답코드 200이 아닌 모든 응답일 때 동작할 함수
				console.log("error 발생");	
				console.log(data);
				console.log(status);
			},
			complete: function(){
				// 성공 실패 유무와 상관없이 동작할 함수
				console.log("complete 무조건 실행");
			}, 
			beforeSend: function() {
				// 서버에 데이터를 전송하기 전에 동작할 함수
				console.log("beforeSend 데이터 전송 전");
			}
		});
	}
</script>
<body>
	<%@ include file="./module/navigation.jsp" %>
	<section class="container">
		<div>
			<button type="button" onclick="sendAjax()">전송</button>
		</div>
		<c:url var="loginUrl" value="/login"/>
		<form action="${loginUrl}" method="post">
			<div>
				<label>직원ID</label>
				<input type="text" name="empId" placeholder="직원 ID를 입력하세요." >
				<c:if test="${not empty error}">
					<label>${error}</label>
				</c:if>
			</div>
			<div>
				<label>부서명</label>
				<select name="empId" data-required="부서명을 선택하세요." >
					<c:forEach items="${deptList}" var="deptDto">
						<option value="${deptDto.deptId}" ${deptDto.deptId == param.deptId ? selected : ""}>
						[${deptDto.deptId}] ${deptDto.deptName}
						</option>
						<c:choose>
							<c:when test="${empty error and cookie.check.value == deptDto.deptId}">
								<option value="${deptDto.deptId}" selected>
									[${deptDto.deptId}] ${deptDto.deptName}
								</option>
							</c:when>
							<c:when test="${not empty error and param.deptId == deptDto.deptId}">
								<option value="${deptDto.deptId}" selected>
									[${deptDto.deptId}] ${deptDto.deptName}
								</option>
							</c:when>
							
							<c:otherwise>
								<option value="${deptDto.deptId}">
									[${deptDto.deptId}] ${deptDto.deptName}
								</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div>
				<label>이름</label>
				<input type="text" name="empName" value="${param.empName}" placeholder="직원명을 입력하세요." >
				<c:if test="${not empty error}">
					<label>${error}</label>
				</c:if>
			</div>
			<div class="input-form wide form-right">
				부서기억하기<input type="checkbox" name="ckeck">
				<button type="submit">로그인</button>
			</div>
		</form>
	</section>
	
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
		<!--  흐름제어 (choose문) -->
		<c:choose>
			<c:when test="${param.x == 'a'}">
				파라미터 x의 값이 a면 실행
			</c:when>
			<c:when test="${param.x == 'b'}">
				파라미터 x의 값이 b면 실행
			</c:when>
			<c:when test="${param.x == 'c' }">
				파라미터 x의 값이 x면 실행
			</c:when>
			<c:otherwise>
				모든 when 조건에 해당하지 않으면 실행됨
			</c:otherwise>
		</c:choose> 
		<hr>
	<ul>
		<c:forEach begin="1" end="5" var="v">
			<li>${v}</li>
		</c:forEach>
	</ul> 
	<%
		List<String> lst = new ArrayList<String>();
		lst.add("a");	lst.add("b"); lst.add("c"); lst.add("d");
		request.setAttribute("lst", lst);
	%>
	<br>
	<ul>
		<c:forEach items="${lst}" var="v">
			<li>${v}</li>
		</c:forEach>
	</ul>
	<br>
	<%
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "가"); map.put("b", "나");map.put("c", "다");
		request.setAttribute("map", map);
	%>
	<ul>
		<c:forEach var="v" items="${map}">
			<li>${v.key} - ${v.value}</li>
		</c:forEach>
	</ul>
	
	<!--  page, request, session, application 등의 저장소를 지정할 수도 있음 -->
	<c:set var="data1" value="Hello" scope="page"/>	 <!--  page : 지역 변수와 유사 -->
	<c:set var="data2" value="Hello" scope="request"/>
	<c:set var="data3" value="Hello" scope="session"/>
	<c:set var="data4" value="Hello" scope="application"/>
	${data1}
	${data2}
	${data3}
	${data4}
	
	<!-- 같은 var(이름)을 사용하더라도 해당 scope를 적어주면 된다. -->
	${pageScope.data1}
	${requestScope.data2}
	${sessionScope.data3}
	${applicationScope.data4}
	
	<!--  배열처럼 사용 -->	
	<c:set var="arr">
		가, 나, 다, 라
	</c:set>
	${arr}
	
	<!-- 저장소에 있는 데이터 지우기 -->
	<c:remove var="data" scope="page"/>
	<c:remove var="data" scope="request"/>
	<c:remove var="data" scope="session"/>
	<c:remove var="data" scope="application"/>
	<p>데이터를 지웠기 때문에 출력되는 값이 없다.</p>
	${pageScope.data}
	${requestScope.data}
	${sessionScope.data}
	${applicationScope.data}
	
	<hr>
	<!-- URL 변수처럼 사용 하기 -->
	<c:url var="url" value="./depts">
		<c:param name="x" value="Hello"/> <!-- 파라미터 설정이 필요하면 이렇게 넣어주면 된다. 자동으로 ? 붙여줌-->
		<c:param name="y" value="bye"/>
	</c:url>
	${url}
	</div>
	
	<p>숫자 Format</p>
	<fmt:formatNumber value="1000"/> 
	<fmt:formatNumber value="0.1" type="percent"/><!-- 퍼센트로 하는 경우는 뒤에 0이 2개 더 붙는다. --> 
	<fmt:formatNumber value="1000" type="currency"/> 
	<fmt:formatNumber value="1000" type="currency" currencySymbol="USD"></fmt:formatNumber>
	<fmt:formatNumber value="1000" type="currency" currencySymbol="$"></fmt:formatNumber>
	<!-- 숫자,날짜 Format은 직원 테이블에서 고용일, 연봉에서 사용될 수 있을 듯! -->
	<p>날짜 Format</p>
	<%
		Date date = new Date();
		request.setAttribute("date", date);
	%>
	<fmt:formatDate value="${date}" type="date"/><br>
	<fmt:formatDate value="${date}" type="date" dateStyle="full"/><br>
	<fmt:formatDate value="${date}" type="date" dateStyle="long"/><br>
	<fmt:formatDate value="${date}" type="date" dateStyle="medium"/><br>
	<fmt:formatDate value="${date}" type="date" dateStyle="short"/><br>
	<fmt:formatDate value="${date}" type="date" pattern="YYYY-MM-dd E EEEE"/><br>	<!-- 내가 원하는 형식으로 출력할 수 있다. -->
	
	<p>시간 Format</p>
	<fmt:formatDate type="time" value="${date}"/><br>
	<fmt:formatDate type="time" value="${date}" timeStyle="full"/><br>
	<fmt:formatDate type="time" value="${date}" timeStyle="long"/><br>
	<fmt:formatDate type="time" value="${date}" timeStyle="medium"/><br>
	<fmt:formatDate type="time" value="${date}" timeStyle="short"/><br>
	<fmt:formatDate type="time" value="${date}" pattern="a hh:mm:ss.SSS"/><br><!-- SSS : 밀리세컨즈 -->
	<fmt:formatDate type="time" value="${date}" pattern="a hh:mm:ss Z z zzzz"/><br><!-- 타임존 zzzz : 대한민국 표준시 -->
	
	<p>날짜 & 시간 Format</p>
	<fmt:formatDate value="${date}" type="both" /><br>
	<fmt:formatDate value="${date}" type="both" dateStyle="full" timeStyle="short"/><br>
	
	
</body>
</html>