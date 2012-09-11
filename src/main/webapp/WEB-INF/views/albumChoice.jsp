<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="userNavBar.jsp"></jsp:include>

<c:set var="objectList" value="${albumChoiceList}" />
<c:set var="selectedList" value="${sessionScope.users.userAlbumChoiceMpgs}"/>
<div class="gap10px"></div>
<div class="page_header">Choose Your Album style</div>
<br>
<br>
<form action="${pageContext.servletContext.contextPath}/saveAlbumChoice"
	style="padding: 0px; margin: 0px;" method="post">
	<c:choose>
		<c:when test="${objectList != null && objectList != ''}">
			<c:forEach var="object" items="${objectList}" varStatus="myCounter">
				<c:set var="listSize" value="${listSize + 1 }" />
				<div class="album_choices">
					<span class="title"><input type="checkbox"
						name="album_choice" id="${object.albumChoiceId}" value="${object.albumChoiceId}"/> <label
						for="${object.albumChoiceId}">${object.name}</label></span>
					${object.description}
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div class="album_choices">No data Found</div>
		</c:otherwise>
	</c:choose>


	<div style="text-align: center;">
		<input type="submit" class="button" value="Save" />
	</div>
</form>
<script type="text/javascript">

<c:forEach var="selected" items="${selectedList}" >
$("#${selected.albumChoice.albumChoiceId}").attr('checked','checked');
</c:forEach>

</script>
<div class="clear_both"></div>