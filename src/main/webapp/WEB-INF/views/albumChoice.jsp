<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="userNavBar.jsp"></jsp:include>

<c:set var="objectList" value="${albumChoiceList}" />

<div class="gap10px"></div>
<div class="page_header">Choose Your Album style</div>
<br>
<br>

<c:choose>
	<c:when test="${objectList != null && objectList != ''}">
		<c:forEach var="object" items="${objectList}" varStatus="myCounter">
			<c:set var="listSize" value="${listSize + 1 }" />
			<div class="album_choices">
				<span class="title"><input type="checkbox"
					name="album_choice" id="${object.albumChoiceId}" /> <label
					for="${object.albumChoiceId}">${object.name}</label></span>
				${object.description}
			</div>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<div class="album_choices">No data Found</div>
	</c:otherwise>
</c:choose>

<form action="${pageContext.servletContext.contextPath}/"
	style="padding: 0px; margin: 0px;" method="get">
	<input type="hidden" name="msg"
		value="Account Successfully Created! Please log in." />
	<div style="padding: 0 0 0 44px;">
		<input type="submit" class="button" value="Continue" />
	</div>
</form>

<div class="clear_both"></div>