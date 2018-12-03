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
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="res/css/mostraProdutoStyle.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
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
							<div class="tab-pane active border border-primary rounded" style="border-color: #646464 !important;" id="pic-0"><img class="img-fluid" src="${produto.listaFotos[0].urlFoto}" /></div>
							<c:forEach items="${fotos}" var="foto" begin="1" varStatus="i">
								<div class="tab-pane" id="pic-${i.index}"><img src="${foto.urlFoto}" /></div>
							</c:forEach>
						</div>
						<ul class="preview-thumbnail nav nav-tabs border border-primary" style="border-color: #646464 !important">
						  <li class="active"><a data-target="#pic-0" data-toggle="tab"><img class="border border-primary" style="border-color: #646464 !important;" src="${produto.listaFotos[0].urlFoto}" /></a></li>
						  
						  <c:forEach items="${fotos}" var="foto" begin="1" varStatus="i">
						    	<li><a data-target="#pic-${i.index}" data-toggle="tab"><img class="border border-primary" style="border-color: #646464 !important;" src="${foto.urlFoto}" /></a></li>
							</c:forEach>
						</ul>
						
					</div>
					<div class="details col-md-6">
						<h3 class="product-title">${produto.nome}</h3>
						<span style="padding-bottom:px; margin-bottom: 0px;">Produto ${produto.condicao}</span>
						<div style="height: 1%"></div>
						<div class="border border-primary rounded" style="height: 7em; border-color: #646464 !important;">
						<p class="product-description">${produto.descricao}</p>
						</div>
						<c:if test="${produto.condicao eq 'usado'}">
							<span style="font-size: 12px; padding-top: 2px;">Estado de conservação do produto: ${produto.estadoProduto}</span>
						</c:if>
						<br/>
						<h4 class="price">Preço unitário: R$ <span id="valorProduto" class="spanPreco"></span></h4>
						<h5 class="sizes">quantidade: <input class="form-control col-2" type="number" id="quantidade" value="1" max="99">
						</h5>
						<h5 class="price">preço total: R$ <span class="spanPreco" id="precoTotal"></span></h5>
						<div class="action">
							<c:if test="${empty usuarioLogado}">
								<button id="erro" title="Por favor, faça o login" class="erro-carrinho btn btn-outline-primary" type="button" disabled>Adicionar ao Carrinho</button>
							</c:if>
							
							<c:if test="${not empty usuarioLogado}">
								<button id="adicionaCarrinho" class="add-to-cart btn btn-outline-primary" type="button">Adicionar ao Carrinho</button>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
	<div class="card">

<h1>COMMENT BOX</h1>
<!-------Wrap------------>
		<div id="wrap">
			<div id="main">
				<div class="row">
					<div class="col-md-5">
						<h3 class="heading">Comments and Responses</h3>
					</div>
					<div class="col-md-7">
						<div id="upper_blank"></div>
					</div>
				</div>
			</div>


			<!------------Form Start---------->

			<div id='form'>
				<div class="row">
					<div class="col-md-12">

						<form action="" method="POST" id="commentform">

							
							<div id="comment-message" class="form-row">
								<textarea name="comentario" placeholder="Digite seu comentario" id="comment" style="resize: none"rows="4" cols="100"	></textarea>
							</div>
							<a href="#"><input type="submit" name="dsubmit"
								id="commentSubmit" value="Submit Comment"></a> 
						</form>

					</div>
				</div>
			</div>
		</div>
		</div>
	
	</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
<script src="res/js/maskMoney.js"></script>
<script type="text/javascript">
	var adicionou = false;
	
	$(document).ready(function(){
		
		$("#valorTotal").maskMoney();
		
		var precoFormatado = "${produto.valor}";
		precoFormatado = precoFormatado.replace(".", ",");
		
		$("#precoTotal").html(precoFormatado);
		$("#valorProduto").html(precoFormatado);
		
		$("#quantidade").on("input", function(){
			var quantidade = $(this).val();
			var preco = ${produto.valor};
			if (quantidade > 99){
				$(this).val(99);
				var precoTotal = preco * 99;
			} else {
				var precoTotal = preco*quantidade;
			}
			$("#valorTotal").maskMoney('mask', precoTotal);
			$("#precoTotal").html($("#valorTotal").val());
		});
	
		$("#adicionaCarrinho").click(function(){
			if(adicionou == false){
				if (parseInt($("#quantidade").val()) == 0){
					alert("Selecione a quantidade");
					return false;
				} else {
					adicionaCarrinho($("#quantidade").val());
				}
			}
		});
	})
	
	function adicionaCarrinho(quant) {
		var codproduto = "${produto.codProduto}";
		var nomeproduto = "${produto.nome}"
		var urlPrimeiraFoto = "${produto.listaFotos[0].urlFoto}";
		var data = {
			   codProduto: codproduto,
			   nome: nomeproduto,
			   url: urlPrimeiraFoto,
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
				$("#adicionaCarrinho").html("Produto adicionado!").addClass("adicionado-carrinho").removeClass("add-to-cart");
				adicionou = true;
			}
		});
	}
</script>
</html>