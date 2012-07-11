<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link href="resources/css/styles.css" rel="stylesheet" type="text/css" />
<title>Login Page</title>
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
</head>
<body onload='document.f.j_username.focus();'>


	<h1 id="head">
		<a href="#"><span>code</span>Pal</a>
	</h1>
	<div id="page-wrap">
		<ul id="nav">
			<li class="current_list_item"><a class="home" href="#">Home</a></li>
			<li><a class="about" href="#">About Us</a></li>
			<li><a href="#">Services</a></li>
			<li><a href="#">Products</a></li>
			<li><a href="#">Contact us</a></li>
		</ul>
		<div id="upper-content">
			<div id="welcome">
				<h2>Welcome</h2>
				<p>
					<a href="http://www.code-pal.com" target="_blank">Code-pal</a> is a
					development agency proficient in developing customized Wordpress
					sites which give soul to your designs.
				</p>
			</div>
			<div id="image"></div>
		</div>
		<div id="lower-content">
			<div id="column-header">
				<h3>Column Header</h3>
				<p>Filler test, text that is filler. Samples are free. free is
					good. don't bother reading the free. Don't bother reading this
					because it doesn't mean anything.</p>
				<p>I thought of writing unmeaningful stuff rather than using
					lorem ipsum.</p>
				<p>Filler text. text that is filler</p>
			</div>
			<div id="flickr">
				<h3>Flickr</h3>
				<ul id="flickr-box">
					<li id=""><a href="#"><img
							src="resources/images/flickr-box.png" alt="Flikr Box" /></a></li>
					<li id=""><a href="#"><img
							src="resources/images/flickr-box.png" alt="Flikr Box" /></a></li>
					<li id=""><a href="#"><img
							src="resources/images/flickr-box.png" alt="Flikr Box" /></a></li>
					<li id=""><a href="#"><img
							src="resources/images/flickr-box.png" alt="Flikr Box" /></a></li>
					<li id=""><a href="#"><img
							src="resources/images/flickr-box.png" alt="Flikr Box" /></a></li>
					<li id=""><a href="#"><img
							src="resources/images/flickr-box.png" alt="Flikr Box" /></a></li>
					<li id=""><a href="#"><img
							src="resources/images/flickr-box.png" alt="Flikr Box" /></a></li>
					<li id=""><a href="#"><img
							src="resources/images/flickr-box.png" alt="Flikr Box" /></a></li>
					<li id=""><a href="#"><img
							src="resources/images/flickr-box.png" alt="Flikr Box" /></a></li>
				</ul>
			</div>
			<div id="get-in-touch">
				<h3>Login Here</h3>
				<form name='f' action="<c:url value='j_spring_security_check' />"
					method='POST'>
					<c:if test="${not empty error}">
						<div class="errorblock">
							Your login attempt was not successful, try again.<br /> Caused :
							${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
						</div>
					</c:if>
					<c:if test="${not empty errorSession}">
						<div class="errorblock">${errorSession}</div>
					</c:if>
					<c:if test="${not empty registerSuccess}">
						<div class="messageblock">You have been Registered
							Successfully, Please login to continue....</div>
					</c:if>
					<ul id="Login-list">
						<li>User:<input type='text' name='j_username' value=''></li>
						<li>Password:<input type='password' name='j_password' /></li>
						<li>Remember Me <input type="checkbox"
							name='_spring_security_remember_me' /></li>
						<li>New User <a
							href="${pageContext.servletContext.contextPath}/RegisterMe">Sign
								Up</a></li>
						<li><input name="submit" type="submit" value="Sign In" /><input
							name="reset" type="reset" /></li>
					</ul>
				</form>
			</div>
		</div>
		<div id="footer">
			&copy; 2010 Developed by <a href="http://www.code-pal.com">Code-pal</a>&nbsp;&nbsp;|&nbsp;&nbsp;Design
			by <a href="http://psdnation.info/free-grunge-psd-website-template/"
				target="_blank">PSD Nation</a>
		</div>

	</div>







</body>
</html>