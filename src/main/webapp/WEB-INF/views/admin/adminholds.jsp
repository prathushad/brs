<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="gutter10">
	<div class="row-fluid" id="">
		<h1 id="skip" style="text-align: center;">Manage Holds</h1>
	</div>
	<div class="row-fluid" style="height: 400px;">
		<%@ include file="../layouts/sidebar.jsp"%>
		<div class="span9 dashboard-rightpanel margin30-b" id="rightpanel">
			<c:if test="${fn:length(results) > 0}">
				<table class="table table-striped">
					<thead>
						<tr class="graydrkbg">
							<th>Title</th>
							<th>Author</th>
							<th>Holded Until</th>
							<th>Release</th>
							<th>Checkout</th>
						</tr>
					</thead>
					<c:forEach var="book" items="${results}">
						<tr>
							<td style="text-wrap: normal; width: 25%;"><c:out
									value="${book.title}"></c:out></td>
							<td style="text-wrap: normal; width: 25%;"><c:out
									value="${book.author}"></c:out></td>
							<td style="text-wrap: normal; width: 20%;"><fmt:formatDate
									type="date" value="${book.pickupDueDate}" /></td>
							<td style="text-wrap: normal; width: 15%;"><a
								class="btn btn-small btn-primary"
								href='<c:url value='/releaseholdbyadmin'/>?book=${book.id}'>Release</a>
							</td>
							<td style="text-wrap: normal; width: 15%;"><a
								class="btn btn-small btn-primary"
								href='<c:url value='/checkout'/>?book=${book.id}'>Checkout</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<c:if test="${fn:length(results) == 0}">
				<div style="text-align: center; padding-top: 40px;">
					<div class="alert alert-danger">There are no books on hold
						with the given criteria.</div>
				</div>
			</c:if>
			<div style="padding-top: 2cm; text-align: center;">
				<a class="btn btn-primary" href="<c:url value='manageholds'/>">Click
					here to go back to manage holds page</a>
			</div>
		</div>
	</div>

</div>