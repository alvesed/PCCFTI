<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"	%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"	%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-maskmoney/3.0.2/jquery.maskMoney.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<style type="text/css">
		.table {border: 1px solid #d7cec7; margin-top: 20px;}
		.table tr:nth-child(even) {background: #d7cec7;}
	</style>

</head>
<body>
<jsp:include page = "header.jsp" />

<div id="divContainer">

<h2>Meus pedidos</h2>

<div id="container"></div>
<table class="table table-striped" style="position: relative;float: top">
  <thead>
    <tr>
      <th scope="col">Data do Pedido</th>
      <th scope="col">Valor do Pedido</th>
      <th scope="col">Qtd. Itens</th>
      <th scope="col">Ver Mais</th>
      <th scope="col">Documento</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${listPedidos}" var="listPedidos">
     <tr>
      <th scope="row"><fmt:formatDate  value="${listPedidos.data_compra}" pattern="dd/MM/yyyy"/></th>
      <td><fmt:formatNumber value="${listPedidos.valor_pago}" type="currency"/></td>
      <td>${listPedidos.quantidadeItensPedido}</td>
      <td>
      
      <form action="verCarrinhoJaCadastrado" method="post">
	      <input type="hidden" value="${listPedidos.cod_pedido}" name="cod_pedido"/>  
                  <button type="submit" class="btn btn-default">
			  		<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
				  </button>
				 
      </form>

	  </td>
	  <td>
	  	 <a href="gerarPdf?cod_pedido=${listPedidos.cod_pedido}" class="btn btn-primary">Gerar PDF</a>
	  </td>
    </tr>
 </c:forEach>
  </tbody>
</table>
</div>
</body>
</html>