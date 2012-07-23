<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}

.messageblock {
	color: #00CC00;
	background-color: #FFFF66;
	border: 3px solid #00CC00;
	padding: 8px;
	margin: 16px;
}
</style>
<head>
<title>Home</title>
</head>
<body>
<!-- 	<div align="right"> -->
<%-- 		<a href="<c:url value="/j_spring_security_logout" />"> Logout</a> --%>
<!-- 	</div> -->
	<div class="messageblock">Hello ${sessionScope.users.firstName}
		${sessionScope.users.lastName}</div>
	<div class="errorblock">Your email Id :
		${sessionScope.users.emailId}</div>
	<div class="messageblock">
		Your address : <br>${sessionScope.users.addressLine1}<br>${sessionScope.users.addressLine2}<br>${sessionScope.users.city}
		, ${sessionScope.users.state} , ${sessionScope.users.country}
	</div>
	<div class="errorblock">Your phone No :
		${sessionScope.users.contactNo}</div>

	<div class="messageblock">
		Style Preferences Selected : <br>
		<table width="90%" align="center">
			<c:forEach items="${sessionScope.users.userStylePreferncesMpgs}"
				var="stylePreferenceMpg" varStatus="counter">
				<c:choose>
					<c:when test="${counter.count%2 != 0 }">
						<tr>
							<td>${stylePreferenceMpg.stylePreference.name}</td>
					</c:when>
					<c:otherwise>
						<td>${stylePreferenceMpg.stylePreference.name}</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</c:forEach>

		</table>

	</div>
	<div class="errorblock"><jsp:include page="directory.jsp"></jsp:include></div>
<%-- 	<div class="messageblock"><jsp:include page="UploadFile.jsp"></jsp:include></div> --%>
	<div class="messageblock"><jsp:include page="UploadFileJquery.jsp"></jsp:include></div>

</body>
</html>
