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
<title>Meus Produtos</title>
</head>
<body>
<jsp:include page = "header.jsp" />
<table class="table table-striped" style="position: relative;float: top">
  <thead>
    <tr>
      <th scope="col">Foto</th>
      <th scope="col">Data Cadastro</th>
      <th scope="col">Valor do produto</th>
      <th scope="col">Vendas</th>
      <th scope="col">Ver Mais</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${listProdutos}" var="listProdutos">
  teste
     <tr>
     <td>
     	<img style="height: 50px" src="${listProdutos.urlPrimeiraImagem}" />
     </td>
     <th scope="row"><fmt:formatDate  value="${listProdutos.dataCadastro}" pattern="dd/MM/yyyy"/></th>
      <td><fmt:formatNumber value="${listProdutos.valor}" type="currency"/></td>
      <td>${listProdutos.quantidadeDeVendas}</td>
      <td>
      <button type="button" class="btn btn-default">
  		<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
	  </button>
	  </td>
    </tr>
 </c:forEach>
  </tbody>
</table>

</body>
</html>