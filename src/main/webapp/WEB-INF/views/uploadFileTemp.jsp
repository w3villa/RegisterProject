<html>
<head>
<meta charset="utf-8">
<title>jQuery File Upload Example</title>
<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet"
	href="resources/css/jquery-file-upload/jquery.fileupload-ui.css">
<style type="text/css">
.bar {
	background: green;
}
</style>
</head>
<body>
	<input id="fileupload" type="file" name="fileData"
		data-url="${pageContext.servletContext.contextPath}/FileUploadAmazonTemp"
		multiple>
	<input type="button" id="cancelBtn" value="cancel" />	
	<br />
	<div id="fileStatus"></div>

	<div id="progress">
		<div class="bar" style="width: 0%;"></div>
	</div>
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script
		src="resources/js/jquery-file-upload/vendor/jquery.ui.widget.js"></script>
	<script
		src="resources/js/jquery-file-upload/jquery.iframe-transport.js"></script>
	<script src="resources/js/jquery-file-upload/jquery.fileupload.js"></script>

	<script
		src="http://blueimp.github.com/JavaScript-Load-Image/load-image.min.js"></script>
	<script
		src="http://blueimp.github.com/JavaScript-Canvas-to-Blob/canvas-to-blob.min.js"></script>
	<script src="resources/js/jquery-file-upload/jquery.fileupload-fp.js"></script>



	<script>
		
		$(function() {
			var jqXHR;
			var count = 0;
			$('#fileupload').fileupload({
				dataType : 'json',
				done : function(e, data) {
					$.each(data.result, function(index, file) {
						//$('<p/>').text(file.fileName).appendTo($('#fileStatus'));
						$("#file_" + count).text("  uploaded.");
						count = count + 1;
					});
				},
				start : function(e, data) {
				},
				stop : function(e) {
					//alert('Uploads Done');
				},
				beforeSend: function (event, files, index, xhr, handler, callBack) {
					xhr.abort();
				}

			});

			
			
			var imageUploadCounter = 0;
			
			$('#cancelBtn').click(function (e) {
			    alert();
				jqXHR.abort();
			});
			
			$('#fileupload')
					.bind(
							'fileuploadadd',
							function(e, data) {
								$
										.each(
												data.files,
												function(index, element) {
													//alert(element.type);
													if(element.type != 'image/png' && element.type != 'image/jpg' && element.type != 'image/gif' && element.type != 'image/jpeg' ) {
										                alert("File doesnt match png, jpg or gif");
										            }

													//alert("file no "+(index+1)+" size :"+element.size);
													//$('<p/>').text((index+1)+". "+element.name+"  uploading").appendTo($('#fileStatus'));
													$("<li/>")
															.html(

																	//(imageUploadCounter + 1)
																	//+ ". "
																	element.name
																			+ "   "
																			+ (element.size / 1024)
																					.toFixed(3)
																			+ "Kb size"
																			+ "<div id = 'file_"+imageUploadCounter+"'>  uploading...</div>")
															.appendTo(
																	$('#fileStatus'));
													imageUploadCounter = imageUploadCounter + 1;

												});

							});
			
			
		});
	</script>
</body>
</html>