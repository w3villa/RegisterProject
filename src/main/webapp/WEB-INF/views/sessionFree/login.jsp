<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="hr">&nbsp;</div>
<div class="page_header">Login</div>
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
		<label for="j_username">User :</label> <input type='text'
			name='j_username' value=''>
	</div>

	<div class="field">
		<label for="j_password">Password :</label> <input type='password'
			name='j_password' value=''>
	</div>

	<div class="style_prefs">
		<input type="checkbox" id="rem_me" name='_spring_security_remember_me' />
		<label for="rem_me">Remember Me</label>
	</div>

	<input name="submit" type="submit" value="Sign In" class="button" /> <input
		name="reset" type="reset" class="button" />

</form>
