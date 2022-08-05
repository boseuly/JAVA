<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>사내 게시판</title>
	<%@include file="../module/head.jsp" %>
</head>
<body>
	<section>
		<div>
			<c:url value="/board" var="boardUrl"/>
			<form action="${boardUrl}" method="get">
				<div class="input-form form-left" >
					<button class="btn btn-outline" type="button" onclick="">추가</button>
				</div>
			</form>
		</div>
	</section>

</body>
</html>