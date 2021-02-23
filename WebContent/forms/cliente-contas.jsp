<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/qualitibank.css" />" />
<meta charset="ISO-8859-1">
<title>Contas dos clientes</title>
</head>
<body>
	<div class="container-fluid">
		<div class="cabecalho" align="center">
			<div class="logomarca"></div>
			<h2 class="menu">
				<a
					href="${pageContext.request.contextPath}/conta?action=nova&cliente_id=<c:out value='${cliente.id}'/>"><img
					class="icone_g"
					src="${pageContext.request.contextPath}/images/adicionar.png">Nova
					conta</a> &nbsp;&nbsp;&nbsp; <a
					href="${pageContext.request.contextPath}/cliente?action=listagem"><img
					class="icone_g"
					src="${pageContext.request.contextPath}/images/listar.png">Listagem
					de clientes</a>
			</h2>
			<div class="contaCliente">Contas do cliente: ${cliente.nome}</div>
		</div>
		<div class="conteudo">
			<table class="table table-bordered" style="width: 100%">
				<tr class="thead-dark">
					<th scope="col">Número</th>
					<th scope="col">Tipo da conta</th>
					<th scope="col" class="text-right">Saldo</th>
					<th scope="col">Ações</th>
				</tr>
				<c:forEach var="conta" items="${cliente.contas}">
					<tr class="thead-light">
						<td scope="row"><c:out value="${conta.numero}" /></td>
						<td scope="row"><c:out value="${conta.tipo}" /></td>
						<td class="text-right"><fmt:formatNumber
								value="${conta.saldo}" type="currency" /></td>
						<td><a
							href="${pageContext.request.contextPath}/conta?action=creditar&id=<c:out value='${conta.id}'/>"><img
								class="icone_p"
								src="${pageContext.request.contextPath}/images/creditar.png"
								alt="Creditar"></a> &nbsp;&nbsp;&nbsp;&nbsp; <a
							href="${pageContext.request.contextPath}/conta?action=debitar&id=<c:out value='${conta.id}'/>"><img
								class="icone_p"
								src="${pageContext.request.contextPath}/images/debitar.png"
								alt="Debitar"></a> &nbsp;&nbsp;&nbsp;&nbsp; <a
							href="${pageContext.request.contextPath}/conta?action=transferir&id=<c:out value='${conta.id}'/>"><img
								class="icone_p"
								src="${pageContext.request.contextPath}/images/transferir.png"
								alt="Transferir"></a></td>
					</tr>
				</c:forEach>
			</table>
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