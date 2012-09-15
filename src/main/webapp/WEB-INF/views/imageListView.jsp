<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="imageDetailBeans" value="${imageDetailBeans}" />
<div class="page_header">Manage Images</div>
<div class="hr"></div>
<div class="gap10px"></div>
<div class="content">
<c:forEach items="${sessionScope.users.userAlbumChoiceMpgs}" var="userAlbumChoiceMpg">
	<span class="album_name">${userAlbumChoiceMpg.albumChoice.name}</span>
	<div class="user_albums" data-uacm_id="${userAlbumChoiceMpg.userAlbumChoiceMpgId}">
		<div style="width:${userAlbumChoiceMpg.albumChoice.noOfPages * 107}px">
			<c:forEach var="i" begin="1" end="${userAlbumChoiceMpg.albumChoice.noOfPages}">
				<div class="album_number" data-seq='${i}'></div>
			</c:forEach>				
		</div>
	</div>
</c:forEach>

<span class="uploaded_images">Uploaded Images</span>
<div class="list_images">	
	<c:choose>
		<c:when test="${imageDetailBeans != null && imageDetailBeans != ''}">	
			<c:forEach var="imageDetailBean" items="${imageDetailBeans}"
				varStatus="myCounter">
				<c:set var="listSize" value="${listSize + 1 }" />
				<img src="${imageDetailBean.path}" data-id="${imageDetailBean.id}">
			</c:forEach>
		</c:when>
		<c:otherwise>
			<td align="center" colspan="3">No data Found</td>
		</c:otherwise>
	</c:choose>
	</div>
</div>


<script type="text/javascript" src="resources/js/jquery.ui.core.js"></script>
<style>
.album_number {
    border: 1px solid;
    float: left;
    height: 75px;
    margin: 0 5px 5px 0;
    width: 100px;
}

.user_albums {
    height: 100px;
    overflow: auto;
    margin: 5px 0 20px;
 }  
 
 .list_images img {
 	cursor: move;
 	margin: 0 14px 14px 0px;
 }
 
 .list_images {
 	height: 200px;
 	width: 960px;
 	overflow: auto;
 	margin-top:5px;
 }
 
 .album_name {
 	font-weight: bold;
 	font-size: 13px; 
 }
 
 .uploaded_images {
 	font-weight: bold;
 	font-size: 15px;
 }
 
</style>

<script>
	var updatePosition = function(userAlbumChoiceMpgId, sequenceNo, imageMappingId) {
		$.ajax({
			url: "u",
			data: {userAlbumChoiceMpgId: userAlbumChoiceMpgId, sequenceNo: sequenceNo, imageMappingId: imageMappingId},
			method: 'get'
		})
	};
	$(".list_images img").draggable({
		cursor: "move",
		helper: function( event ) {
			return $("<img src='"+ $(this).attr("src") +"' />");
		}
	});
	$(".album_number").droppable({
		drop: function( event, ui ) {
			dragged_image = $(ui.draggable);
			pan = $(this);
			pan.html("<img src='" + dragged_image.attr("src") + "' />");
			updatePosition(pan.closest(".user_albums").data("uacm_id"), pan.data("seq"), dragged_image.data("id"));
		}
	});
	
</script>