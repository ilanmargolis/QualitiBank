<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/qualitibank.css" />" />
<title>Cadastro de cliente</title>
</head>
<body>
	<div class="container-fluid">
		<div class="cabecalho" align="center">
			<div class="logomarca"></div>
			<h2 class="menu">
				<a href="${pageContext.request.contextPath}/cliente?action=listagem"><img
					class="icone_g"
					src="${pageContext.request.contextPath}/images/listar.png">Listagem
					de clientes</a>
			</h2>

			<h2 class="tipoTela">
				<c:if test="${cliente != null}">
            			Editar cadastro
            		</c:if>
				<c:if test="${cliente == null}">
            			Novo cliente
            		</c:if>
			</h2>
		</div>
		<div class="conteudo">
			<c:if test="${cliente != null}">
				<form
					action="${pageContext.request.contextPath}/cliente?action=alterar"
					method="post">
			</c:if>
			<c:if test="${cliente == null}">
				<form
					action="${pageContext.request.contextPath}/cliente?action=inserir"
					method="post">
			</c:if>
			<div class="form-row align-items-center mt-4">
				<c:if test="${cliente != null}">
					<input type="hidden" name="id"
						value="<c:out value='${cliente.id}' />" />
				</c:if>

				<div class="col-9 input-group mb-2">
					<div class="input-group-prepend">
						<span class="input-group-text" id="nomeCliente">Nome do
							cliente</span>
					</div>
					<input type="text" class="form-control"
						value="<c:out value='${cliente.nome}' />"
						placeholder="Digite o nome do cliente" name="nome"
						aria-label="Usuário" aria-describedby="nomeCliente">
				</div>

				<div class="col-9 input-group">

					<div class="input-group-prepend">
						<span class="input-group-text" id="emailCliente">E-mail</span>
					</div>
					<input type="email" class="form-control"
						value="<c:out value='${cliente.email}' />"
						placeholder="Digite o e-mail do cliente" name="email"
						id="emailCliente" aria-label="Usuário"
						aria-describedby="emailCliente">
				</div>

			</div>
			<div class="col mt-2">
				<button type="submit" class="btn btn-primary" value="Salvar">Enviar</button>
			</div>
		</div>
		</form>
	</div>
	<div class="rodape">
		<p>&copy2021 Qualiti innovative learning & Ilan Margolis</p>
	</div>
	</div>

	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>

</body>
</html>
