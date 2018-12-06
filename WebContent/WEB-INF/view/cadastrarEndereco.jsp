<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Cadastro de Endereço</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		
		  	<style type="text/css">
		  	
				.error {
					border: 1px solid #F00;
				}
				
				.divHeaderMenuOptionSubContainer {
	        		margin-top: -15px !important;
	        	}
					
  			</style>
  			
	</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	  <div id="divContainer">
   <div id="main" class="container-fluid">
      <h3 class="page-header" align="center">Cadastrar Endereço</h3>
      <form id="form" action="confirmaCompra">
         <div class="row">
            <div class="form-group col col-sm-6 col-md-4">
               <label for="nome">Cidade</label>
               <input readonly value="${endereco.city}" type="text" class="form-control" id="nome" placeholder="Nome da sua Cidade">
            </div>
            <div class="form-group col col-sm-6 col-md-4">
               <label for="nome">Logradouro</label>
               <input readonly value="${endereco.logradouro}" type="text" class="form-control" id="logradouro" placeholder="Nome da sua Cidade" >
            </div>
            <div class="form-group col col-sm-6 col-md-4">
               <label for="cpf">Complementos</label>
               <input readonly value="${endereco.complement}" type="text" class="form-control" id="cpf" placeholder="opcional" >
            </div>
            
		            <div class="form-group col col-sm-6 col-md-4">
		            <label>Estado</label>
				   <select  disabled="disabled" class="form-control" id="state" name="state">
						<option value="AC">Acre</option>
						<option value="AL">Alagoas</option>
						<option value="AP">Amapá</option>
						<option value="AM">Amazonas</option>
						<option value="BA">Bahia</option>
						<option value="CE">Ceará</option>
						<option value="DF">Distrito Federal</option>
						<option value="ES">Espírito Santo</option>
						<option value="GO">Goiás</option>
						<option value="MA">Maranhão</option>
						<option value="MT">Mato Grosso</option>
						<option value="MS">Mato Grosso do Sul</option>
						<option value="MG">Minas Gerais</option>
						<option value="PA">Pará</option>
						<option value="PB">Paraíba</option>
						<option value="PR">Paraná</option>
						<option value="PE">Pernambuco</option>
						<option value="PI">Piauí</option>
						<option value="RJ">Rio de Janeiro</option>
						<option value="RN">Rio Grande do Norte</option>
						<option value="RS">Rio Grande do Sul</option>
						<option value="RO">Rondônia</option>
						<option value="RR">Roraima</option>
						<option value="SC">Santa Catarina</option>
						<option value="SP">São Paulo</option>
						<option value="SE">Sergipe</option>
						<option value="TO">Tocantins</option>
					</select>
		 	 </div>
            
            <div class="form-group col col-sm-6 col-md-4">
               <label for="email">CEP</label>
               <input readonly value="${endereco.postalCode}"  readonly type="text" class="form-control" id="postalCode" placeholder="#####-###" >
            </div>
            <div class="form-group col col-sm-6  col-md-4">
               <label for="senha">Número</label>
               <input readonly value="${endereco.number}" type="text" class="form-control" id="number" placeholder="Número da sua casa">
            </div>
         </div>
       
         <button type="submit" class="btn btn btn-primary" id="botao" ><- Voltar</button>
      </form>
   </div>
</div>
</body>

	<script type="text/javascript">
	document.getElementById('state').value = '${endereco.state}';
			$(document).ready(function() {
				$('#confirmar').click(function(){
					alert('Parabens!');
					window.location.href = 'cadastrarEndereco';
				});		
			})
			
			
		
		</script>
</html>