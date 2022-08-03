<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<ul class="pagination justify-content-center">
	<li class="page-item">
		<c:choose>
		<c:when test="${datas.hasPrevPage()}">
			<li class="page-item disabled">
				<a class="page-link bi bi-caret-left-fill" href="#"></a>
			</li>
		</c:when>
		<c:otherwise>
			<li class="page-item disabled">
				<a class="page-link disabled bi bi-caret-left-fill" href="${pageUrl}?page=${datas.prevPage}"></a>
			</li>
		</c:otherwise>
		</c:choose>
	</li>
	<c:forEach items="${datas.getPages(datas.currentPage - 2, datas.currentPage + 2)}" var="item">
		<li class="page-item ${datas.currentPage ==item ? ' active' : ''}">
			<a class="page-link" href="${pageUrl}?page=${item}">${item}</a>
		</li>
	</c:forEach>
	<c:choose>
		<c:when test="${datas.hasNextPage()}"> <!-- 얘는 메소드이기 때문에 ()를 써줘야 한다. -->
			<li class="page-item disabled">
				<a class="page-link bi bi-caret-right-fill" href="${pageUrl}?page=${datas.nextPage}"></a>
			</li>
		</c:when>
		<c:otherwise>
			<li class="page-item disabled">
				<a class="page-link bi bi-caret-right-fill" href="#"></a>
			</li>
		</c:otherwise>
	</c:choose>
</ul>
