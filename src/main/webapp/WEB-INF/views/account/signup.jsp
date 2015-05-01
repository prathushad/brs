<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
	<body>
		<h1 id="banner">Signup</h1>  
		<form name="f" action="<c:url value='register'/>"
					method="POST"  data-parsley-validate>
			<table>
				<tr>
					<td>First Name:</td>
					<td><input type='text' name='firstname' required data-parsley-required-message="Please enter the First Name"/></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><input type='text' name='lastname' required data-parsley-required-message="Please enter the Last Name"/></td>
				</tr>
				<tr>
					<td>Email Address:</td>
					<td><input type='text' name='email_address' required data-parsley-required-message="Please enter the Email Address"/></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' required data-parsley-required-message="Please enter the Password"/></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit">&nbsp;<input name="reset" type="reset"></td>
				</tr>
			</table>
		</form>
	</body>
</html>