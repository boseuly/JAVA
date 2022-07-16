<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>지역 삭제</title>
</head>
<body>
<c:url value="/locs/del" var="locsDel"/>
	<form action="${locsDel}" method="post">
		<div>
			<label>지역ID</label>
			<input type="text" value="${data.locId}" name="locId" readonly>
		</div>
		<div>
			<label>주소</label>
			<input type="text" value="${data.stAddr}" name="stAddr" readonly>
		</div>
		<div>
			<label>우편</label>
			<input type="text" value="${data.postal}" name="postal" readonly>
		</div>
		<div>
			<label>시</label>
			<input type="text" value="${data.city}" name="city" readonly>
		</div>
		<div>
			<label>주</label>
			<input type="text" value="${data.state}" name="state" readonly>
		</div>
		<div>
			<label>국가</label>
			<input type="text" value="${data.ctyId}" readonly>
		</div>
		<div>
			<button type="submit">삭제</button>
		</div>
	</form>
</body>
</html>