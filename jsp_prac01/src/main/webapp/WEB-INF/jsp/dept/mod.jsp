<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 수정</title>
</head>
<body>
	<form action="./mod" method="post">
		<section>
			<div hidden>
				<label>부서 ID</label>
			</div>
			<div hidden>
				<input type="text" name="deptId" value="${data.deptId}">
			</div>
			<div>
				<label>부서명</label>
			</div>
			<div>
				<input type="text" value="${data.deptName }" name="deptName" required>
			</div>
			<div>
				<label>관리자 ID</label>
			</div>
			<div>
				<input type="text" value="${data.mngId }" name="mngId" required>
			</div>
			<div>
				<label>지역 ID</label>
			</div>
			<div>
				<input type="text" value="${data.locId }" name="locId" required>
			</div>
		</section>
		<div>
			<button type="submit">수정</button>
		</div>
	</form>
</body>
</html>