<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="paging">
	<c:set var="currentPage" value="${page}"/>	<!-- 파라미터 page를 의미 -->
	<c:set var="prevPage" value="${currentPage - 1}"/>
	<c:set var="nextPage" value="${currentPage + 1}"/>
	
	<ul class="page center">
		<li class="page-item">
			<c:choose>
				<c:when test="${prevPage <= 0}">
					<a class="page-link disabled material-symbols-outlined" href="#">keyboard_arrow_left</a>
				</c:when>
				<c:otherwise>
					<a class="page-link material-symbols-outlined" href="./depts?page=${prevPage}">keyboard_arrow_left</a>
				</c:otherwise>
			</c:choose>
		</li>
		<c:forEach items="${pageList}" var="item">
				<li class="page-item">
				<a class="page-link${currentPage == item ? ' active' : '' }" href="./depts?page=${item}">${item}</a>
				</li>
		</c:forEach>
		<li class="page-item">
			<c:choose>
				<c:when test="${nextPage > pageList.size()}"> <!-- 다음 페이지가 전체 페이지 갯수보다 크다면 선택 못 하도록 -->
					<a class="page-link disabled material-symbols-outlined" href="#">keyboard_arrow_right</a>
				</c:when>
				<c:otherwise>
					<a class="page-link material-symbols-outlined" href="./depts?page=${nextPage}">keyboard_arrow_right</a>
				</c:otherwise>
			</c:choose>
		</li>
	</ul>
</div>