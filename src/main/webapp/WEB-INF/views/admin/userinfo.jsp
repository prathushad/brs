<div class="gutter10">
	<div class="row-fluid" id="">
		<h1 id="skip" style="text-align: center;">Manage Users</h1>
	</div>
	<div class="row-fluid" style="height: 400px;">
		<%@ include file="../layouts/sidebar.jsp"%>
		<div class="span9 dashboard-rightpanel margin30-b" id="rightpanel">
			<form name="search" action="<c:url value='finduser'/>"
				method="POST" style="padding-top: 2cm; padding-left: 50px;">
				<table>
					<tr>
						<td>Username</td>
						<td><input type='text' name='username' style="width: 400px" value="${user.userName}" disabled/></td>
					</tr>
					<tr>
						<td>First Name</td>
						<td><input type='text' name='username' style="width: 400px" value="${user.firstName}"/></td>
					</tr>
					<tr>
						<td>Last Name</td>
						<td><input type='text' name='username' style="width: 400px" value="${user.lastName}"/></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><input type='text' name='username' style="width: 400px" value="${user.email}"/></td>
					</tr>
					<tr>
						<td colspan='2' style="text-align: center; padding-left: 40px;"><input
							name="manage" value="Update" class="btn btn-primary"
							type="submit"></td>
					</tr>
				</table>
			</form>
			<div style="text-align: center; padding-left: -40px;">
				<span><a href="manageusers" class="btn btn-primary">Back to Manage Users</a></span>

			</div>
		</div>
	</div>
</div>