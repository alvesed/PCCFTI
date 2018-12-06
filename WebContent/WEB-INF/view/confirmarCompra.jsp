<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
		<style type="text/css">
			
			.divHeaderMenuOptionSubContainer {
        		margin-top: -47px !important;
        		margin-left: 106px !important;
        		width: calc(100% - 109px) !important;
        	}
        	
        	.divHeaderMenuOptionSubOption {
        		width: calc(100% - 18px) !important;
        	}
        	
        	.label {
        		position: relative;
        		float: left;
        		height: 50px;
        		width: 15%;
        		margin-left: 2.5%;
        		margin-left: 2.5%;
        		margin-top: 20px;
        	}
        	
        	.inputForm {
        		position: relative;
        		float: left;
				height: 50px;
				width: 75%;
				margin-top: 20px;
				margin-left: 2.5%;
				margin-left: 2.5%;
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
			
			<p class="label">Usuario:</p> <input class="inputForm" readonly value="${carrinho.usuario.nome}">
			
			<br><br><br><br><br>
			
			<p class="label">Quantidade de Itens:</p> <input class="inputForm" style="width: 10% !important;" readonly value="${carrinho.listaPedidos.size()}">
			<p class="label" style="margin-left: 10%;">Valor Total:</p> <input class="inputForm" style="width: 37.5% !important; margin-left: 1.5%;"  readonly value="${carrinho.valor_pago}"><br><br><br><br><br><br><br><br>
			<p class="label">Endereço:</p> 
			<select class="inputForm" id="address">
				<option> ${endereco.city}</option>
				<option>Outro</option>
			</select>
			
			<br><br><br><br><br>
			
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