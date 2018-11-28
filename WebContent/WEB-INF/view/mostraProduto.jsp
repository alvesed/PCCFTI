<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<!-- Latest compiled and minified CSS -->
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="res/css/mostraProdutoStyle.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>White Market</title>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700" rel="stylesheet">
</head>
<body>
<jsp:include page = "header.jsp" />
<input type="hidden" id="valorTotal">
<div class="container">
		<div class="card">
			<div class="container-fliud">
				<div class="wrapper row">
					<div class="preview col-md-6">
						<div class="preview-pic tab-content">
						  <div class="tab-pane active" id="pic-1"><img src="http://placekitten.com/400/252" /></div>
						  <div class="tab-pane" id="pic-2"><img src="http://placekitten.com/200/126" /></div>
						  <div class="tab-pane" id="pic-3"><img src="http://placekitten.com/400/252" /></div>
						  <div class="tab-pane" id="pic-4"><img src="http://placekitten.com/400/252" /></div>
						  <div class="tab-pane" id="pic-5"><img src="http://placekitten.com/400/252" /></div>
						</div>
						<ul class="preview-thumbnail nav nav-tabs">
						  <li class="active"><a data-target="#pic-1" data-toggle="tab"><img src="http://placekitten.com/400/252" /></a></li>
						  <li><a data-target="#pic-2" data-toggle="tab"><img src="http://placekitten.com/200/126" /></a></li>
						  <li><a data-target="#pic-3" data-toggle="tab"><img src="http://placekitten.com/200/126" /></a></li>
						  <li><a data-target="#pic-4" data-toggle="tab"><img src="http://placekitten.com/200/126" /></a></li>
						  <li><a data-target="#pic-5" data-toggle="tab"><img src="http://placekitten.com/200/126" /></a></li>
						</ul>
						
					</div>
					<div class="details col-md-6">
						<h3 class="product-title">${produto.nome}</h3>
						<span class="review-no">Produto ${produto.condicao}</span>
						<c:if test="${produto.condicao eq 'Usado'}">
							<span class="review-no">Produto ${produto.condicao}</span>
						</c:if>
						<p class="product-description">${produto.descricao}</p>
						<h4 class="price">Preço unitário: R$ <span id="valorProduto" class="spanPreco"></span></h4>
						<br/>
						<div class="col-md-2">
						<h5 class="sizes">quantidade: <input style="width: 30%; display: inline;" class="form-control col-2" type="number" id="quantidade" value="1" min="1" max="99">
						</h5></div>
						<h5 class="price">preço total: R$ <span class="spanPreco" id="precoTotal"></span></h5>
						<br/>
						<div class="action">
							<button id="adicionaCarrinho" class="add-to-cart btn btn-outline-primary" type="button">Adicionar ao Carrinho</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
<script src="res/js/maskMoney.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		var precoFormatado = "${produto.valor}";
		precoFormatado = precoFormatado.replace(".", ",");
		$("#precoTotal").html(precoFormatado);
		$("#valorProduto").html(precoFormatado);
		
		$("#valorTotal").maskMoney();
		
		$("#quantidade").on("input", function(){
			var quantidade = $(this).val();
			var preco = ${produto.valor};
			var precoTotal = preco*quantidade;
			$("#valorTotal").maskMoney('mask', precoTotal);
			$("#precoTotal").html($("#valorTotal").val());
		});
	
		$("#adicionaCarrinho").click(function(){
			<c:if test="${empty usuario}">alert("Por favor, faça o login");</c:if>
			<c:if test="${not empty usuario}">adicionaCarrinho($("#quantidade").val())</c:if>
		})
	});
	function adicionaCarrinho(quant) {
		var codproduto = "${produto.codProduto}";
		var nomeproduto = "${produto.nome}"
		var data = {
			   codProduto: codproduto,
			   nome: nomeproduto,
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