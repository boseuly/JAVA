<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<nav class="nav-container">
     <ul class="style-none">
       <li class="dept-list">
         <a class="dept-url" href="${pageContext.request.contextPath}/dept" >부서</a> <!--  만약 이걸 누르면 이미 부서 페이지인걸 확인해야 함 -->
       </li>
       <li class="locs-list">
         <a class="locs-url" href="${pageContext.request.contextPath}/locs">지역</a>
       </li>
       <li class="emps-list">
         <a class="emps-url" href="${pageContext.request.contextPath}/emps">직원</a>
       </li>
     </ul>
</nav>