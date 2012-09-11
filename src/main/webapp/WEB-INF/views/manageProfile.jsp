<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="userNavBar.jsp"></jsp:include>

<div class="gap10px"></div>
<div class="page_header">Manage Profile</div>
<br>
<br>
<form:form modelAttribute="userEntityBean" method="Post"
	action="manageProfile">

	<div class="gap10px"></div>
	<form:hidden path="id" />
	<input type="hidden" name="operation" id="operation" />
	<div class="admin_form">
		<div class="admin_fields">
			<label for="firstName">First Name</label>
			<form:input path="firstName"  cssClass="user_input" />
			<span class="error"><form:errors path="firstName" /></span>
		</div>
		<div class="admin_fields">
			<label for="lastName">Last Name</label>
			<form:input path="lastName" cssClass="user_input" />
			<span class="error"><form:errors path="lastName" /></span>
		</div>
		<div class="admin_fields">
			<label for="emailId">Email</label>
			<form:input path="emailId" cssClass="user_input" />
			<span class="error"><form:errors path="emailId" /></span>
		</div>
		<div class="admin_fields">
			<label for="addressLine1">Address Line 1</label>
			<form:input path="addressLine1" cssClass="user_input" />
			<span class="error"><form:errors path="addressLine1" /></span>
		</div>
		<div class="admin_fields">
			<label for="addressLine2">Address Line 2</label>
			<form:input path="addressLine2" cssClass="user_input" />
			<span class="error"><form:errors path="addressLine2" /></span>
		</div>
		<div class="admin_fields">
			<label for="city">City</label>
			<form:input path="city" cssClass="user_input" />
			<span class="error"><form:errors path="city" /></span>
		</div>
		<div class="admin_fields">
			<label for="state">State</label>
			<form:input path="state" cssClass="user_input" />
			<span class="error"><form:errors path="state" /></span>
		</div>
		<div class="admin_fields">
			<label for="zipCode">Zip Code</label>
			<form:input path="zipCode" cssClass="user_input" />
			<span class="error"><form:errors path="zipCode" /></span>
		</div>
		<div class="admin_fields">
			<label for="country">Country</label>
			<form:input path="country" cssClass="user_input" />
			<span class="error"><form:errors path="country" /></span>
		</div>
		<div class="admin_fields">
			<label for="newPass">New Password (Empty if don't want to
				change)</label> <input type="text" name="newPass" id="newPass"
				class="user_input" /> <span class="error"></span>
		</div>
		<div class="clear_both_admin"></div>
	</div>

	<div class="gap10px"></div>
	<div class="gap10px"></div>
		<input type="submit" name="save" id="save" value="Save"
		class="button1" />
</form:form>