<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="imageDetailBeans" value="${imageDetailBeans}" />
<jsp:include page="userNavBar.jsp"></jsp:include>
<div class="gap10px"></div>
<div class="content">
	<div class="slideshow" align="center">
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
				<div id="slides">
					<div class="slides_container">
						<div>
							<img src="resources/images/slide_image_1.jpg">
						</div>
						<div>
							<img src="resources/images/slide_image_2.jpg">
						</div>
						<div>
							<img src="resources/images/slide_image_3.jpg">
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="gap10px"></div>
	<div class="hr"></div>
	<div class="upload" align="center">
		<div class="title">Upload your album here</div>
		<div class="info">Please select images of your wedding after
			clicking the 'Select File' button below. You can upload multiple
			images at a time</div>
		<jsp:include page="uploadFileUploadify.jsp"></jsp:include>
		<div class="uploader"></div>
	</div>
	<div class="gap10px"></div>
	<div class="slideshow" align="center">
		<a href="${pageContext.servletContext.contextPath}/ListUploadedImages">Click
			here to proceed Next</a>
	</div>
</div>
<script type="text/javascript" src="resources/js/slides.jquery.js"></script>
<script>
	$(function() {
		$("#slides").slides({
			play : 5000,
			pagination : false,
			generatePagination : false
		});
	});
</script>

