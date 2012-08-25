<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="footer">
	<div class="gap10px"></div>
	<div class="gap10px"></div>

	<div class="hr">&nbsp;</div>
	<ul class="link-nav">
		<li><a href="${pageContext.servletContext.contextPath}/faq">FAQ</a></li>
		<li><a href="${pageContext.servletContext.contextPath}/contactus">Contact
				Us</a></li>
		<c:if test="${sessionScope.users == null}">		
			<li><a
				href="${pageContext.servletContext.contextPath}/welcomeUser">Log
					In</a></li>
		</c:if>
		<%--li><a
			href="${pageContext.servletContext.contextPath}/welcomeAdmin">Log
				In as Admin</a></li--%>
	</ul>
	<div class="copyright">&copy; 2012, IGILD</div>
</div>