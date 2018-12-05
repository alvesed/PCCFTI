<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
		<style type="text/css">
			
			.inputForm {
				height: 50px;
				width: 700px;
				margin-top: 20px;
				margin-left: 20px;
				line-height: 50px;
				font-size: 30px;
				padding-left: 10px;
				border-radius: 8px;
				color: rgba(20, 20, 20, 1);
			}
			
		</style>
		
	</head>
	
	<body>
		<jsp:include page="header.jsp"></jsp:include>
		
		<div id="divContainer">
			
			Usuario: <input class="inputForm" readonly value="${carrinho.usuario.nome}">
			<br><br>Quantidade de Itens: <input class="inputForm" style="width: 150px !important;" readonly value="${carrinho.listaPedidos.size()}">
			Valor Total: <input class="inputForm" style="width: 350px !important;"  readonly value="${carrinho.valor_pago}">
			<br><br>Endereço: 
			<select class="inputForm" id="address">
				<option> ${carrinho.usuario.endereco}</option>
				<option>Outro</option>
			</select>
			
			<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
			<input type="button" id="confirmar"/>
			 <input alt="Pague com PagSeguro" name="submit"  type="image" id="confirmar" 
			 src="https://p.simg.uol.com.br/out/pagseguro/i/botoes/pagamentos/120x53-pagar.gif"/>  
			
		</div>
		
		<script type="text/javascript">
			$(document).ready(function() {
				$('#confirmar').click(function(){
					window.location.href = 'cadastrarEndereco';
				});
				
				$('#address').change(function(){
					if($(this).val() === "Outro") {
						window.location.href = 'cadastrarEndereco';
					}
				});
			})
		</script>
		
	</body>
</html>