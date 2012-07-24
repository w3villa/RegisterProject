<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="hr">&nbsp;</div>


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
	<div class="hr">&nbsp;</div>
	<div class="page_header">Contact Us</div>
	<br />
	<br /> Please mail us at :
	<a href="mailto:mybook@igild.com">mybook@igild.com</a>
	<form:form modelAttribute="contactUsBean" method="Post"
		action="contactus">
		<div id="form_status"></div>
		<c:if test="${not empty error}">
			<div class="errorblock">
				Failed to save form.<br /> Caused : ${error}
			</div>
		</c:if>
		<div class="field">
			<label for="name">Name</label>
			<form:input path="name" />
			<span class="error"><form:errors path="name" /></span>
		</div>
		<div class="field">
			<label for="email">Email</label>
			<form:input path="email" />
			<span class="error"><form:errors path="email" /></span>
		</div>
		<div class="field">
			<label for="contactNo">Contact Number</label>
			<form:input path="contactNo" />
			<span class="error"><form:errors path="contactNo" /></span>
		</div>
		<div class="field">
			<label for="comments">Comments</label>
			<form:textarea path="comments" />
			<span class="error"><form:errors path="comments" /></span>
		</div>
		<input type="submit" value="Save Details" class="button" />
		<input name="reset" type="reset" class="button" />
	</form:form>
</body>