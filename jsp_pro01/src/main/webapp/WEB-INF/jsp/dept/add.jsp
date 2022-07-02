<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dept.model.DeptDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 추가</title>
</head>
<body>
	<h1>부서 추가</h1>
	<%
		String deptId ="" ;  			// 에러 항목은 입력값이 없는 상태로 초기화를 해준 것이다.
		String deptName = "";
		String mngId = "";
		String locationId = "";
		if(request.getAttribute("error") != null){ // 부서코드 중복 에러가 났다는 의미
			DeptDTO dto = (DeptDTO)request.getAttribute("error");
			deptId = dto.getDeptId() == -1 ? "" : String.valueOf(dto.getDeptId()); // 제약조건 위배라면 -1 -> -1이면 빈공간 을 
			deptName = dto.getDeptName();
			mngId =  dto.getMngId() == -1 ? "" : String.valueOf(dto.getMngId());
			locationId = dto.getLocationId() == -1 ? "" : String.valueOf(dto.getLocationId());
	%>

	<script type="text/javascript">
		alert("<%=request.getAttribute("errorMsg") %>");
	</script>
	
	<%
		}
	%>
	<form action="./add" method="post"><!--  DeptController에서 DeptAddController 로 이동하는 거니까 같은 디렉토리에 있으니까 ./add 만 써주면 된다. 원래는 /depts/add (서블릿 url)-->
		<div>																		<!-- 원래는 value에 getParameter 를 넣었는데 초기값이 null이 나와서 변수를 생성한 다음 변수를 넣어줌 -->
			<input type="text" name="deptId" value="<%=deptId%>" placeholder="부서 ID"> <!--  헷갈리지 않게 멤버변수와 이름을 같게 함 -->
		</div>                                           							<!--  사용자가 입력했던 데이터를 보존 시킴 -->
		<div>																		<!--  value에 초기값을 설정함 -->
			<input type="text" name="deptName" value="<%=deptName %>" placeholder="부서 이름">
		</div>
		<div>
			<input type="text" name="mngId" value="<%=mngId %>"  placeholder="관리자 ID">
		</div>
		<div>
			<input type="text" name="locationId" value="<%=locationId %>" placeholder="지역 ID">
		</div>
		<div>
			<button type="submit">저장</button>
		</div>
	</form>
</body>
</html>