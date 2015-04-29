<%@page session="true"%>
<%@page import="java.util.Set"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="navbar navbar-fixed-top" id="masthead" role="banner">
	<div class="navbar-inner">
		<div class="container">
			<div style="float:left">
				<a href="home" style="padding-right: 550px; color: white">BOOK
					RESERVATION SYSTEM</a>
			</div>
			<div  style="float:right">
				<ul class="nav">
					<sec:authorize access="isAuthenticated()">
						<li><a href="j_spring_security_logout">Logout</a></li>
					</sec:authorize>
					<sec:authorize access="!isAuthenticated()">
						<li><a href="<c:url value='/login'/>">Login</a></li>
					</sec:authorize>
					<sec:authorize access="!isAuthenticated()">
						<li><a href="<c:url value='/signup'/>">Signup</a></li>
					</sec:authorize>
				</ul>
			</div>
		</div>
	</div>
</div>

