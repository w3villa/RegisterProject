<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="imageDetailBeans" value="${imageDetailBeans}" />
<div class="page_header">Manage Images</div>
<div class="hr"></div>
<div class="gap10px"></div>
<div class="content">
	<c:choose>
		<c:when test="${imageDetailBeans != null && imageDetailBeans != ''}">
			<div id="slides">
				<div class="slides_container">
					<c:forEach var="imageDetailBean" items="${imageDetailBeans}"
						varStatus="myCounter">
						<c:set var="listSize" value="${listSize + 1 }" />
						<div>
							<img src="${imageDetailBean.path}">
						</div>
					</c:forEach>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<td align="center" colspan="3">No data Found</td>
		</c:otherwise>
	</c:choose>
</div>
<script type="text/javascript" src="resources/js/slides.jquery.js"></script>
<script>
	$(function(){
	    $("#slides").slides({
	    	play: 5000,
	    	pagination: false,
	    	generatePagination: false
	    });
	});
</script>
