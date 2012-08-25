<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>UploadiFive Test</title>
<script src="resources/js/jquery-file-upload/jquery.1.7.2.min.js"
	type="text/javascript"></script>
<script src="resources/js/uploadify/jquery.uploadify.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="resources/css/uploadify/uploadify.css">
<style type="text/css">
body {
	font: 13px Arial, Helvetica, Sans-serif;
}
</style>
</head>

<body>
	<h1>Uploadify Demo</h1>
	
		<div id="queue"></div>
		
	
	<form:form modelAttribute="uploadBean" method="post"
		enctype="multipart/form-data">
<input id="file_upload" name="fileData" type="file" multiple="true">
	</form:form>
	<script type="text/javascript">
		$(function() {
			$('#file_upload')
					.uploadify(
							{
								'swf' : 'resources/swf/uploadify/uploadify.swf',
								'uploader' : '${pageContext.servletContext.contextPath}/FileUploadUploadify',
								'onUploadStart' : function(file) {
						        },
								'onUploadSuccess' : function(file,data,response){
								}
							});
		});
	</script>
</body>
</html>