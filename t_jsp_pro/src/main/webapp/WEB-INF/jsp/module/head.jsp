<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:url value="/static/css" var="cssUrl"/>
<c:url value="/static/css" var="jsUrl"/>
<link rel="stylesheet" type="text/css" href="${cssUrl}/default.css">
<link rel="stylesheet" type="text/css" href="${cssUrl}/required.css">
<link rel="stylesheet" type="text/css" href="${cssUrl}/form.css">
<link rel="stylesheet" type="text/css" href="${cssUrl}/navigation.css">
<link rel="stylesheet" type="text/css" href="${cssUrl}/paging.css">
<link rel="stylesheet" type="text/css" href="${cssUrl}/table.css">
<script type="text/javascript" src="${jsUrl}/required.js"></script>
<script type="text/javascript" src="${jsUrl}/jquery-3.6.0.min.js"></script>
