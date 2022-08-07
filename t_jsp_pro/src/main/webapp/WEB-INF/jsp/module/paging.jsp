<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	
<ul class="pagination justify-content-center">
	<li class="page-item">
		<c:choose>
			<c:when test="${datas.hasPrevPage()}"> <!-- 함수를 사용할 때는 이런 식으로 사용한다. (getter는 제외 )-->
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
	<c:forEach items="${datas.getPages(datas.currentPage - 2,datas.currentPage + 2)}" var="item"> <!-- 현재 페이지에서 -2한 값부터, 현재 페이지에서 +2한 값까지 리스트를 불러와라 -->
		<li class="page-item ${datas.currentPage == item ? ' active' : ''}"> 
			<a class="page-link" href="${pageUrl}?page=${item}">${item}</a>
		</li>
	</c:forEach>
	<li class="page-item">
		<c:choose>
			<c:when test="${datas.hasNextPage()}"> 
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
	</li>
</ul>
