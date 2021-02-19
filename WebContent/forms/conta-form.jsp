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
	<div class="container-fluid">
		<div class="cabecalho" align="center">
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

			<c:if test="${operacao=null}">
				<h2 class="tipoTela">Nova conta</h2>
			</c:if>
			<c:if test="${operacao != null}">
				<h2 class="tipoTela">Cliente: ${cliente.nome}</h2>
			</c:if>
			<h2 class="tipoTela">Operação(): ${operacao.tipo} /
				${cliente.nome} / ${conta.tipo}</h2>
			<%-- 		<%
		 request.getAttribute("operacao");
		%>
 --%>
		</div>
		<div class="conteudo">
			<form
				action="${pageContext.request.contextPath}/conta?action=inserir"
				method="post">
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
							name="numero" aria-label="Usuário" aria-describedby="numeroConta">
					</div>

					<div class="col-4 input-group">
						<div class="input-group-prepend">
							<label class="input-group-text" for="inputTipo">Tipo de
								conta</label>
						</div>
						<select class="custom-select" id="inputTipo" name="tipo">
							<option selected>Escolher...</option>
							<option value="Conta">Conta</option>
							<option value="Poupanca">Poupança</option>
							<option value="Bonificada">Bonificada</option>
						</select>
					</div>

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
