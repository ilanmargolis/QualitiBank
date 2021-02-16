<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>Cadastro de cliente</title>
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

		<h2>
			<c:if test="${cliente != null}">
            			Editar
            		</c:if>
			<c:if test="${cliente == null}">
            			Novo cliente
            		</c:if>
		</h2>
	</div>
	<div>
		<c:if test="${cliente != null}">
			<form action="${pageContext.request.contextPath}/cliente?action=alterar&id=<c:out value='${cliente.id}'/>" method="post">
		</c:if>
		<c:if test="${cliente == null}">
			<form action="${pageContext.request.contextPath}/cliente?action=inserir" method="post">
		</c:if>
		<div class="formPadrao">
			<c:if test="${cliente != null}">
				<input type="hidden" name="id"
					value="<c:out value='${cliente.id}' />" />
			</c:if>

			<div class="form-group">
				<label for="nomeCliente">Nome</label> <input type="text"
					class="form-control" id="nomeCliente" name="nome" size="45"
					value="<c:out value='${cliente.nome}' />">
			</div>

			<div class="form-group">
				<label for="emailCliente">Endereço de email</label> <input
					type="email" class="form-control" id="emailCliente" name="email"
					size="45" aria-describedby="emailHelp" placeholder="Seu email"
					value="<c:out value='${cliente.email}' />"> <small
					id="emailHelp" class="form-text text-muted">Nunca vamos
					compartilhar seu email, com ninguém.</small>
			</div>

			<button type="submit" class="btn btn-primary" value="Salvar">Enviar</button>
		</div>
		</form>
	</div>

	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>

</body>
</html>
