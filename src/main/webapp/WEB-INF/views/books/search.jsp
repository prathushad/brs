<div class="gutter10">
	<div class="row-fluid" id="">
		<h1 id="skip" style="text-align: center;">Search our collection!</h1>
	</div>
	<div class="row-fluid" style="height: 400px;">
		<%@ include file="../layouts/sidebar.jsp"%>
		<div class="span9 dashboard-rightpanel margin30-b" id="rightpanel">
			<form name="search" action="<c:url value='dosearch'/>" method="POST"
				style="padding-top: 2cm; padding-left: 50px;">
				<table>
					<tr>
						<td>Enter the book title:</td>
						<td><input type='text' name='title' style="width: 400px" /></td>
					</tr>
					<tr>
						<td colspan='2' style="text-align: center;"><input
							name="search" value="Search" class="btn btn-primary"
							type="submit"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

</div>