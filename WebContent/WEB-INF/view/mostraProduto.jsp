<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>White Market</title>
</head>
<body>
<jsp:include page = "header.jsp" />
${produto.nome}<br/>
${produto.descricao}<br/>
${produto.estadoProduto}<br/>
${produto.condicao}<br/>
	<label for="quantidade">Quantidade:</label>
	<input type="number" id="quantidade" value="1" name="quantidade" min="1" max="99">
	<button id="adicionaCarrinho">Adicionar ao Carrinho</button>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
	
		$("#adicionaCarrinho").click(function(){
			<c:if test="${empty usuario}">alert("Por favor, faça o login");</c:if>
			<c:if test="${not empty usuario}">adicionaCarrinho(parseInt($("#quantidade").val()))</c:if>
		})
	});
	function adicionaCarrinho(quant) {
		var codproduto = "${produto.codProduto}";
		var data = {
			   cod_produto: codproduto,
			   quantidade: quant
	 		};   
					   
		$.ajax({
			url:"adicionaCarrinho",
			type: "POST",
			async:false,
			data: data,
			cache: true,
			contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
			success: function(resultado){
				
				alert("Produto adicionado ao carrinho com sucesso!");
			}
		});
	}
</script>
</html>