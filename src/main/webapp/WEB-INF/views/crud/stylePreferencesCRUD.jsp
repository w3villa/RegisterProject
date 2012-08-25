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
<div class="page_header">Style Preference</div>
<c:if test="${status eq fail}">
	<div class="errorblock">${message}</div>
</c:if>
<c:if test="${status eq pass}">
	<div class="messageblock">${message}</div>
</c:if>
<div align="center">
	<form:form modelAttribute="stylePreferenceBean" method="Post"
		action="crudStylePref">

		<div class="gap10px"></div>
		<form:hidden path="stylePreferenceId" />
		<input type="hidden" name="operation" id="operation" />
		<div class="admin_form">
			<div class="admin_fields">
				<label>Style Preference Name :</label>
				<form:input path="name" cssClass="user_input" />
				<span class="error"><form:errors path="name" /></span>
			</div>
			<div class="admin_fields">
				<label>Style Preference Description :</label>
				<form:textarea path="description" class="user_input user_textarea" />
				<span class="error"><form:errors path="description" /></span>
			</div>
			<div class="clear_both_admin"></div>
		</div>
		<div class="gap10px"></div>
		<div class="gap10px"></div>
		<input type="button" name="clearAll" value="Clear All" id="clearAll"
			class="button1" />&nbsp;&nbsp;
			<input type="button" name="save" id="save" value="Save"
			class="button1"/>&nbsp;&nbsp; <input
			type="button" class="button1" name="update" id="update"
			value="Update" />&nbsp;&nbsp; <input
			type="button" name="delete" class="button1" id="delete"
			value="Delete" />
	</form:form>
</div>
<div id="tableDataList" align="center">
	<table class="admin_data">
		<tr>
			<th style="width: 15px;">&nbsp;</th>
			<th>Style Preference Name</th>
			<th>Style Preference Description</th>
		</tr>
		<c:choose>
			<c:when test="${objectList != null && objectList != ''}">
				<c:forEach var="object" items="${objectList}" varStatus="myCounter">
					<c:set var="listSize" value="${listSize + 1 }" />
					<tr data-id="${object.stylePreferenceId}">
						<td><input type="radio" name="radioName" /></td>
						<td class="style_pref_name">${object.name}</td>
						<td class="style_pref_desc">${object.description}</td>
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
	$("#stylePreferenceId").val($(this).data("id"));
	$("#name").val($(this).find(".style_pref_name").text());
	$("#description").val($(this).find(".style_pref_desc").text());
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
$("#clearAll").live("click",function(){
	clearAll();
});
function submitForm(operation) {
		$("#operation").val(operation);
		document.forms[0].submit();
	}
function clearAll(){
	$(".admin_data tr").removeClass("selected_row");
	$(".admin_data tr").find("input[type='radio']").attr("checked", false);
	$("#stylePreferenceId").val('');
	$("#name").val('');
	$("#description").val('');
	$("#operation").val('');
// 	$("#save").attr("disabled",false);
// 	$("#update").attr("disabled",true);
// 	$("#delete").attr("disabled",true);

	$("#save").show();
	$("#update").hide();
	$("#delete").hide();
}	
	
	

</script>
</html>