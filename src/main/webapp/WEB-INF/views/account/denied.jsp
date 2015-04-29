<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>
	
		<h1 id="banner">Login Failed!</h1>
	
		<hr />
	
		<c:if test="${not empty error}">
			<div style="color:red">
				Login was unsuccessful.
			</div>
		</c:if>
	
		<p class="message">Access denied!</p>
		<a href="/login">Go back to login page</a> 
	</body>
</html>