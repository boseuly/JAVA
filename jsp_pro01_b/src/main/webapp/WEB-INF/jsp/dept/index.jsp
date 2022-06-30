<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dept.model.DeptDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 조회 결과</title>
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
			transition: opacity 0.5s;
		}
		.required-box.show {
			opacity: 1;
			transition: opacity 0.5s;
		}
		.required-box:after {
			content: '';
			position: absolute;
			top: 0; left: 15%;
			width: 0; height: 0;
			border: 6px solid transparent;
			/* border-bottom-color: white; */
			border-top: 0;
			margin-left: -6px; margin-top: -6px;
		}
		.required-box:before {
			content: '';
			position: absolute;
			top: 0; left: 15%;
			width: 0; height: 0;
			border: 7px solid transparent;
			border-bottom-color: black;
			border-top: 0;
			margin-left: -7px; margin-top: -7px;
		}
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
	<h1>부서 조회 결과</h1>
	<div>
		<button type="button" onclick="location.href='./depts/add'">추가</button>
	</div>
	<div>
		<form action="./depts" method="get">
			<div>
				<input type="text" name="search" data-required="부서코드를 입력하세요.">
				<button type="submit">조회</button>
			</div>
		</form>
	</div>
	<table>
		<tr>
			<th>DeptId</th>
			<th>DeptName</th>
			<th>MngId</th>
			<th>LocId</th>
			<th></th>
		</tr>
	<%
		if(request.getAttribute("deptDatas") != null) {
			List<DeptDTO> datas = (List<DeptDTO>) request.getAttribute("deptDatas");
			for(DeptDTO data: datas) {
	%>
				<tr>
					<td><%=data.getDeptId() %></td>
					<td><%=data.getDeptName() %></td>
					<td><%=data.getMngId() %></td>
					<td><a href="./locs?search=<%=data.getLocId() %>"><%=data.getLocId() %></a></td>
					<td>
						<button type="button" onclick="location.href='./depts/mod?id=<%=data.getDeptId() %>'">수정</button>
					</td>
				</tr>
	<%
			}
		}
	%>
	</table>
</body>
</html>