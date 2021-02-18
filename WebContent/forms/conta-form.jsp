<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/qualitibank.css" />" />
<title>Cadastro de nova conta</title>
</head>
<body>
	<div align="center">
		<div class="logomarca"></div>
		<h2 class="menu">
			<a
				href="${pageContext.request.contextPath}/conta?action=listagem&cliente_id=<c:out value='${cliente.id}'/>"><img
				class="icone_g"
				src="${pageContext.request.contextPath}/images/listar.png">Listagem
				de contas</a> &nbsp;&nbsp;&nbsp; <a
				href="${pageContext.request.contextPath}/cliente?action=listagem"><img
				class="icone_g"
				src="${pageContext.request.contextPath}/images/listar.png">Listagem
				de clientes</a>
		</h2>
		<h2 class="tipoTela">Nova conta</h2>
	</div>
	<div>
		<form action="${pageContext.request.contextPath}/conta?action=inserir"
			method="post">
			<div class="formPadrao">
				<c:if test="${cliente != null}">
					<input type="hidden" name="cliente_id"
						value="<c:out value='${cliente.id}' />" />
				</c:if>

				<div class="form-group">
					<label for="numeroConta">Número</label> <input type="text"
						class="form-control" id="numeroCliente" name="numero" size="45"
						value="<c:out value='${conta.numero}' />"> <select
						name="tipo">
						<option value="Conta">Conta</option>
						<option value="Poupanca">Poupança</option>
						<option value="Bonificada">Bonificada</option>
					</select>
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
