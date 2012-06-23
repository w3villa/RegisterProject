<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><jsp:include
	page="header.jsp" />
<script type="text/javascript" src="resources/js/jquery-1.7.2.js"></script>
<style>
.errors {
	background: #F7DEC0;
}
</style>
<div id="banner">
	<form:form modelAttribute="userEntityBean" method="Post"
		action="RegisterMe">
		<div id="form_status">Incomplete</div>
		<table width="100%">
			<tr>
				<!-- 		<td><img src="resources/images/img04.jpg" width="750" height="170" alt="" /></td> -->
				<td>
					<fieldset>
						<legend>Register Me</legend>
						<table width="90%" align="center">
							<tr>
								<td class="date">First Name</td>
								<td><form:input path="firstName" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red" size="1"><form:errors
											path="firstName" /></font></td>
							</tr>
							<tr>
								<td class="date">Last Name</td>
								<td><form:input path="lastName" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red" size="1"><form:errors
											path="lastName" /></font></td>
							</tr>
							<tr>
								<td class="date">Email Id</td>
								<td><form:input path="emailId" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red" size="1"><form:errors
											path="emailId" /></font></td>
							</tr>
							<tr>
								<td class="date">Password</td>
								<td><form:password path="password" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red" size="1"><form:errors
											path="password" /></font></td>
							</tr>
							<tr>
								<td class="date">Re-Type Password</td>
								<td><form:password path="ReTypePassword" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red" size="1"><form:errors
											path="ReTypePassword" /></font></td>
							</tr>
							<tr>
								<td class="date">Addres line 1</td>
								<td><form:password path="addressLine1" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red" size="1"><form:errors
											path="addressLine1" /></font></td>
							</tr>
							<tr>
								<td class="date">Addres line 2</td>
								<td><form:password path="addressLine2" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red" size="1"><form:errors
											path="addressLine2" /></font></td>
							</tr>
							<tr>
								<td class="date">City</td>
								<td><form:password path="city" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red" size="1"><form:errors
											path="city" /></font></td>
							</tr>
							<tr>
								<td class="date">State</td>
								<td><form:password path="state" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red" size="1"><form:errors
											path="state" /></font></td>
							</tr>
							<tr>
								<td class="date">Country</td>
								<td><form:password path="country" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red" size="1"><form:errors
											path="country" /></font></td>
							</tr>
							<tr>
								<td class="date">Contact No</td>
								<td><form:password path="contactNo" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red" size="1"><form:errors
											path="contactNo" /></font></td>
							</tr>
							<tr>
								<td><input type="submit" value="SIGN UP" /></td>
							</tr>
						</table>
					</fieldset>
				</td>
			</tr>
		</table>
	</form:form>
</div>
<script>
	$("#ReTypePassword").live(
			"blur",
			function() {
				if (($("#password").val() != $(this).val())
						&& ($("#password").val().trim().length != 0)
						&& ($(this).val().trim().length != 0)) {
					$("#password").addClass("errors");
					$(this).addClass("errors");
				} else {
					$("#password").removeClass("errors");
					$(this).removeClass("errors");
				}
			});
</script>