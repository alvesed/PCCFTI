<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	</head>
	
	<body>
		<jsp:include page="header.jsp"></jsp:include>
		
		<div id="divContainer">
			
			Usuario: <input readonly value="${pedido.usuario.nome}">
			Quantidade de Itens: <input readonly value="${pedido.usuario.quantidadeItensPedido}">
			Endereço: 
			<select>
			<option> ${pedido.usuario.endereco}</option>
			<option>Outro</option>
			</select>
			Valor Total: <input readonly>
			<input type="button" value="Confirmar">
			
		</div>
	</body>
</html>