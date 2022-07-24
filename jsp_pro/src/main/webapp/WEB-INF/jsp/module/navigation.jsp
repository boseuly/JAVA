<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<header>
	<nav class="top-nav">
		<ul class="nav">
			<c:if test="${not empty sessionScope.loginData}"> <!--  로그인 한 사람들한테만 보이도록 한다. -->
				<li class="nav-item ${fn:contains(url, '/emps') ? 'active' : '' }">
					<c:url var="m5" value="/emps" />
					<a class="nav-link" href="${m5}">직원</a>
				</li>
				<li class="nav-item ${fn:contains(url, 'depts') ? 'active' : '' }">
					<c:url var="m6" value="/depts" />
					<a class="nav-link" href="${m6}">부서</a>
				</li>
				<li class="nav-item ${fn:contains(url, 'locs') ? 'active' : '' }">
					<c:url var="m7" value="/locs" />
					<a class="nav-link" href="${m7}">지역</a>
				</li>
			</c:if>
		</ul>
		<ul class="nav right">
			<c:if test="${not empty sessionScope.loginData}">
				<li class="nav-item dropdown">
					<a class="nav-link" href="#">${sessionScope.loginData.empName}</a>
					<ul class="nav dropdown-nav">
						<c:url var="myInfoUrl" value="/myinfo" />
						<li class="nav-item">
							<a class="nav-link" href="${myInfoUrl}">개인정보</a>
						</li>
						<c:url var="logoutUrl" value="/logout" />
						<li class="nav-item">
							<a class="nav-link" href="${logoutUrl}">로그아웃</a>
						</li>
					</ul>
				</li>
			</c:if>
		</ul>
	</nav>
</header>