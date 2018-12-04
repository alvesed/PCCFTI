<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<!-- Latest compiled and minified CSS -->

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="res/css/mostraProdutoStyle.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">

<title>White Market</title>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700" rel="stylesheet">
</head>
<body>
	<jsp:include page = "header.jsp" />
	<div class="containerBody">
	<c:set var="i" value="0"/>
		<c:forEach var="listaVendedor" items="${lista}">
			<div class="containerVendedor">
			<div class="container">
				<table id="cart" class="table table-hover table-condensed">
    				<thead>
						<tr>
							<th style="width:50%">Produto</th>
							<th style="width:10%">Preço</th>
							<th style="width:8%">Quantidade</th>
							<th style="width:22%" class="text-center">Subtotal</th>
							<th style="width:10%"></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="itemPedido" items="${listaVendedor.listaItemPedido}">
						<tr>
							<td data-th="Product">
								<div class="row">
									<div class="col-sm-2 hidden-xs"><img src="${foto[i]}" alt="foto do produto" class="img-responsive"/></div>
									<c:set var="i" value="${i+1}"/>
									<div class="col-sm-10">
										<h4 class="nomargin">${itemPedido.produto.nome}</h4>
										<p>${itemPedido.produto.descricao}</p>
									</div>
								</div>
							</td>
							<td data-th="Price">R$ ${itemPedido.produto.valor}</td>
							<td data-th="Quantity">${itemPedido.quantidade}</td>
							<td data-th="Subtotal" class="text-center">${itemPedido.quantidade * itemPedido.produto.valor}</td>
							<td class="actions" data-th="">
								<a href="verProduto?codigoProduto=${itemPedido.produto.codProduto}"><button type="submit" class="btn btn-default">
				  					Ver Produto
					  			</button></a>							
							</td>
						</tr>
					</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td>Vendedor: ${listaVendedor.vendedor.nome}</td>
						</tr>
					</tfoot>
				</table>
			</div>
			</div>
		</c:forEach>
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="res/js/jquery.barrating.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
<script src="res/js/maskMoney.js"></script>
<script type="text/javascript">
	$(document).ready(function(){

	});
</script>
</html>