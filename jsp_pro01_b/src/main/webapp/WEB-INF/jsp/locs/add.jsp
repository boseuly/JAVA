<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지역 추가</title>
</head>
<body>
	<div>
		<div>
			<h1>지역 추가</h1>
		</div>

		<form action="./add" method="post">
			<div>
				<input type="text" name="locId" placeholder="지역 ID">
			</div>
			<div>
				<input type="text" name="stAddr" placeholder="주소">
			</div>
			<div>
				<input type="text" name="postal"  placeholder="우편">
			</div>
			<div>
				<input type="text" name="city" placeholder="도시">
			</div>
			<div>
				<input type="text" name="state" placeholder="주">
			</div>
			<div>
				<input type="text" name="ctyId" placeholder="국가 ID">
			</div><br>
			<div>
				<button type="submit">추가</button>
			</div>
		</form>
	</div>
</body>
</html>