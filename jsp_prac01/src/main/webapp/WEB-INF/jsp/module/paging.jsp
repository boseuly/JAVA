<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="paging">
	<c:set var="currentPage" value="${page}"/>	<!-- 변수설정 -->
	<c:set var="prevPage" value="${currentPage -1}"/>
	<c:set var="nextPage" value="${currentPage + 1}"/>
	<c:set var="maxPage" value="${pageList.get(pageList.size() -1)}"/> <!-- 가장 마지막 페이지를 알아낸다. -->
	<ul> <!-- mapPage를 넘지 않도록 조정해주면 된다. -> 안 그러면 인덱스를 넘어버림 -->
		<!-- 이전페이지로 이동하기 링크 -->
		<li>
			<c:choose>
				<c:when test="${prevPage <= 0}"> <!-- 만약 이전 페이지가 존재하지 않는다면 -->
					<a href="#">keyboard_arrow_left</a> <!-- #은 클릭시 이벤트가 발생하지 않도록 하기 위함이다.(현재 페이지를 유지하기 위함) -->
				</c:when>
				<c:otherwise>
					<a href="${pageUrl}?page=${prevPage}">keyboard_arrow_left</a>  <!-- url 변수를 이용해서 해당 페이지를 보여줘야 한다. -->
				</c:otherwise>
			</c:choose>
		</li>
		<!-- 페이지 선택 --> <!-- pageList의 타입 List<Integer> -->	<!-- 최대 페이지 수를 넘지 않도록 해준다. -->
		<c:forEach begin="${currentPage -2 <= 0 ? 1 : currentPage -2}" end="${currentPage + 2 > maxPage ? maxPage : currentPage + 2}" var="item"><!-- 페이지 리스트의 요소 하나하나를 item에 저장한다. -->
			<li>
				<a href="${pageUrl}?page=${pageList.get(item-1)}">
				${pageList.get(item-1)}</a><!-- pageList하나하나 가져오는데 -->
			</li>
		</c:forEach>
		
		<!-- 다음페이지로 이동하기 링크 -->
		<li>
			<c:choose>
				<c:when test="${nextPage > pageList.size()}"> <!-- 만약 nextPage가 pageList의 길이와 같다면 -->
					<a href="#">keyboard_arrow_left</a> <!-- #은 클릭시 이벤트가 발생하지 않도록 하기 위함이다.(현재 페이지를 유지하기 위함) -->
				</c:when>
				<c:otherwise>
					<a href="${pageUrl}?page=${nextPage}">keyboard_arrow_right</a> 
				</c:otherwise>
			</c:choose>
		</li>
	</ul>
</div>