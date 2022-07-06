<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="locs.model.LocsDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지역 삭제</title>
</head>
<body>
	<h3>해당 지역을 삭제하시겠습니까?</h3>
	<%
		LocsDTO data = (LocsDTO)request.getAttribute("data");
	%>
	<form action="./del" method="post">
		<div>
			<input type="text" name="locId" value="<%=data.getLocId()%>">
		</div>
		<div>
			<input type="text" name="stAddr" value="<%=data.getStAddr()%>">
		</div>
		<div>
			<input type="text" name="postal" value="<%=data.getPostal()%>">
		</div>
		<div>
			<input type="text" name="city" value="<%=data.getCity()%>">
		</div>
		<div>
			<input type="text" name="state" value="<%=data.getState()%>">
		</div>
		<div>
			<input type="text" name="ctyId" value="<%=data.getCtyId()%>">
		</div>
		<div>
			<button type="submit">삭제</button>
			<button type="button" onclick="history.back();">돌아가기</button>
		</div>
		
	
	</form>
</body>
</html>