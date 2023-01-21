<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/app.js"></script>

<title>Login | Register</title>
</head>
<body>
	<div class="row text-center my-3">
		<h1 class="col">Login and Registration</h1>
	</div>
	<div class="row mx-5">
		<div class="col">
			<div class="d-flex flex-column justify-content-center align-items-center bg-light rounded py-4 shadow">
				<h2>Create an account</h2>
				<form:form class="form" action="/register" method="POST" modelAttribute="user">
					<div>
						<form:label class="form-label" path="name">Name</form:label>
						<form:input class="form-control" path="name"/>
						<form:errors path="name"/>
					</div>
					<div>
						<form:label class="form-label" path="email">Email</form:label>
						<form:input class="form-control" path="email" type="email"/>
						<form:errors path="email"/>
					</div>
					<div>
						<form:label class="form-label" path="password">Password</form:label>
						<form:input class="form-control" path="password" type="password"/>
						<form:errors path="password"/>
					</div>
					<div>
						<form:label class="form-label" path="confirmPassword">Confirm Password</form:label>
						<form:input class="form-control" path="confirmPassword" type="password"/>
						<form:errors path="confirmPassword"/>
					</div>
					<button class="btn btn-primary mt-3">Create Account</button>
				</form:form>
			</div>
		</div>
		<div class="col">
			<div class="d-flex flex-column justify-content-center align-items-center bg-light rounded py-4 shadow">
				<h2>Login</h2>
				<form:form action="/login" method="POST" modelAttribute="logUser">
					<div>
						<form:label class="form-label" path="email">Email</form:label>
						<form:input class="form-control" path="email" type="email"/>
						<form:errors path="email"/>
					</div>
					<div>
						<form:label class="form-label" path="password">Password</form:label>
						<form:input class="form-control" path="password" type="password"/>
						<form:errors path="password"/>
					</div>
					<button class="btn btn-success mt-3">Login</button>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>