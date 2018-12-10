<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"	%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"	%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Cupom - Cadastrar</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<style type="text/css">
		.error {border: 1px solid #F00;}
		
       	.divHeaderMenuOptionSubContainer {
       		margin-top: -15px !important;
       	}
	</style>
</head>
<body>


<jsp:include page="header.jsp"></jsp:include>
	  <div id="divContainer">
   <div id="main" class="container-fluid">
      <h3 class="page-header" align="center">Cadastrar Cupom</h3>
      <form id="form" action="cadastrarCupom" method="post">
         <div class="row">
            <div class="form-group col col-sm-6 col-md-4">
               <label for="nome">Código *</label>
               <input value = "${produtos.nome}" type="text" class="form-control" id="cod_cupom" placeholder="Código Alfa-Numérico" name="cod_cupom" required>
            </div>
            <div class="form-group col col-sm-6 col-md-4">
               <label for="qnt_cupons">Quantidade de Cupons</label>
               <input type="text" class="form-control" id="qnt_cupons" placeholder="" name="qnt_cupons">
            </div>
            
            <div class="form-group col-sm-6  col-md-4">
               <label for="tipo_desconto">Tipo de Desconto *</label>
               <select id="tipo_desconto" class="form-control" name="tipo_desconto">
                  <option value="r" selected>Reais</option>
                  <option value="p">Porcentagem</option>
               </select>
            </div>
            
            <div class="form-group col col-sm-6 col-md-4">
               <label for="valor_percent">Percentual de Desconto</label>
               <input type="text" class="form-control" id="valor_percent" placeholder="0" name="valor_percent" required disabled>
            </div>
            <div class="form-group col col-sm-6 col-md-4">
               <label for="valor_desconto">Valor em Reais</label>
               <input type="text" class="form-control" id="valor_desconto" placeholder="R 0,00" name="valor_desconto" required>
            </div>
            <div class="form-group col col-sm-6 col-md-4">
               <label for="valor_minimo">Valor Mínimo da Compra</label>
               <input type="text" class="form-control" id="valor_minimo" placeholder="R 0,00" name="valor_minimo">
            </div>
         
         
            <div class="form-group col col-sm-6  col-md-4">
               <label for="descricao">Descrição *</label>
               
               <textarea rows="" cols="" class="form-control" id="descricao" placeholder="" name="descricao" maxlength="250" required>
               </textarea>
            </div>
         
         
            <div class="form-group col-sm-6 col-md-4">
               <label for="dataNascimento">Data de Expiração *</label>
               <input type="text" class="form-control" id="data_expiracao" placeholder="Dia/Mês/Ano"name="data_expiracao" required>
            </div>
          </div>
          
         
          
          <div class="row">
            <div class="col-md-6">
            	<table class="table table-bordered">
	  				<thead>
	    				<tr> 
	    				<th>
	    				<input class="" type="checkbox" value="" id="todos-produtos">
	          	 		</th>
	          	 		<th>
	          	 		Produtos
	          	 		</th>
	          	 		</tr>
	          	 	</thead>
	          	 	
	          	 	<tbody>
	          	 	
	          	 	<c:forEach items="${listProdutos}" var="listProdutos">
		          	 	<tr>
		          	 	<td>
		          	 		<input class="lista-produto" type="checkbox" value="${listProdutos.codProduto}" name="listaProdutos[]">
		          	 	</td>
		          	 	<td>
		          	 	${listProdutos.nome}
		          	 	</td>
		          	 	</tr>
		          	</c:forEach> 	
		          	 	
		          	 	
	          	 	</tbody>
          	 	</table>	

            </div>
          </div>
         
         <input class="lista-produto" type="hidden" value="${usuarioLogado.codigo}" name="usuario">
         <button type="submit" class="btn btn btn-primary" id="botao" >Cadastrar</button>
     	
      </form>
      
      <c:forEach items="${listProdutos}" var="listProdutos">
     <tr>
     <td>
     	<img style="max-height: 50px;max-width: 50px; width: auto; height: auto" src="${listProdutos.urlPrimeiraImagem}" />
     </td>
     <th scope="row"><fmt:formatDate  value="${listProdutos.dataCadastro}" pattern="dd/MM/yyyy"/></th>
      <td><fmt:formatNumber value="${listProdutos.valor}" type="currency"/></td>
      <td>${listProdutos.nome}</td>
      <td>
      <form action="verItemJaCadastrado" method="post">
	      <input type="hidden" value="${listProdutos.codProduto}" name="codProduto"/>  
            <button  type="submit" class="btn btn-default">
  		<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
	  </button>
      </form>

	  </td>
    </tr>
 </c:forEach>
   </div>
</div>
 <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script type="text/javascript">
		
			$(document).ready(function() {
				
				$("#todos-produtos").click(function() {
				
					if($(this).is(":checked"))
						$(".lista-produto").prop('checked', true);
					else
						$(".lista-produto").prop('checked', false);
					
				});
				
				$("#tipo_desconto").change(function(){
					
					
					if($(this).val()=="r"){
					$("#valor_percent").prop('disabled', true);
					$("#valor_percent").val("");
					$("#valor_desconto").prop('disabled', false);
					
					}else if($(this).val()=="p"){
						$("#valor_percent").prop('disabled', false);
						$("#valor_desconto").prop('disabled', true);
						$("#valor_desconto").val("");
						}else{
							$("#valor_percent").prop('disabled', false);
							$("#valor_desconto").prop('disabled', false);							
						}
						
				});
				
			});
		
</script>


</body>
</html>