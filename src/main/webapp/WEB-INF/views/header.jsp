<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><html>
<link href="resources/css/style.css" rel="stylesheet" type="text/css"
	media="screen" />

<c:choose>
	<c:when test="${sessionScope.users != null}">
		<a href="${pageContext.servletContext.contextPath}/welcome"><img
	src="resources/images/logo.png" class="logo" /></a>
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
			class='join_now'>Join Now</a>
	</c:otherwise>
</c:choose>