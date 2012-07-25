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
<div class="crud" align="center">
	<form:form modelAttribute="stylePreferenceBean" method="Post"
		action="crudStylePref">

		<fieldset>
			<legend>Style Preference Attributes</legend>
			<table width="80%" align="center">
				<tr>
					<td colspan="7">&nbsp;<form:hidden path="stylePreferenceId" /></td>
				</tr>
				<tr>
					<td><label>1.</label></td>
					<td><label>Style Preference Name :</label></td>
					<td><form:input path="name" /> <span class="error"><form:errors
								path="name" /></span></td>
					<td>&nbsp;</td>
					<td><label>2.</label></td>
					<td><label>Style Preference Description :</label></td>
					<td><form:textarea path="description" /> <span class="error"><form:errors
								path="description" /></span></td>
				</tr>
				<tr>
					<td colspan="7">&nbsp;<input type="hidden" name="operation"
						id="operation" /></td>
				</tr>
			</table>

			<input type="button" name="clearAll" value="Clear All" id="clearAll"
				class="buttonCrud" onclick="resetAll();clearData();clearRadio();" />&nbsp;&nbsp;
			<input type="button" name="save" id="save" value="Save"
				class="buttonCrud" onclick="saveMe();" />&nbsp;&nbsp; <input
				type="button" class="buttonCrud" name="update" id="update"
				value="Update" onclick="updateMe();" disabled />&nbsp;&nbsp; <input
				type="button" name="delete" class="buttonCrud" id="delete"
				value="Delete" onclick="deleteMe();" disabled />
		</fieldset>

	</form:form>
</div>
<div id="tableDataList" width="80%" align="center">
	<fieldset>
		<legend>Style Preference Data List</legend>
		<table width="80%" align="center" border="2">
			<tr>
				<th>&nbsp;</th>
				<th>Style Preference Name</th>
				<th>Style Preference Description</th>
			</tr>
			<c:choose>
				<c:when test="${objectList != null && objectList != ''}">
					<c:forEach var="object" items="${objectList}" varStatus="myCounter">
						<c:set var="listSize" value="${listSize + 1 }" />
						<tr>
							<td><input type="radio" id="radio_${myCounter.count}"
								name="radioName" value="row_${myCounter.count}"
								onclick="onClickRadio('${object.stylePreferenceId}','${myCounter.count}')" /></td>
							<td id="td_name_${myCounter.count}">${object.name}</td>
							<td id="td_description_${myCounter.count}">${object.description}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<td align="center" colspan="3">No data Found</td>
				</c:otherwise>
			</c:choose>
		</table>
	</fieldset>
</div>

<script type="text/javascript">
	function onClickRadio(id, counter) {
		clearData();
		document.getElementById('stylePreferenceId').value = id;
		document.getElementById('name').value = document
				.getElementById('td_name_' + counter).textContent;
		document.getElementById('description').value = document
				.getElementById('td_description_' + counter).textContent;

		document.getElementById('update').disabled = false;
		document.getElementById('delete').disabled = false;
		document.getElementById('clearAll').disabled = false;
		document.getElementById('save').disabled = true;
	}

	function clearData() {
		document.getElementById("operation").value='';
		document.getElementById('stylePreferenceId').value = '';
		document.getElementById('name').value = '';
		document.getElementById('description').value = '';
	}

	function clearRadio() {
		for ( var i = 1; i <= '${listSize}'; i++) {
			document.getElementById('radio_' + i).checked = false;
		}
	}

	function resetAll() {
		document.getElementById('update').disabled = true;
		document.getElementById('delete').disabled = true;
		document.getElementById('clearAll').disabled = false;
		document.getElementById('save').disabled = false;
	}

	function saveMe() {
		if (confirm("Do you want to save details?")){}
			submitForm('<%=ProjectConstant.CRUD_CRAETE%>');
	}

	function updateMe() {
		if (confirm("Do you want to update details?"))
			submitForm('<%=ProjectConstant.CRUD_UPDATE%>');
	}

	function deleteMe() {
		if (confirm("Do you want to delete details?"))
			submitForm('<%=ProjectConstant.CRUD_DELETE%>');
	}

	function submitForm(operation) {
		document.getElementById("operation").value = operation;
		document.forms[0].submit();

	}
	
	<c:if test="${status != ''  || status != null}">
	clearData();
	</c:if>
</script>
</html>