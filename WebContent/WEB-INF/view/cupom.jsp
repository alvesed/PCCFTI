<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
               <label for="nome">Código</label>
               <input type="text" class="form-control" id="cod_cupom" placeholder="Código Alfa-Numérico" name="cod_cupom">
            </div>
            <div class="form-group col col-sm-6 col-md-4">
               <label for="qnt_cupons">Quantidade de Cupons</label>
               <input type="text" class="form-control" id="qnt_cupons" placeholder="" name="qnt_cupons">
            </div>
            
            <div class="form-group col-md-4">
               <label for="tipo_desconto">Tipo de Desconto</label>
               <select id="tipo_desconto" class="form-control" name="tipo_desconto">
                  <option value="n" disabled hidden>Selecione</option>
                  <option value="r">Reais</option>
                  <option value="p">Porcentagem</option>
               </select>
            </div>
            
            <div class="form-group col col-sm-6 col-md-4">
               <label for="valor_percent">Percentual de Desconto</label>
               <input type="text" class="form-control" id="valor_percent" placeholder="0" name="valor_percent">
            </div>
            <div class="form-group col col-sm-6 col-md-4">
               <label for="valor_desconto">Valor em Reais</label>
               <input type="text" class="form-control" id="valor_desconto" placeholder="R 0,00" name="valor_desconto">
            </div>
            <div class="form-group col col-sm-6 col-md-4">
               <label for="valor_minimo">Valor Mínimo da Compra</label>
               <input type="text" class="form-control" id="valor_minimo" placeholder="R 0,00" name="valor_minimo">
            </div>
            </div>
            <div class="form-group col col-sm-6  col-md-4">
               <label for="descricao">Descrição</label>
               <input type="descricao" class="form-control" id="descricao" placeholder="" name="descricao">
            </div>
         
         <div class="form-row">
            <div class="form-group col-md-4">
               <label for="dataNascimento">Data de Expiração</label>
               <input type="text" class="form-control" id="data_expiracao" placeholder="Dia/Mês/Ano"name="data_expiracao">
            </div>
          </div>
         
         <button type="submit" class="btn btn btn-primary" id="botao" >Cadastrar</button>
     	
      </form>
   </div>
</div>

</body>
</html>