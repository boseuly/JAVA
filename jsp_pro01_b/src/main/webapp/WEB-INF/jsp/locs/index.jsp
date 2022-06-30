<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, locs.model.LocsDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>지역 조회 결과</title>
	<style type="text/css">
		.required-box {
			margin: 0; padding: 0.3rem 0.6rem;
			box-sizing: border-box;
			display: inline;
			position: relative;
			border: 1px solid black;
			border-radius: 4px;
			background-color: black;
			color: white;
			box-shadow: 2px 2px 2px gray;
			opacity: 0;
		}
		.required-box.show {
			opacity: 1;
			transition: opacity 1s;
		}
		.required-box:before {
			content: '';
			position: absolute;
			top: 0; left: 15%;
			width: 0; height: 0;
			border: 6px solid transparent;
			border-bottom-color: black;
			border-top: 0;
			margin-left: -6px; margin-top: -6px;
		}
		/*
		.required-box:after {
			content: '';
			position: absolute;
			top: 0; left: 50%;
			width: 0; height: 0;
			border: 4px solid transparent;
			border-bottom-color: white;
			border-top: 0;
			margin-left: -4px; margin-top: -4px;
		}
		*/
	</style>
</head>
<script type="text/javascript">
window.onload = function() {
	var form = document.forms[0];
	form.addEventListener("submit", formCheck);
}

function formCheck(e) {
	for(element of e.target.querySelectorAll("[data-required]")) {
		if(element.value === "") {
			e.preventDefault();
			if(!document.querySelector(".required-box")) {
				requiredBox(element, element.dataset.required);
			}
			return false;
		}
	}
	this.submit();
}

function requiredBox(element, message) {
	var box = document.createElement("div");
	box.setAttribute("class", "required-box");
	box.innerText = message;
	element.parentElement.append(box);
	
	box.style.left = element.offsetLeft - box.offsetLeft + (element.offsetWidth / 10) + "px";
	box.style.top = element.offsetHeight + 16 + "px";
	box.setAttribute("class", "required-box show");
	
	setTimeout(function() {
		box.remove();
	}, 1500);
}
</script>
<body>
	<h1>지역 조회 결과</h1>
	<div>
		<form action="./locs" method="get">
			<div>
				<input type="text" name="search" data-required="지역코드를 입력하세요.">
				<button type="submit">조회</button>
			</div>
		</form>
	</div>
	<table>
		<tr>
			<th>LocId</th>
			<th>StAddr</th>
			<th>Postal</th>
			<th>City</th>
			<th>State</th>
			<th>CtyId</th>
		</tr>
	<%
		if(request.getAttribute("locsDatas") != null) {
			List<LocsDTO> datas = (List<LocsDTO>) request.getAttribute("locsDatas");
			for(LocsDTO data: datas) {
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
		}
	%>
	</table>
</body>
</html>