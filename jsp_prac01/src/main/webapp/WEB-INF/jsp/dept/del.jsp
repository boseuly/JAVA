<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 삭제 페이지</title>
</head>
<body>
	<form action="./del" method="post">
		<div>
			<label>부서 ID</label>
		</div>
		<div>
			<input type="text" value="${data.deptId}" name="deptId" readonly>
		</div>
			<div>
			<label>부서명</label>
		</div>
		<div>
			<input type="text" value="${data.deptName}" name="deptName" readonly>
		</div>
			<div>
			<label>관리자 ID</label>
		</div>
		<div>
			<input type="text" value="${data.mngId}" name="mngId" readonly>
		</div>
			<div>
			<label>지역 ID</label>
		</div>
		<div>
			<input type="text" value="${data.locId}" name="locId" readonly>
		</div>
		<div>
			<button type="submit">삭제</button>
		</div>
	</form>
</body>
</html>