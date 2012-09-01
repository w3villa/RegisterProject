<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="imageDetailBeans" value="${imageDetailBeans}" />
<div class="page_header">Manage Images</div>
<div class="hr"></div>
<div class="gap10px"></div>
<div class="content">
<div class="demo">
	<c:choose>
		<c:when test="${imageDetailBeans != null && imageDetailBeans != ''}">
			<ul id="sortable">
				<c:forEach var="imageDetailBean" items="${imageDetailBeans}"
					varStatus="myCounter">
					<c:set var="listSize" value="${listSize + 1 }" />
					<li class="ui-state-default">
						<img src="${imageDetailBean.path}" data-id="${imageDetailBean.id}">
					</li>
				</c:forEach>
			</ul>			
		</c:when>
		<c:otherwise>
			<td align="center" colspan="3">No data Found</td>
		</c:otherwise>
	</c:choose>
	</div>
	<form:form method="Post"
		action="updateUploadedImages" >
	<input type="hidden" class="position_ids" name="csv">
	<input type="submit" class="button" class="update_positions" value="Update Positions">
	</form:form>
</div>

<style>
#sortable { list-style-type: none; margin: 0; padding: 0; }
#sortable li { 
	margin: 0 3px 3px 3px; 
	padding: 0.4em; 
	padding-left: 1.5em; 
	font-size: 1.4em; 
	cursor:move;
	height:100px; 
}
</style>
<script type="text/javascript" src="resources/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="resources/js/widget.js"></script>
<script type="text/javascript" src="resources/js/mouse.js"></script>
<script type="text/javascript" src="resources/js/sortable.js"></script>




<script>
var getUpdatedPosition = function() {
	var arr = [];
	$("#sortable li img").each(function() {
		arr.push($(this).data("id"));
	});
	return arr;
}
$(function() {
	$("#sortable").sortable({
		stop: function(event, ui) {
			$(".position_ids").val(getUpdatedPosition());	
		}
	});
	$( "ul, li" ).disableSelection();
	
});
</script>