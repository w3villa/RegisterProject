<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/css/ajaxfileupload.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript" src="resources/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="resources/js/ajaxfileupload.js"></script>

<link href="resources/css/jquery-ui-1.8.20.custom.css" rel="stylesheet"
	type="text/css" />
<script src="resources/js/jquery.uniform.min.js"></script>
<!-- <script src="resources/js/jquery-1.3.2.min.js"></script> -->
<script src="resources/js/jquery-ui-1.7.3.custom.min.js"></script>

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
		<table width="80%" align="center">
			<tr>
				<td width="50%"><label>Please select a file to upload :
				</label></td>
				<td width="50%"><input id="fileData" name="fileData"
					type="file" /> <input type="button" id="uploadbutton"
					value="upload" onclick="ajaxFileUpload()" size="60" name="files[]" multiple/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="hidden" id="uploadPath"
					name="uploadPath" value="/resources/images/" /></td>
			</tr>
			<tr>
				<td><label>Progress : </label></td>
				<td>
					<table width="50%">
						<tr>
							<td>
								<div style="padding: 10px 5px 5px 5px; float: left;">
									<div id="progressbar1"></div>
								</div>
							</td>
							<td id="percentage" align="left"><font size='1'>0%</font></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td><label>Status : </label></td>
				<td>
					<div id="statusbar"></div>
				</td>
			</tr>
		</table>
		<table width="70%" align="center">
			<tr>
				<td align="center"><div id="imageDiv"></div></td>
			</tr>
		</table>
	</fieldset>
</body>
<script type="text/javascript">

	$(document).ready(function() {
		
	});
	function ajaxFileUpload() {
		$("#progressbar1").progressbar();
		var refreshIntervalId = setInterval(checkFileStatus, 1000);
		
		//checkFileStatus();
		
		//starting setting some animation when the ajax starts and completes
		/*$("#loading").ajaxStart(function() {
			$(this).show();
		}).ajaxComplete(function() {
			$(this).hide();
		});*/

		/*
			prepareing ajax file upload
			url: the url of script file handling the uploaded files
		    fileElementId: the file type of input element id and it will be the index of  $_FILES Array()
			dataType: it support json, xml
			secureuri:use secure protocol
			success: call back function when the ajax complete
			error: callback function when the ajax failed
			
		 */
		$
				.ajaxFileUpload({
					url : '${pageContext.servletContext.contextPath}/FileUploadAmazon',
					secureuri : false,
					fileElementId : 'fileData',
					dataType : 'json',
					data : {
						uploadPath : $("#uploadPath").val()
					},
					success : function(uploadFileResponse, status) {
						stopStatusCheck(refreshIntervalId);
						//$("#progressbar1").css("background", "skyBlue");
						//var status = checkFileStatus();
						$("#uploadPath").val(uploadFileResponse.uploadPath) ;
						if (status) {
							$("#statusbar").html("Completed");
							var imgHtml = "<img id='uploaded'	src='${pageContext.servletContext.contextPath}/"
									+ $("#uploadPath").val()
									+ uploadFileResponse.fileName
									+ "' width='400' height='300' />";
							//alert(imgHtml);
							$("#imageDiv").html(imgHtml);
						}
						//clearInterval(refreshIntervalId);
						//$("#progressbar1").css("background", "SeaGreen");
					},
					error : function(data, status, e) {
						alert(e);
					}
				})

		
		return false;
	}

	function stopStatusCheck(refreshIntervalId){
		$("#progressbar1 > div").css("background", "SeaGreen");
		clearInterval(refreshIntervalId);	
	}
	
	function checkFileStatus() {
		$.post("${pageContext.servletContext.contextPath}/getStatus", "userName=me", function(uploadInfoBean) {
			var per = uploadInfoBean.percentage;
			//alert(per);
			if(per != '100'){
				$("#statusbar").html("Uploading...");
				$("#progressbar1").progressbar("option", "value", per);
				$("#percentage").html("<font size='1'>" + per + "%</font>");
				$("#progressbar1 > div").css("background", "skyBlue");
			}
			if(per == '100'){
				$("#progressbar1").progressbar("option", "value", per);
				$("#percentage").html("<font size='1'>" + per + "%</font>");
				$("#progressbar1 > div").css("background", "SeaGreen");
				clearInterval(refreshIntervalId);	
			}
		});
		return true;
	}
</script>