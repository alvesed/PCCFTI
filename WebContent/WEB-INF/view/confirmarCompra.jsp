<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
		<script type="text/javascript">
			function verificaSessao() {
				<c:if test="${empty usuarioLogado.email.length()}">
					window.location.href="<c:url value='/login'/>";
	    		</c:if>
	    	}
		</script>
		
	</head>
	
	<body  onload="javascript:verificaSessao()">
		<jsp:include page="header.jsp"></jsp:include>
		
		<div id="divContainer">
			
			Usuario: <input readonly value="${pedido.usuario.nome}">
			Quantidade de Itens: <input readonly value="${pedido.listaPedidos.size()}">
			Endereço: 
			<select>
			<option> ${pedido.usuario.endereco}</option>
			<option>Outro</option>
			</select>
			Valor Total: <input readonly value="${pedido.valor_pago}">
			</br>
			 <input alt="Pague com PagSeguro" name="submit"  type="image" id="confirmar"   
				src="https://p.simg.uol.com.br/out/pagseguro/i/botoes/pagamentos/120x53-pagar.gif"/>  
			
		</div>
		
		<script type="text/javascript">
		
			$(document).ready(function() {
				$('#confirmar').click(function(){
					alert('Parabens!');
					window.location.href = 'finalizarCompra';
				});
			})
		
		</script>
		
	</body>
</html>