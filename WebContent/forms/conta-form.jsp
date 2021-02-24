<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/qualitibank.css" />" />
<title>Cadastro de nova conta</title>
</head>
<body>
	<div class="container-fluid">
		<div class="cabecalho" align="center">
			<div class="logomarca"></div>
			<h2 class="menu">
				<a
					href="${pageContext.request.contextPath}/conta?action=listagem&cliente_id=<c:out value='${conta.cliente.id}'/>"><img
					class="icone_g"
					src="${pageContext.request.contextPath}/images/listar.png">Listagem
					de contas</a> &nbsp;&nbsp;&nbsp; <a
					href="${pageContext.request.contextPath}/cliente?action=listagem"><img
					class="icone_g"
					src="${pageContext.request.contextPath}/images/listar.png">Listagem
					de clientes</a>
			</h2>


			<h2 class="tipoTela">
				<c:choose>
					<c:when test="${operacao eq 'nova'}">
						<div class="alert alert-info" role="alert">Nova conta</div>
						<c:set var="tipoPost" scope="request" value="nova" />
						<c:set var="desativar" scope="request" value="false" />
					</c:when>

					<c:when test="${operacao eq 'credito'}">
						<div class="alert alert-success" role="alert">Operação de
							crédito</div>
						<c:set var="tipoPost" scope="request" value="credito" />
						<c:set var="desativar" scope="request" value="true" />
					</c:when>

					<c:when test="${operacao eq 'debito'}">
						<div class="alert alert-danger" role="alert">Operação de
							débito</div>
						<c:set var="tipoPost" scope="request" value="debito" />
						<c:set var="desativar" scope="request" value="true" />
					</c:when>

					<c:when test="${operacao eq 'transferencia'}">
						<div class="alert alert-warning" role="alert">Transferência
							entre contas</div>
						<c:set var="tipoPost" scope="request" value="transferencia" />
						<c:set var="desativar" scope="request" value="true" />
					</c:when>

					<c:otherwise>
						<c:set var="tipoPost" scope="request" value="" />
						<c:set var="desativar" scope="request" value="false" />
					</c:otherwise>
				</c:choose>

				<c:set var="desativar" scope="request" value="true" />
			</h2>

			<h2 class="contaCliente">
				Cliente: ${conta.cliente.nome}
				<c:if test="${operacao eq 'transferencia' || operacao eq 'debito'}"> / Saldo atual: <fmt:formatNumber
						value="${conta.saldo}" type="currency" />
				</c:if>
			</h2>

		</div>
		<div class="conteudo">
			<c:choose>
				<c:when test="${tipoPost == 'credito'}">
					<form
						action="${pageContext.request.contextPath}/conta?action=creditar&conta_id=<c:out value='${conta.id}'/>"
						method="post">
				</c:when>

				<c:when test="${tipoPost == 'debito'}">
					<form
						action="${pageContext.request.contextPath}/conta?action=debitar&conta_id=<c:out value='${conta.id}'/>"
						method="post">
				</c:when>

				<c:when test="${tipoPost == 'transferencia'}">
					<form
						action="${pageContext.request.contextPath}/conta?action=transferir&conta_id=<c:out value='${conta.id}'/>"
						method="post">
				</c:when>

				<c:otherwise>
					<form
						action="${pageContext.request.contextPath}/conta?action=inserir"
						method="post">
				</c:otherwise>
			</c:choose>

			<div class="form-row align-items-center mt-4">
				<c:if test="${cliente != null}">
					<input type="hidden" name="cliente_id"
						value="<c:out value='${cliente.id}' />" />
				</c:if>

				<div class="col-3 input-group">
					<div class="input-group-prepend">
						<span class="input-group-text" id="numeroConta">Número da
							conta</span>
					</div>
					<input type="text" class="form-control"
						value="<c:out value='${conta.numero}' />" placeholder="XXXXX-X"
						name="numero" aria-label="Usuário" aria-describedby="numeroConta"
						<c:if test="${desativar}">readonly</c:if>>

				</div>

				<div class="col-3 input-group">
					<div class="input-group-prepend">
						<label class="input-group-text" for="inputTipo">Tipo de
							conta</label>
					</div>
					<select class="custom-select" id="inputTipo" name="tipo"
						<c:if test="${desativar}">disabled</c:if>>
						<option selected>Escolher...</option>
						<option value="Conta"
							<c:if test="${conta.tipo=='Conta'}">selected</c:if>>Conta</option>
						<option value="Poupanca"
							<c:if test="${conta.tipo=='Poupanca'}">selected</c:if>>Poupança</option>
						<option value="Bonificada"
							<c:if test="${conta.tipo=='Bonificada'}">selected</c:if>>Bonificada</option>
					</select>
				</div>

				<c:if test="${tipoPost != 'nova'}">
					<c:if test="${tipoPost == 'transferencia'}">
						<br />
						<div class="col-4 input-group">
							<div class="input-group-prepend">
								<label class="input-group-text" for="inputTipo">Conta
									destino</label>
							</div>
							<select class="custom-select" id="inputTipo"
								name="contaDestino_id">
								<option selected>Escolher a conta destino</option>
								<c:forEach var="conta" items="${contasDestino}">
									<option value="${conta.id}"><c:out
											value="${conta.numero}" />&nbsp;&nbsp;
										<c:out value="${conta.tipo}" />&nbsp;&nbsp;
										<c:out value="${conta.cliente.nome}" /></option>
								</c:forEach>
							</select>
						</div>
					</c:if>
					<div class="col-2 input-group">
						<div class="input-group-prepend">
							<span class="input-group-text" id="valor">Valor</span>
						</div>
						<fmt:setLocale value="en_US" />
						<input type="text" class="form-control text-right"
							value="<fmt:formatNumber value="0.00" type="currency" currencySymbol=""/>"
							name="valor" aria-label="Valor" aria-describedby="valor">
					</div>
				</c:if>
			</div>

			<div class="col mt-2">
				<button type="submit" class="btn btn-primary" value="Salvar">Enviar</button>
			</div>
			</form>
		</div>
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
