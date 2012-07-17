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
			<jsp:include page="header.jsp"></jsp:include>
			<img src="resources/images/background.jpg" />
			<ul class="link-nav">
				<li><a href="${pageContext.servletContext.contextPath}/faq">FAQ</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/contactus">Contact Us</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/login">Log In</a></li>
			</ul>
			<jsp:include page="footer.jsp"></jsp:include>
		</div>	
	</div>	
</body>
</html>