<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>Internet Qualiti Bank</title>
</head>
<body>
	<div align="center">
		<h1>Qualiti Bank</h1>
		<h2>
			<a href="${pageContext.request.contextPath}/cliente?action=novo">Novo
				cliente</a> &nbsp;&nbsp;&nbsp; <a
				href="${pageContext.request.contextPath}/cliente?action=listagem">Listagem
				de clientes</a>
		</h2>
	</div>
	<div align="center">
		<table class="table table-striped table-bordered" style="width: 100%">
			<tr class="thead-dark">
				<th scope="col">ID</th>
				<th scope="col">Name</th>
				<th scope="col">Email</th>
				<th scope="col">Actions</th>
			</tr>
			<c:forEach var="cliente" items="${clientes}">
				<tr>
					<td scope="row"><c:out value="${cliente.id}" /></td>
					<td><c:out value="${cliente.nome}" /></td>
					<td><c:out value="${cliente.email}" /></td>
					<td><a href="${pageContext.request.contextPath}/cliente?action=editar&id=<c:out value='${cliente.id}'/>">Editar</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="${pageContext.request.contextPath}/cliente?action=deletar&id=<c:out value='${cliente.id}'/>">Deletar</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
		<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>

</body>
</html>
