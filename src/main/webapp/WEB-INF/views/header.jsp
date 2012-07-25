<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.w3villa.constants.ProjectConstant" %>
<html>
<link href="resources/css/style.css" rel="stylesheet" type="text/css"
	media="screen" />
<script type="text/javascript" src="resources/js/jquery-1.7.2.js"></script>
<c:set var="roleUser" value="<%=ProjectConstant.ROLE_USER%>"/>
<c:set var="roleAdmin" value="<%=ProjectConstant.ROLE_ADMIN%>"/>
<c:choose>
	<c:when test="${sessionScope.users != null}">
		<c:if test="${sessionScope.role == roleUser}">
			<a href="${pageContext.servletContext.contextPath}/welcomeUser"><img
				src="resources/images/logo.png" class="logo" /></a>
		</c:if>
		<c:if test="${sessionScope.role == roleAdmin}">
			<a href="${pageContext.servletContext.contextPath}/welcomeAdmin"><img
				src="resources/images/logo.png" class="logo" /></a>
		</c:if>
	</c:when>
	<c:otherwise>
		<a href="${pageContext.servletContext.contextPath}/"><img
			src="resources/images/logo.png" class="logo" /></a>
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${sessionScope.users != null}">
		<a href="<c:url value="/j_spring_security_logout" />" class='join_now'>
			Logout</a>
	</c:when>
	<c:otherwise>
		<a href="${pageContext.servletContext.contextPath}/RegisterMe"
			class='join_now'></a>
	</c:otherwise>
</c:choose>