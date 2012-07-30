<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="objectList" value="${objectList}" />
<jsp:include page="../adminNavBar.jsp"></jsp:include>
<div class="page_header">Contact Us Requests</div>

<div id="tableDataList" align="center">
	<table class="admin_data">
		<tr>
			<th style="width: 15px;">Sr. No.</th>
			<th>Person Name</th>
			<th>Person Email</th>
			<th>Comments</th>
			<th>Contact No.</th>
			<th>Date of comment</th>
		</tr>
		<c:choose>
			<c:when test="${objectList != null && objectList != ''}">
				<c:forEach var="object" items="${objectList}" varStatus="myCounter">
					<c:set var="listSize" value="${listSize + 1 }" />
					<tr>
						<td>${myCounter.count}</td>
						<td>${object.name}</td>
						<td>${object.email}</td>
						<td>${object.comments}</td>
						<td>${object.contactNo}</td>
						<td>${object.createdDt}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<td align="center" colspan="3">No data Found</td>
			</c:otherwise>
		</c:choose>
	</table>
</div>