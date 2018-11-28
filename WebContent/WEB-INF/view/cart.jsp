<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
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
			
			.h4CartItemQuantidade {
				width: 25px;
				height: 25px;
				font-size: 22px;
				margin-top: 55px;
				margin-left: -65px;
				padding-left: 10px;
				border: none;
				border-radius: 5px;
				
				transition: 300ms linear;
			}
			
			.h4CartItemQuantidade:hover, .h4CartItemQuantidade:focus {
				box-shadow: inset 0 -7px 7px -7px black;
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
			
				<c:when test="${pedido.getListaPedidos().size() >= 1}">
				
					<c:forEach var="itemPedido" items="${pedido.listaPedidos}">
				
					    <div class="divCartItem">
					    
					    	<div class="divCartItemImg">
					    		<img alt="" src="">
					    	</div>
					    	
					    	<h4 class="h4CartItemCod"> ${itemPedido.produto.codProduto} </h4>
					    	<h4> - </h4>
					    	<h4 class="h4CartItemNome"> ${itemPedido.produto.nome} </h4>
					    	
					    	<input type="number" class="h4CartItemQuantidade" value="${itemPedido.quantidade}">
					    	
					    </div>
					    
					    <svg></svg>
					    
					</c:forEach>
					
					<input type="button" id="keepBuying" onClick="window.location.href='<spring:url value="/telaPrincipal" />';">
					<input type="button" id="confirmationBuying" onClick="window.location.href='<spring:url value="/confirmaCompra" />'">
					
				</c:when>
				
				<c:otherwise>
				
					<div class="divCartItem">
				        <a href="#">Que tal comprar umas coisinhas? VAMOS L�!!!</a>
				    </div>
				
				</c:otherwise>
				
			</c:choose>
			
		</div>
		
		<script type="text/javascript">
		
			$(document).ready(function() {
				$('.qtdItemPedido').onchange(function () {
					
					var data = {
						codItemPedido: $(this).parent().find("input[name='codItemPedido']").val(),
						qtdItemPedido: $(this).val()
					}
					
					$.ajax({
						url: "atualizarQuantidadeItemPedido",
						type: "POST",
						data: data,
						contentType: "application/x-www-form-urlencoded; charset = iso-8859-1;",
						
						success:
							alert("Quantidade do item alterada com sucesso!");
					});
				});
			});
		
		
		</script>
	
	</body>
	
</html>