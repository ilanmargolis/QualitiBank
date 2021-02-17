<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/qualitibank.css" />" />
<title>Internet Qualiti Bank</title>
</head>
<body>
	<div align="center">
		<h1>Qualiti Bank</h1>
		<h2>
			<a href="${pageContext.request.contextPath}/cliente?action=novo"><img
				class="icone_g"
				src="${pageContext.request.contextPath}/images/adicionar.png">Novo
				cliente</a>
		</h2>
	</div>
	<div align="center">
		<table class="table table-striped table-bordered" style="width: 100%">
			<tr class="thead-dark">
				<th scope="col">ID</th>
				<th scope="col">Nome</th>
				<th scope="col">Email</th>
				<th scope="col">Ações</th>
			</tr>
			<c:forEach var="cliente" items="${clientes}">
				<tr>
					<td scope="row"><c:out value="${cliente.id}" /></td>
					<td><c:out value="${cliente.nome}" /></td>
					<td><c:out value="${cliente.email}" /></td>
					<td><a
						href="${pageContext.request.contextPath}/conta?action=listagem&cliente_id=<c:out value='${cliente.id}'/>"><img
							class="icone_m"
							src="${pageContext.request.contextPath}/images/contas.png"></a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="${pageContext.request.contextPath}/cliente?action=editar&id=<c:out value='${cliente.id}'/>"><img
							class="icone_p"
							src="${pageContext.request.contextPath}/images/editar.png""></a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="${pageContext.request.contextPath}/cliente?action=deletar&id=<c:out value='${cliente.id}'/>"><img
							class="icone_p"
							src="${pageContext.request.contextPath}/images/deletar.png"
							alt="Deletar""></a></td>
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
