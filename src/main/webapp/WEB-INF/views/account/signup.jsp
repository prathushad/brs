<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
	<body>
		<h1 id="banner">Signup</h1>  
		<form name="f" action="<c:url value='register'/>"
					method="POST">
			<table>
				<tr>
					<td>First Name:</td>
					<td><input type='text' name='firstname' /></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><input type='text' name='lastname' /></td>
				</tr>
				<tr>
					<td>Email Address:</td>
					<td><input type='text' name='email_address' /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password'></td>
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