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
	<c:set var="maxPage" value="${pageList.get(pageList.size() - 1)}"></c:set>
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
		</li>								<!-- 현재페이지가 왼쪽에 나오고 5개씩 페이지 리스트가 나오도록 설정 -->
		<%--c:forEach begin="${currentPage - 1 <= 0 ? 1:currnetPage - }" end="${currentPage + 4 > maxPage ? maxPage : currentPage + 4}" var="item" --%>
											<!--  항상 현재 페이지가 가운데에 오고 5개씩 페이지 리스트가 나오도록 설정  -->
		<c:forEach begin="${currentPage - 2 <= 0 ? 1:currnetPage - 2}" end="${currentPage + 2 > maxPage ? maxPage : currentPage + 4}" var="item">	 <!-- +4했을 때 maxPage를 넘어서면 maxPage를 넘지 않도록 end=maxPage로 설정 -->
			<li class="page-item">			<!-- -1 해주는 이유 : 페이지는 1부터 시작하지만 index는 0부터 시작해서 (pageList는 arrayList<Integer>임) -->
				<a class="page-link${currentPage == pageList.get(item-1) ? ' active' : '' }" href="${pageUrl}?page=${pageList.get(item-1)}">${pageList.get(item-1)}</a>
			</li>  							<!-- begin ~ end 번만 반복해라 5개가 나오게 하려면 4를 해줘야 한다. -->
		</c:forEach>
		<li class="page-item">
			<c:choose>
				<c:when test="${nextPage > pageList.size()}"> <!-- 다음 페이지가 전체 페이지 갯수보다 크다면 선택 못 하도록 -->
					<a class="page-link disabled material-symbols-outlined" href="#">keyboard_arrow_right</a>
				</c:when>
				<c:otherwise>
					<a class="page-link material-symbols-outlined" href="${pageUrl}?page=${nextPage}">keyboard_arrow_right</a>
				</c:otherwise>
			</c:choose>
		</li>
	</ul>
</div>