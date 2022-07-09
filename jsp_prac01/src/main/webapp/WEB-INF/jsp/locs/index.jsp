<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="locs.model.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지역 조회</title>
</head>
<body>
	<h1>지역 조회</h1>
	<hr>
	<div>
		<button type="button" onclick="location.href='./dept'">부서 조회로 이동</button>
	</div>
	<form action="./locs" method="get">
		<input type="text" name="search">
		<button type="submit">조회</button>
	</form>
	<table>
		<tr>
			<th>LocId</th>
			<th>StAddr</th>
			<th>Postal</th>
			<th>City</th>
			<th>State</th>
			<th>Country</th>
		</tr>
	<%
		List<LocsDTO> datas =(List<LocsDTO>) request.getAttribute("datas");
		for(LocsDTO data : datas){
	%>
		<tr>
			<td><%=data.getLocId() %></td>
			<td><%=data.getStAddr() %></td>
			<td><%=data.getPostal() %></td>
			<td><%=data.getCity() %></td>
			<td><%=data.getState() %></td>
			<td><%=data.getCtyId() %></td>
		</tr>
	<%
		}
	%>
	</table>
	
</body>
</html>