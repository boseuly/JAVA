<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="locs.model.LocsDTO" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지역 수정</title>
</head>
<body>
	<%
		LocsDTO data = (LocsDTO)request.getAttribute("data");
	%>
	<%
		if(request.getAttribute("errorMsg") != null) {
	%>
			<script type="text/javascript">
				alert("<%=request.getAttribute("errorMsg") %>");
			</script>
	<%
		}
	%>
	
	<form action="./mod" method="post"><!--  내가 이미 locs/mode 이기때문에 locs는 쓰면 중복된다. -->
		<div>
			<input type="text" name="locId" value="<%=data.getLocId() %>" placeholder="지역 ID" readonly>
		</div>
		<div>
			<input type="text" name="stAddr" value="<%=data.getStAddr() %>" placeholder="주소">
		</div>
		<div>
			<input type="text" name="postal" value="<%=data.getPostal() %>" placeholder="우편">
		</div>
		<div>
			<input type="text" name="city" value="<%=data.getCity() %>" placeholder="도시">
		</div>
		<div>
			<input type="text" name="state" value="<%=data.getState() %>" placeholder="주">
		</div>
		<div>
			<input type="text" name="ctyId" value="<%=data.getCtyId() %>" placeholder="국가 ID">
		</div>
		<div>
			<button type="submit">수정</button>
		</div>
	</form>
</body>
</html>