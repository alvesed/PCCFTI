<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
		<title>Insert title here</title>
		
		<style type="text/css">
		
			body {
				padding: 0px;
				margin: 0px;
				background-color: silver;
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
			}
			
			.divCartItem a {
				margin-left: 50px;
				text-decoration: none;
				line-height: 150px;
				font-size: 30px;
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
	
		<div id="container">
			
			<c:choose>
			
				<c:when test="${pedido.getListaPedidos().size() >= 1}">
				
					<c:forEach var="itemPedido" items="${pedido.listaPedidos}">
				
						<input type="hidden" name="codItemPedido" value="{$itemPedido.cod_item_pedido}">
						<input type="text" name="qtdItemPedido" class="qtdItemPedido" value="{$itemPedido.quantidade}">
				
					    <div class="divCartItem">
					        <c:out value="${itemPedido.getInfo()}" />
					    </div>
					    
					    <svg></svg>
					    
					</c:forEach>
					
					<input type="submit" >
					
				</c:when>
				
				<c:otherwise>
				
					<div class="divCartItem">
				        <a href="#"> Que tal comprar umas coisinhas? VAMOS LÁ!!! </a>
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