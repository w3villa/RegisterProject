<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script type="text/javascript" src="resources/js/jquery-1.7.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<body>
	<fieldset>
		<legend>Upload List</legend>
		<table width="80%" align="center" id="tableId">

			<c:choose>
				<c:when test="${!empty assetList}">
					<c:forEach items="${assetList}" var="asset" varStatus="counter">
						<c:choose>
							<c:when test="${counter.count%2 != 0 }">
								<tr>
									<td><a href="#" onclick="getFolderDetails('${asset.name}')"><img
											src="${pageContext.servletContext.contextPath}${asset.imageLocation}"
											alt="${asset.name}" width="102" height="102" /></a>
											<br>${asset.name} (${asset.no_of_File_folder_inside})
											</td>
							</c:when>
							<c:otherwise>
								<td><a href="#" onclick="getFolderDetails('${asset.name}')"><img
											src="${pageContext.servletContext.contextPath}${asset.imageLocation}"
											alt="${asset.name}" width="102" height="102" /></a>
											<br>
											${asset.name} (${asset.no_of_File_folder_inside})
								</td>
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


		</table>
	</fieldset>
</body>
</body>
<script type="text/javascript">
function getFolderDetails(assetName) {
	$.post("${pageContext.servletContext.contextPath}/getAsset", "folderName="+assetName, function(amazonStructureList) {
		
		$.each(amazonStructureList, function(i, item){
            alert("No. " + (i+1) + " name = " + item.name + ", imageLocation = " + item.imageLocation);
        });
	});
	return true;
}
</script>
</html>