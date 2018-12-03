<%@page import="br.com.whitemarket.model.ItemPedido"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
		
		<style type="text/css">
		
			body {
				padding: 0px;
				margin: 0px;
				background-color: silver;
			}
			
			body * {
				outline: none;
			}
			
			#divCartContainer {
				position: relative;
				float: top;
				background-color: black;
				height: 500px;
				width: 80%;
				margin-left: calc(50% - 40%);
				background-color: white;
			}
			
			.divCartItem {
				position: relative;
				float: top;
				height: 175px;
				width: 100%;
				margin-top: 5px;
				background-color: silver;
			}
			.divCartItem a {
				margin-left: 50px;
				text-decoration: none;
				line-height: 150px;
				font-size: 30px;
			}
			
			.divCartItemImg {
				position: relative;
				float: left;
				height: 145px;
				width: 145px;
				margin-top: 15px;
				margin-left: 15px;
				background-color: black;
			}
			
			.h4CartItemCod {
				margin-left: 25px;
			}
			
			.h4CartItemNome {
				margin-left: 5px;
			}
			
			.inputCartItemQuantidade {
				width: 25px;
				height: 25px;
				font-size: 22px;
				margin-top: 55px;
				margin-left: -65px;
				padding-left: 10px;
				border: none;
				border-radius: 5px;
				background-color: rgba(245, 245, 245, 0);
				
				transition: 200ms linear;
			}
			
			.inputCartItemQuantidade:hover {
				box-shadow: inset 0 -5px 7px -7px black;
			}
			
			.inputCartItemQuantidade:focus {
			box-shadow: inset 0 -7px 7px -7px black;
				background-color: rgba(245, 245, 245, 1);
			}
			
			h4 {
				position: relative;
				float: left;
				margin-top: 15px;
				margin-left: 10px;
			}
		
			svg {
				box-shadow: 0px 2px 5px #888888;
				margin-left: calc(50% - 250px);
				height: 1px;
				width: 500px;
			}
			
		</style>
	
	</head>
	
	<body>
	
		<jsp:include page="header.jsp" />
	
		<div id="divContainer">
			
			<c:choose>
				<c:when test="${not empty usuario.nome}">
				
					<c:if test="${not empty pedido and pedido.getListaPedidos().size() > 0}">
				
						<c:forEach var="itemPedido" items="${pedido.listaPedidos}">
					
						    <div class="divCartItem">
						    
						    	<img alt="" class="divCartItemImg" src="${itemPedido.produto.descricao}">
						    	
						    	<h4 class="h4CartItemCod"> ${itemPedido.produto.codProduto} </h4>
						    	<h4> - </h4>
						    	<h4 class="h4CartItemNome"> ${itemPedido.produto.nome} </h4> <br>
						    	
						    	<h4 class="h4CartItemName"> ${itemPedido.produto.valor * itemPedido.quantidade} </h4>
						    	
						    	<input type="text" id="produtoQuantidade" class="inputCartItemQuantidade" value="${itemPedido.quantidade}" maxlength="2" max="99">
						    	
						    </div>
						    
						    <svg></svg>
						    
						</c:forEach>
						
						<input type="button" id="keepBuying" onClick="window.location.href='<spring:url value="/telaPrincipal" />';" value="Comprar mais...">
						<input type="button" id="confirmationBuying" onClick="window.location.href='<spring:url value="/confirmaCompra" />'" value="Fechar compra">
					
					</c:if>
					
					<c:if test="${not empty pedido or pedido.getListaPedidos().size() eq 0}">
						<div class="divCartItem">
					        <a href="<spring:url value='/telaPrincipal' />">Que tal comprar umas coisinhas? VAMOS LÁ!!!</a>
					    </div>
					</c:if>
				
				</c:when>
				
				<c:otherwise>
				
					<div class="divCartItem">
				        <a href="<spring:url value='/login' />">Vamos nos logar? Partiu #comprinhas...</a>
				    </div>
				
				</c:otherwise>
			</c:choose>
			
			
			
		</div>
		
		<script type="text/javascript">
		
			$(document).ready(function() {
				
				$('#produtoQuantidade').change(function () {
					
					if ($('#produtoQuantidade').val() <= 0) {
						var confirmation = confirm("Deseja retirar esse item das compras?");
						if (confirmation == true) {
							var removeData = {
								codProduto: $(this).parent().find(".h4CartItemCod").text()
							}
							
							$.ajax({
								url: "removerItemCarrinho",
								type: "POST",
								data: removeData,
								contentType: "application/x-www-form-urlencoded; charset = iso-8859-1;",
								success: function(data){
									alert("OK");
								}
							});
						}
					} else {
						var confirmation = confirm("Deseja alterar esse item das compras?");
						var data = {
							codProduto: $(this).parent().find(".h4CartItemCod").text(),
							qtdProduto: $(this).val()
						}
						
						$.ajax({
							url: "alterarQuantidadeItemCarrinho",
							type: "POST",
							data: data,
							contentType: "application/x-www-form-urlencoded; charset = iso-8859-1;",
							success: function(data){
								alert("OK");
							}
						});
					}
				});
			});
		
		
		</script>
	
	</body>
	
</html>