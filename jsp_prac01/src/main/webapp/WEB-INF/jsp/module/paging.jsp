<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="paging">
	<c:set var="currentPage" value="${page}"/>	<!-- 변수설정 -->
	<c:set var="prevPage" value="${currentPage -1}"/>
	<c:set var="nextPage" value="${currentPage + 1}"/>
	<ul>
		<!-- 이전페이지로 이동하기 링크 -->
		<li>
			<c:choose>
				<c:when test="${prevPage <= 0}"> <!-- 만약 이전 페이지가 존재하지 않는다면 -->
					<a href="#">keyboard_arrow_left</a> <!-- #은 클릭시 이벤트가 발생하지 않도록 하기 위함이다.(현재 페이지를 유지하기 위함) -->
				</c:when>
				<c:otherwise>
					<a href="./dept?page=${prevPage}">keyboard_arrow_left</a> 
				</c:otherwise>
			</c:choose>
		</li>
		<!-- 페이지 선택 --> <!-- pageList의 타입 List<Integer> -->
		<c:forEach items="${pageList}" var="item"><!-- 페이지 리스트의 요소 하나하나를 item에 저장한다. -->
			<li>
				<a href="./dept?page=${item}">${item}</a>
			</li>
		</c:forEach>
		
		<!-- 다음페이지로 이동하기 링크 -->
		<li>
			<c:choose>
				<c:when test="${nextPage > pageList.size()}"> <!-- 만약 nextPage가 pageList의 길이와 같다면 -->
					<a href="#">keyboard_arrow_left</a> <!-- #은 클릭시 이벤트가 발생하지 않도록 하기 위함이다.(현재 페이지를 유지하기 위함) -->
				</c:when>
				<c:otherwise>
					<a href="./dept?page=${nextPage}">keyboard_arrow_left</a> 
				</c:otherwise>
			</c:choose>
		</li>
	</ul>
</div>