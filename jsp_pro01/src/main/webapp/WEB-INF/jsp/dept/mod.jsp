<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dept.model.DeptDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 수정</title>
</head>
<body>
	<h1>부서 수정</h1>
	<script type="text/javascript">
		alert("<%=request.getAttribute("errorMsg")%>");
	</script>
	<%
		if(request.getParameter("errorMsg") != null){
	%>
			<script type="text/javascript">
				alert("<%=request.getAttribute("errorMsg")%>");
			</script>
	<%
		}
	%>
	<%
		DeptDTO data = (DeptDTO) request.getAttribute("data");
	%>
	<form action="./mod" method="post">
		<input type="hidden" name="deptId" value="<%=data.getDeptId() %>" placeholder="부서 ID" readonly> 
		<div>																		
			<input type="text" name="deptName" value="<%=data.getDeptName() %>" placeholder="부서 이름">
		</div>
		<div>
			<input type="text" name="mngId" value="<%=data.getMngId() %>"  placeholder="관리자 ID">
		</div>
		<div>
			<input type="text" name="locationId" value="<%=data.getLocationId() %>" placeholder="지역 ID">
		</div>
		<div>
			<button type="submit">수정</button>
		</div>
	</form>
</body>
</html>