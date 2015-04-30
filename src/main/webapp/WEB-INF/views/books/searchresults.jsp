<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="gutter10">
	<div class="row-fluid" id="">
		<h1 id="skip" style="text-align: center;">Search our collection!</h1>
	</div>
	<div class="row-fluid" style="height: 400px;">
		<%@ include file="../layouts/sidebar.jsp"%>
		<div class="span9 dashboard-rightpanel margin30-b" id="rightpanel">
			<table class="table table-striped">
				<thead>
					<tr class="graydrkbg">
						<th>Title</th>
						<th>Author</th>
						<th>Availability</th>
						<th>Place Hold</th>
					</tr>
				</thead>
				<c:forEach var="book" items="${results}">
					<tr>
						<td style="text-wrap:normal;width:25%;"><c:out value="${book.title}"></c:out></td>
						<td style="text-wrap:normal;width:25%;"><c:out value="${book.author}"></c:out></td>
						<td style="text-wrap:normal;width:25%;">
						<c:if test="${book.isAvailable=='Y'}"><span style="color:green;">Available</span></c:if>
						<c:if test="${book.isAvailable!='Y'}"><span style="color:red;">Not Available</span></c:if></td>
						<td style="text-wrap:normal;width:25%;">
						<c:if test="${book.isAvailable=='Y'}"><a href='<c:url value='/placehold'/>?book=${book.id}'>Hold this book</a></c:if>
						<c:if test="${book.isAvailable!='Y'}"><span>Currently unavailable</span></c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div style="padding-top: 2cm; text-align: center;">
				<a class="btn btn-primary" href="<c:url value='search'/>">Click
					here to go back to search page</a>
			</div>
		</div>
	</div>

</div>