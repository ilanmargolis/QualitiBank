<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">

<meta charset="ISO-8859-1">
<title>Qualiti bank</title>
</head>
<body>
	<center>
		<h1>Qualiti Bank</h1>
		<h2>
			<a href="${pageContext.request.contextPath }/cliente?action=novo">Novo
				cliente</a> &nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath }/cliente?action=listagem">Listagem de clientes</a>

		</h2>
	</center>
	<div align="center">
		<table class="table table-dark table-hover table-bordered"
			style="width: 100%">
			<tr class="thead-dark">
				<th scope="col">ID</th>
				<th scope="col">Nome</th>
				<th scope="col">Email</th>
				<th scope="col">Ações</th>
			</tr>
			<c:forEach var="customer" items="${customers}">
				<tr>
					<td scope="row"><c:out value="${customer.id}" /></td>
					<td><c:out value="${customer.name}" /></td>
					<td><c:out value="${customer.email}" /></td>
					<td><a href="edit?id=<c:out value='${customer.id}'/>">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="delete?id=<c:out value='${customer.id}'/>">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
		integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"
		integrity="sha384-LtrjvnR4Twt/qOuYxE721u19sVFLVSA4hf/rRt6PrZTmiPltdZcI7q7PXQBYTKyf"
		crossorigin="anonymous"></script>

</body>
</html>