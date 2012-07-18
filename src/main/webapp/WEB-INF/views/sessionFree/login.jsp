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
	<div>Login Page</div>

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

		<table width = "100%">
			<tr>
				<td>User:</td>
				<td><input type='text' name='j_username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' /></td>
			</tr>
			<tr>
				<td>Remember Me</td>
				<td><input type="checkbox" name='_spring_security_remember_me' />
				</td>
			</tr>
			<!-- tr>
				<td colspan='2'>New User <a
					href="${pageContext.servletContext.contextPath}/RegisterMe">Sign
						Up</a>
				</td>
			</tr-->
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Sign In" /><input name="reset" type="reset" /></td>
			</tr>

		</table>

	</form>
</body>
</html>