<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<div class="hr">&nbsp;</div>
	<div class="page_header">Contact Us</div>
	<form:form modelAttribute="contactUsBean" method="Post"
		action="contactus" cssClass="user_form">
		<div id="form_status"></div>
		<c:if test="${not empty error}">
			<div class="errorblock">
				Failed to save form.<br /> Caused : ${error}
			</div>
		</c:if>
		<div class="field">
			<label for="name">Name</label>
			<form:input path="name" cssClass="user_input" />
			<span class="error"><form:errors path="name" /></span>
		</div>
		<div class="field">
			<label for="email">Email</label>
			<form:input path="email" cssClass="user_input" />
			<span class="error"><form:errors path="email" /></span>
		</div>
		<div class="field">
			<label for="contactNo">Contact Number</label>
			<form:input path="contactNo" cssClass="user_input" />
			<span class="error"><form:errors path="contactNo" /></span>
		</div>
		<div class="field">
			<label for="comments">Comments</label>
			<form:textarea path="comments" cssClass="user_input user_textarea" />
			<span class="error"><form:errors path="comments" /></span>
		</div>
		<div class="gap10px"></div>
		<input type="submit" value="Post Details" class="button" />
		<input name="reset" type="reset" class="button" />
	</form:form>
</body>