<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
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
<body>

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
		<div class="messageblock">You have been Registered Successfully,
			Please login to continue....</div>
	</c:if>

	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>
				<div class="field">
					<label for="j_username">User :</label>
					<input type='text' name='j_username' value=''>
				</div>
				
				<div class="field">
					<label for="j_password">Password :</label>
					<input type='password' name='j_password' value=''>
				</div>
				
				<div class="style_prefs">
					<input type="checkbox" id="rem_me" name='_spring_security_remember_me' /> <label for="rem_me">Remember Me</label>
				</div>
				
				<input name="submit" type="submit" value="Sign In" class="button" />
				<input name="reset" type="reset" class="button" />

	</form>
</body>
</html>