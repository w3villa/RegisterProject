<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/css/style.css" rel="stylesheet" type="text/css" media="screen" />
<title>Welcome to igild</title>
</head>
<body>
	<div align="center">
		<div class="body">
<%-- 			<jsp:include page="header.jsp"></jsp:include> --%>
			<a href="${pageContext.servletContext.contextPath}/RegisterMe" style="position:relative">
				<img src="resources/images/background.jpg"  />
				<span class="click_here">Click here for FREE Album Design</span>
			</a>
			

<%-- 			<jsp:include page="footer.jsp"></jsp:include> --%>
		</div>	
	</div>	
</body>
</html>