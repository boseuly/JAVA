<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="paging">
	<c:set var="currentPage" value="${page}" />
	<c:set var="prevPage" value="${currentPage - 1}" />
	<c:set var="nextPage" value="${currentPage + 1}" />
	<c:set var="maxPage" value="${pageList.get(pageList.size() - 1)}" />
	<ul class="page center">
		<li class="page-item">
			<c:choose>
				<c:when test="${prevPage <= 0}">
					<a class="page-link disabled material-symbols-outlined" href="#">keyboard_arrow_left</a>
				</c:when>
				<c:otherwise>
					<a class="page-link material-symbols-outlined" href="${pageUrl}?page=${prevPage}">keyboard_arrow_left</a>
				</c:otherwise>
			</c:choose>
		</li>
		<c:forEach begin="${currentPage - 2 <= 0 ? 1 : currentPage - 2}" end="${currentPage + 2 > maxPage ? maxPage : currentPage + 2}" var="item">
			<li class="page-item">
				<a class="page-link ${currentPage == pageList.get(item-1) ? ' active' : ''}"
					href="${pageUrl}?page=${pageList.get(item-1)}">${pageList.get(item-1)}</a>
			</li>
		</c:forEach>
		<li class="page-item">
			<c:choose>
				<c:when test="${nextPage > pageList.size()}">
					<a class="page-link disabled material-symbols-outlined" href="#">keyboard_arrow_right</a>
				</c:when>
				<c:otherwise>
					<a class="page-link material-symbols-outlined" href="${pageUrl}?page=${nextPage}">keyboard_arrow_right</a>
				</c:otherwise>
			</c:choose>
		</li>
	</ul>
</div>