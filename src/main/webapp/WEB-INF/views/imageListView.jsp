<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="imageDetailBeans" value="${imageDetailBeans}" />
<div class="page_header">Manage Images</div>
<div class="hr"></div>
<div class="gap10px"></div>
<div class="content">
	<c:forEach items="${sessionScope.users.userAlbumChoiceMpgs}"
		var="userAlbumChoiceMpg">
		<span>${userAlbumChoiceMpg.albumChoice.name}</span>
		<div class="user_albums"
			data-uacm_id="${userAlbumChoiceMpg.userAlbumChoiceMpgId}">
			<div
				style="width:${userAlbumChoiceMpg.albumChoice.noOfPages * 107}px">
				<c:forEach var="i" begin="1"
					end="${userAlbumChoiceMpg.albumChoice.noOfPages}">
					<c:set var="imageExists" value="false"/>
					<c:forEach items="${userAlbumChoiceMpg.imageAlbumChoiceMappings}"
						var="imageAlbumChoiceMapping">
						<c:if test="${imageAlbumChoiceMapping.sequenceNo eq i }">
							<c:set var="imageExists" value="true"/>
							<div class="album_number" data-seq='${i}'
								data-id="${imageAlbumChoiceMapping.imageAlbumChoiceMappingId}"
								data-image_id="${imageAlbumChoiceMapping.imageMapping.imageMappingId}">
							</div>
							
						</c:if>
					</c:forEach>
					<c:if test="${imageExists eq false}">
						<div class="album_number" data-seq='${i}' data-id="">
							<img src="${imageDetailBean.path}"
								data-id="${imageDetailBean.id}">
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</c:forEach>

	<b>Uploaded Images</b>
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
	margin-top: 15px;
	overflow: auto;
}

.list_images img {
	cursor: move;
	margin: 0 14px 14px 0px;
}

.list_images {
	margin-top: 20px;
	height: 200px;
	width: 960px;
	overflow: scroll;
}
</style>

<script>
	var updatePosition = function(imageAlbumChoiceMappingId,
			userAlbumChoiceMpgId, sequenceNo, imageMappingId, pan) {
		$
				.ajax({
					url : "${pageContext.servletContext.contextPath}/updateUploadedImages",
					data : {
						imageAlbumChoiceMappingId : imageAlbumChoiceMappingId,
						userAlbumChoiceMpgId : userAlbumChoiceMpgId,
						sequenceNo : sequenceNo,
						imageMappingId : imageMappingId
					},
					method : 'get',
					success : function(data, textStatus, jqXHR) {
						pan.attr("data-id", data);
					}
				})
	};
	$(".list_images img").draggable({
		cursor : "move",
		helper : function(event) {
			return $("<img src='" + $(this).attr("src") + "' />");
		}
	});
	$(".album_number")
			.droppable(
					{
						drop : function(event, ui) {
							dragged_image = $(ui.draggable);
							pan = $(this);
							pan.html("<img src='" + dragged_image.attr("src")
									+ "' />");
							updatePosition(pan.data("id"), pan.closest(
									".user_albums").data("uacm_id"), pan
									.data("seq"), dragged_image.data("id"), pan);
						}
					});
</script>