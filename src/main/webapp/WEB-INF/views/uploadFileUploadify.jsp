<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script src="resources/js/jquery-file-upload/jquery.1.7.2.min.js"
	type="text/javascript"></script>
<script src="resources/js/uploadify/jquery.uploadify.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="resources/css/uploadify/uploadify.css">
	
	
<div id="queue"></div>
<div class="upload_form">
	<form:form modelAttribute="uploadBean" method="post"
		enctype="multipart/form-data">
		<input id="file_upload" name="fileData" type="file" multiple="true">
	</form:form>
</div>
<script type="text/javascript">
$(function() {
	$('#file_upload').uploadify({
		'swf' : 'resources/swf/uploadify/uploadify.swf',
		'uploader' : '${pageContext.servletContext.contextPath}/FileUploadUploadify',
		'onUploadStart' : function(file) {
	    },
		'onUploadSuccess' : function(file,data,response){
		}
	});
});
</script>
