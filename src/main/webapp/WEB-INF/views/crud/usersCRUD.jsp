<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.w3villa.constants.ProjectConstant"%>
<c:set var="objectList" value="${objectList}" />
<c:set var="status" value="${status}" />
<c:set var="message" value="${message}" />
<c:set var="pass" value="<%=ProjectConstant.UPLOAD_STATUS_PASS%>" />
<c:set var="fail" value="<%=ProjectConstant.UPLOAD_STATUS_FAIL%>" />
<c:set var="listSize" value="0" />
<jsp:include page="../adminNavBar.jsp"></jsp:include>
<div class="page_header">Users</div>
<c:if test="${status eq fail}">
	<div class="errorblock">${message}</div>
</c:if>
<c:if test="${status eq pass}">
	<div class="messageblock">${message}</div>
</c:if>
<div align="center">
	<form:form modelAttribute="userEntityBean" method="Post"
		action="crudUsers">

		<div class="gap10px"></div>
		<form:hidden path="id" />
		<input type="hidden" name="operation" id="operation" />
		<div class="admin_form">
			<div class="admin_fields">
				<label for="firstName">First Name</label>
				<form:input path="firstName" cssClass="user_input" />
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
				<label for="country">New Password (Empty if don't want to change)</label> <input type="text"
					name="newPass" id="newPass" class="user_input" /> <span
					class="error"><form:errors path="country" /></span>
			</div>
			<div class="clear_both_admin"></div>
		</div>

		<div class="gap10px"></div>
		<div class="gap10px"></div>
		<input type="button" name="clearAll" value="Clear All" id="clearAll"
			class="button1" />&nbsp;&nbsp;
			<input type="button" name="save" id="save" value="Save"
			class="button1" />&nbsp;&nbsp; <input type="button" class="button1"
			name="update" id="update" value="Update" />&nbsp;&nbsp; <input
			type="button" name="delete" class="button1" id="delete"
			value="Delete" />
	</form:form>
</div>
<div id="tableDataList" align="center">
	<table class="admin_data">
		<tr>
			<th style="width: 15px;">&nbsp;</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>User Name</th>
			<th>Email</th>
			<th>Address Line 1</th>
			<th>Address Line 2</th>
			<th>City</th>
			<th>State</th>
			<th>Zip Code</th>
			<th>Country</th>
			<th>Modified Date</th>
			<th>Created Date</th>
		</tr>
		<c:choose>
			<c:when test="${objectList != null && objectList != ''}">
				<c:forEach var="object" items="${objectList}" varStatus="myCounter">
					<c:set var="listSize" value="${listSize + 1 }" />
					<tr data-id="${object.id}">
						<td><input type="radio" name="radioName" /></td>
						<td class="style_pref_firstName">${object.firstName}</td>
						<td class="style_pref_lastName">${object.lastName}</td>
						<td class="style_pref_userName">${object.userName}</td>
						<td class="style_pref_emailId">${object.emailId}</td>
						<td class="style_pref_addressLine1">${object.addressLine1}</td>
						<td class="style_pref_addressLine2">${object.addressLine2}</td>
						<td class="style_pref_city">${object.city}</td>
						<td class="style_pref_state">${object.state}</td>
						<td class="style_pref_zipCode">${object.zipCode}</td>
						<td class="style_pref_country">${object.country}</td>
						<td class="style_pref_updateDt">${object.updateDt}</td>
						<td class="style_pref_createdDt">${object.createdDt}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<td align="center" colspan="3">No data Found</td>
			</c:otherwise>
		</c:choose>
	</table>
</div>

<script type="text/javascript">
$(document).ready(function(){
	clearAll();
});


$(".admin_data tr").removeClass("selected_row");

$(".admin_data tr").live("click", function() {
	$(".admin_data tr").removeClass("selected_row");	
	
	$("#id").val($(this).data("id"));
	$("#firstName").val($(this).find(".style_pref_firstName").text());
	$("#lastName").val($(this).find(".style_pref_lastName").text());
	$("#emailId").val($(this).find(".style_pref_emailId").text());
	$("#addressLine1").val($(this).find(".style_pref_addressLine1").text());
	$("#addressLine2").val($(this).find(".style_pref_addressLine2").text());
	$("#city").val($(this).find(".style_pref_city").text());
	$("#state").val($(this).find(".style_pref_state").text());
	$("#zipCode").val($(this).find(".style_pref_zipCode").text());
	$("#country").val($(this).find(".style_pref_country").text());
	
	
	$(this).find("input[type='radio']").attr("checked", true);
	$(this).addClass("selected_row");
// 	$("#save").attr("disabled",true);
// 	$("#update").attr("disabled",false);
// 	$("#delete").attr("disabled",false);

	$("#save").hide();
	$("#update").show();
	$("#delete").show();
});

$("#save").live("click",function(){
	if (confirm("Do you want to save details?"))
	submitForm('<%=ProjectConstant.CRUD_CRAETE%>');
});
$("#update").live("click",function(){
	if (confirm("Do you want to update details?"))
		submitForm('<%=ProjectConstant.CRUD_UPDATE%>');
});
$("#delete").live("click",function(){
	if (confirm("Do you want to delete details?"))
		submitForm('<%=ProjectConstant.CRUD_DELETE%>');
	});
	$("#clearAll").live("click", function() {
		clearAll();
	});
	function submitForm(operation) {
		$("#operation").val(operation);
		document.forms[0].submit();
	}
	function clearAll() {
		$(".admin_data tr").removeClass("selected_row");
		$(".admin_data tr").find("input[type='radio']").attr("checked", false);

		$("#id").val('');
		$("#firstName").val('');
		$("#lastName").val('');
		$("#emailId").val('');
		$("#addressLine1").val('');
		$("#addressLine2").val('');
		$("#city").val('');
		$("#state").val('');
		$("#zipCode").val('');
		$("#country").val('');
		$("#newPass").val('');

		// 	$("#save").attr("disabled",false);
		// 	$("#update").attr("disabled",true);
		// 	$("#delete").attr("disabled",true);

		$("#save").hide();
		$("#update").hide();
		$("#delete").hide();
	}
</script>
</html>