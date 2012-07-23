<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/css/ajaxfileupload.css" type="text/css"
	rel="stylesheet">
<script src="resources/js/jquery-file-upload/jquery.1.7.2.min.js"></script>
<script src="resources/js/jquery-file-upload/vendor/jquery.ui.widget.js"></script>
<script src="resources/js/jquery-file-upload/jquery.iframe-transport.js"></script>
<script src="resources/js/jquery-file-upload/jquery.fileupload.js"></script>

<script src="resources/js/jquery-file-upload/load-image.min.js"></script>
<script src="resources/js/jquery-file-upload/canvas-to-blob.min.js"></script>
<script src="resources/js/jquery-file-upload/jquery.fileupload-fp.js"></script>

<style type="text/css">
#progressbar1 {
	float: left;
	border: 1px solid Skyblue;
	padding: 1px 1px 1px 1px;
	width: 200px;
	height: 8px;
	float: left;
}
</style>
<body>
	<fieldset>
		<legend>Upload file</legend>
		<div class="field">
			<label>Please select a file to upload :<input
				id="fileupload" type="file" name="fileData"
				data-url="${pageContext.servletContext.contextPath}/FileUploadAmazon"
				multiple> <input type="button" id="cancelBtn"
				class="button1" value="cancel all" /> </label>
		</div>
		<div id="fileStatus" class="field"></div>

	</fieldset>
</body>
<script type="text/javascript">
	$(function() {
		$.xhrPool = [];
		$.xhrPool.abortAll = function() {
			$(this).each(function(idx, jqXHR) {
				if ($("#file_" + idx).text() != '  uploaded.') {
					$("#file_" + idx).text("  cancelled.");
					$('#cancel_' + idx).attr("disabled", "disabled");
					$('#cancel_' + idx).hide('slow');
					jqXHR.abort();
				}
			});
			//$.xhrPool.length = 0;
		};

		$.xhrPool.abortOne = function(index) {
			$(this).each(function(idx, jqXHR) {
				if (idx == index) {
					jqXHR.abort();
					$("#file_" + index).text("  cancelled.");
					$('#cancel_' + index).attr("disabled", "disabled");
					$('#cancel_' + index).hide('slow');
				}
			});
		};

		var count = 0;
		$('#fileupload').fileupload({
			dataType : 'json',
			done : function(e, data) {
				$.each(data.result, function(index, file) {
					//$('<p/>').text(file.fileName).appendTo($('#fileStatus'));
					$("#file_" + count).text("  uploaded.");
					//alert("count :"+count);
					$('#cancel_' + count).attr("disabled", "disabled");
					$('#cancel_' + count).hide('slow');
					count = count + 1;
				});
			},
			start : function(e, data) {
			},
			stop : function(e) {
				//alert('Uploads Done');
			},
			beforeSend : function(jqXHR) {
				$.xhrPool.push(jqXHR);
			},
			complete : function(jqXHR) {
				// 		        var index = $.xhrPool.indexOf(jqXHR);
				// 		        if (index > -1) {
				// 		            $.xhrPool.splice(index, 1);
				// 		        }
			}

		// 			beforeSend : function(event, files, index, xhr, handler, callBack) {

		// 				for (var i = 0; i < files.files.length; i++) {
		// 				    var file = files.files[i];
		// 				    var imageType = /image.*/;

		// 				    if (!file.type.match(imageType)) {
		// 				    	files.xhr().abort();
		// 				    	alert(file.name+" file doesnt match png, jpg, jpeg or gif.. aborting.");
		// 				    }

		// 				}

		// 				assignXHR(files.xhr());
		// 			}
		});

		var imageUploadCounter = 0;

		$('#cancelBtn').click(function(e) {
			$.xhrPool.abortAll();
		});

		$('#fileupload')
				.bind(
						'fileuploadadd',
						function(e, data) {
							$
									.each(
											data.files,
											function(index, element) {
												if (element.type != 'image/png'
														&& element.type != 'image/jpg'
														&& element.type != 'image/gif'
														&& element.type != 'image/jpeg') {
													//alert(element.name+" file doesnt match png, jpg, jpeg or gif.. excluding.");
												} else {
													$("<li/>")
															.html(

																	//(imageUploadCounter + 1)
																	//+ ". "

																	"<img id='img_"+imageUploadCounter+"' width='50' height='50'/>"
																			+ element.name
																			+ "   "
																			+ (element.size / 1024)
																					.toFixed(3)
																			+ "Kb size"
																			+ "<div id = 'file_"+imageUploadCounter+"' class='copyright'>  uploading...</div>"
																			+ "<input type='button' id='cancel_"
																			+ imageUploadCounter
																			+ "' class='button1' value='cancel' onclick='$.xhrPool.abortOne("
																			+ imageUploadCounter
																			+ ")' />")
															.appendTo(
																	$('#fileStatus'));
													//alert("imageUploadCounter :"+imageUploadCounter);
													var img = document
															.getElementById('img_'
																	+ imageUploadCounter);
													var reader = new FileReader();
													reader.onload = (function(
															aImg) {
														return function(e) {
															aImg.src = e.target.result;
														};
													})(img);
													reader
															.readAsDataURL(element);
													imageUploadCounter = imageUploadCounter + 1;
												}
											});
						});

	});
</script>