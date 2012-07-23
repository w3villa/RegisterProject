<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<body>
	<fieldset>
		<legend>Upload List</legend>
		<div id="directoryDiv">
			<table width="80%" align="center" id="tableId">
				<tbody>
					<c:choose>
						<c:when test="${!empty assetList}">
							<c:forEach items="${assetList}" var="asset" varStatus="counter">
								<c:choose>
									<c:when test="${counter.count%2 != 0 }">
										<tr>
											<td><a href="#"
												onclick="getFolderDetails('${asset.name}')"><img
													src="${pageContext.servletContext.contextPath}${asset.imageLocation}"
													alt="${asset.name}" width="102" height="102" /></a> <br>${asset.name}
												(${asset.no_of_File_folder_inside})</td>
									</c:when>
									<c:otherwise>
										<td><a href="#"
											onclick="getFolderDetails('${asset.name}')"><img
												src="${pageContext.servletContext.contextPath}${asset.imageLocation}"
												alt="${asset.name}" width="102" height="102" /></a> <br>
											${asset.name} (${asset.no_of_File_folder_inside})</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td>No files uploaded</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<div id="imageDisplayDiv">
			<br> <br> Preview:
			<div id="preview_status_div">
				<table width="50%">
					<tr>
						<td id="preview_status_td" align="left"><font size='1'></font></td>
					</tr>
				</table>
			</div>
			<table width="80%" align="center" id="imageDisplayTableId">
				<tbody>
				</tbody>
			</table>
		</div>
	</fieldset>
</body>
</body>
<script type="text/javascript">
	function getFolderDetails(assetName) {
		$("#imageDisplayTableId").empty();
		$("#preview_status_td").html("<font size='1'> uploading.... </font>");
		$
				.post(
						"${pageContext.servletContext.contextPath}/getAsset",
						"folderName=" + assetName,
						function(amazonStructureList) {
							$("#preview_status_td").html("<font size='1'>Done. </font>");
							var newRowBody = "";
							$
									.each(
											amazonStructureList,
											function(i, item) {
												if (i % 2 == 0) {
													//alert('1');
													newRowBody = "<tr><td>"
															+ "<img id='image_'"+i+"	src='${pageContext.servletContext.contextPath}/resources/"+
							item.fullPath
							+ "' width='102' height='102' /> <br> "
															+ item.name
															+ "</td>";
													if (amazonStructureList.length == (i + 1)) {
														//alert('2 :'+newRowBody);
														var newRow = $(newRowBody
																+ "<td>&nbsp;</td></tr>");
														$(
																"#imageDisplayTableId")
																.append(newRow);
													}

												} else {
													//alert('3');
													newRowBody = newRowBody
															+ "<td>"
															+ "<img id='image_'"+i+"	src='${pageContext.servletContext.contextPath}/resources/"+
							item.fullPath
							+ "' width='102' height='102' /> <br> "
															+ item.name
															+ "</td></tr>";
													var newRow = $(newRowBody);
													$("#imageDisplayTableId")
															.append(newRow);
												}

												//var $tr = $("#tableId").find("tbody tr:last").clone();//this will grab the last table row.
												//$("#tableId").append($tr);//add the row back to the table
												//var newRow = $("<tr><td>hi</td><td>bye</td></tr>");
												//$("#tableId").append(newRow);
												//alert("No. " + (i + 1) + " name = " + item.name + ", imageLocation = " + item.imageLocation);
											});

						});
		return true;
	}
</script>
</html>