<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="resources/css/style.css" rel="stylesheet" type="text/css"
	media="screen" />
<script type="text/javascript" src="resources/js/jquery-1.7.2.js"></script>
<style>
.errors {
	background: #F7DEC0;
}

.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
<body>
	<form:form modelAttribute="userEntityBean" method="Post"
				action="RegisterMe">
				<div id="form_status"></div>
				<c:if test="${not empty error}">
					<div class="errorblock">
						Registration process failed.<br /> Caused : ${error}
					</div>
				</c:if>
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
										<td><font color="red"><form:errors
													path="firstName" /></font></td>
									</tr>
									<tr>
										<td class="date">Last Name</td>
										<td><form:input path="lastName" /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><font color="red"><form:errors path="lastName" /></font></td>
									</tr>
									<tr>
										<td class="date">Email Id</td>
										<td><form:input path="emailId" /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><font color="red"><form:errors path="emailId" /></font></td>
									</tr>
									<tr>
										<td class="date">Password</td>
										<td><form:password path="password" /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><font color="red"><form:errors path="password" /></font></td>
									</tr>
									<tr>
										<td class="date">Re-Type Password</td>
										<td><form:password path="ReTypePassword" /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><font color="red"><form:errors
													path="ReTypePassword" /></font></td>
									</tr>
									<tr>
										<td class="date">Addres line 1</td>
										<td><form:input path="addressLine1" /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><font color="red"><form:errors
													path="addressLine1" /></font></td>
									</tr>
									<tr>
										<td class="date">Addres line 2</td>
										<td><form:input path="addressLine2" /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><font color="red"><form:errors
													path="addressLine2" /></font></td>
									</tr>
									<tr>
										<td class="date">City</td>
										<td><form:input path="city" /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><font color="red"><form:errors path="city" /></font></td>
									</tr>
									<tr>
										<td class="date">State</td>
										<td><form:input path="state" /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><font color="red"><form:errors path="state" /></font></td>
									</tr>
									<tr>
										<td class="date">Country</td>
										<td><form:input path="country" /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><font color="red"><form:errors path="country" /></font></td>
									</tr>
									<tr>
										<td class="date">Contact No</td>
										<td><form:input path="contactNo" /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><font color="red"><form:errors
													path="contactNo" /></font></td>
									</tr>
									<tr>
										<td colspan="2"><fieldset>
												<legend>Style Preferences </legend>
												<table width="90%" align="center">
													<c:forEach items="${stylePreferenceList}"
														var="stylePreference" varStatus="counter">
														<c:choose>
															<c:when test="${counter.count%2 != 0 }">
																<tr>
																	<td><input type="checkbox" name="stylePreferences"
																		value="${stylePreference.stylePreferenceId}">&nbsp;${stylePreference.name}
																	</td>
															</c:when>
															<c:otherwise>
																<td><input type="checkbox" name="stylePreferences"
																	value="${stylePreference.stylePreferenceId}">&nbsp;${stylePreference.name}
																</td>
																</tr>
															</c:otherwise>
														</c:choose>
													</c:forEach>

												</table>
											</fieldset></td>
									</tr>
									<tr>
										<td colspan="2" align="center"><input type="submit"
											value="SIGN UP" /></td>
									</tr>

								</table>
							</fieldset>
						</td>
					</tr>
				</table>
			</form:form>
</body>
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