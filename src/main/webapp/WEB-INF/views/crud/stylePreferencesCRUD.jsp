<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.w3villa.constants.ProjectConstant"%>
<c:set var="objectList" value="${objectList}" />
<c:set var="status" value="${status}" />
<c:set var="message" value="${message}" />
<c:set var="pass" value="<%=ProjectConstant.UPLOAD_STATUS_PASS%>" />
<c:set var="fail" value="<%=ProjectConstant.UPLOAD_STATUS_FAIL%>" />
<c:set var="listSize" value="0" />
<div class="hr">&nbsp;</div>
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
				<div class="clear_both"></div>
			</div>
			<div class="gap10px"></div>
			<div class="gap10px"></div>
			<input type="button" name="clearAll" value="Clear All" id="clearAll"
				class="buttonCrud" onclick="resetAll();clearData();clearRadio();" />&nbsp;&nbsp;
			<input type="button" name="save" id="save" value="Save"
				class="buttonCrud" onclick="saveMe();" />&nbsp;&nbsp; <input
				type="button" class="buttonCrud" name="update" id="update"
				value="Update" onclick="updateMe();" disabled />&nbsp;&nbsp; <input
				type="button" name="delete" class="buttonCrud" id="delete"
				value="Delete" onclick="deleteMe();" disabled />
	</form:form>
</div>
<div id="tableDataList" align="center">
		<table class="admin_data">
			<tr>
				<th style="width:15px;">&nbsp;</th>
				<th>Style Preference Name</th>
				<th>Style Preference Description</th>
			</tr>
			<c:choose>
				<c:when test="${objectList != null && objectList != ''}">
					<c:forEach var="object" items="${objectList}" varStatus="myCounter">
						<c:set var="listSize" value="${listSize + 1 }" />
						<tr data-id="${object.stylePreferenceId}">
							<td><input type="radio" name="radioName"  /></td>
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

$(".admin_data tr").live("click", function() {
	$("#stylePreferenceId").val($(this).data("id"));
	$("#name").val($(this).find(".style_pref_name").text());
	$("#description").val($(this).find(".style_pref_desc").text());
	$(this).find("input[type='radio']").attr("checked", true)
});

// 	function onClickRadio(id, counter) {
// 		clearData();
// 		document.getElementById('stylePreferenceId').value = id;
// 		document.getElementById('name').value = document
// 				.getElementById('td_name_' + counter).textContent;
// 		document.getElementById('description').value = document
// 				.getElementById('td_description_' + counter).textContent;

// 		document.getElementById('update').disabled = false;
// 		document.getElementById('delete').disabled = false;
// 		document.getElementById('clearAll').disabled = false;
// 		document.getElementById('save').disabled = true;
// 	}

// 	function clearData() {
// 		document.getElementById("operation").value='';
// 		document.getElementById('stylePreferenceId').value = '';
// 		document.getElementById('name').value = '';
// 		document.getElementById('description').value = '';
// 	}

// 	function clearRadio() {
// 		for ( var i = 1; i <= '${listSize}'; i++) {
// 			document.getElementById('radio_' + i).checked = false;
// 		}
// 	}

// 	function resetAll() {
// 		document.getElementById('update').disabled = true;
// 		document.getElementById('delete').disabled = true;
// 		document.getElementById('clearAll').disabled = false;
// 		document.getElementById('save').disabled = false;
// 	}

// 	function saveMe() {
// 		if (confirm("Do you want to save details?")){}
<%-- 			submitForm('<%=ProjectConstant.CRUD_CRAETE%>'); --%>
// 	}

// 	function updateMe() {
// 		if (confirm("Do you want to update details?"))
<%-- 			submitForm('<%=ProjectConstant.CRUD_UPDATE%>'); --%>
// 	}

// 	function deleteMe() {
// 		if (confirm("Do you want to delete details?"))
<%-- 			submitForm('<%=ProjectConstant.CRUD_DELETE%>'); --%>
// 	}

// 	function submitForm(operation) {
// 		document.getElementById("operation").value = operation;
// 		document.forms[0].submit();

// 	}
	
	<c:if test="${status != ''  || status != null}">
	//clearData();
	</c:if>
</script>
</html>