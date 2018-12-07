<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Cadastro de Cliente</title>
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
      <h3 class="page-header" align="center">Cadastrar Cliente</h3>
      <form id="form" action="efetivarCadastroCliente" method="post">
         <div class="row">
            <div class="form-group col col-sm-6 col-md-4">
               <label for="nome">Nome</label>
               <input type="text" class="form-control" id="nome" placeholder="Nome Completo" name="nome">
            </div>
            <div class="form-group col col-sm-6 col-md-4">
               <label for="cpf">CPF</label>
               <input type="text" class="form-control" id="cpf" placeholder="000.000.000-00" name="cpf">
            </div>
            <div class="form-group col col-sm-6 col-md-4">
               <input type="hidden" value="endereco" class="form-control" id="endereco" placeholder="" name="endereco">
            </div>
            <div class="form-group col col-sm-6 col-md-4">
               <label for="email">E-mail</label>
               <input type="text" class="form-control" id="email" placeholder="" name="email">
            </div>
            <div class="form-group col col-sm-6 col-md-4">
               <label for="telefone">Telefone</label>
               <input type="text" class="form-control" id="telefone" placeholder="(00) 0000-00000" name="telefone">
            </div>
            <div class="form-group col col-sm-6  col-md-4">
               <label for="senha">Senha</label>
               <input type="password" class="form-control" id="senha" placeholder="*****" name="senha">
            </div>
         </div>
         <div class="form-row">
            <div class="form-group col-md-4">
               <label for="dataNascimento">Data de Nascimento</label>
               <input type="text" class="form-control" id="dataNascimento" placeholder="Dia/Mês/Ano"name="dataNascimento">
            </div>
            <div class="form-group col-md-4">
               <label for="sexo">Sexo</label>
               <select id="sexo" class="form-control" name="sexo">
                  <option value="" disabled hidden>Selecione</option>
                  <option value="m">Masculino</option>
                  <option value="f">Feminino</option>
               </select>
            </div>
         </div>
         
         <div class="row">
            <div class="form-group col col-sm-6 col-md-4">
               <label for="nome">Cidade</label>
               <input type="text" class="form-control" id="city" placeholder="Nome da sua Cidade" name="city">
            </div>
            <div class="form-group col col-sm-6  col-md-4">
               <label for="senha">Logradouro</label>
               <input type="text" class="form-control" id="logradouro" placeholder="Rua" name="logradouro">
            </div>
            <div class="form-group col col-sm-6 col-md-4">
               <label for="complement">Complementos</label>
               <input type="text" class="form-control" id="complement" placeholder="opcional" name="complement">
            </div>
            
		            <div class="form-group col col-sm-6 col-md-4">
		            <label>Estado</label>
				   <select class="form-control" id="exampleFormControlSelect1" name="state">
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
               <input type="text" class="form-control" id="postalCode" placeholder="#####-###" name="postalCode">
            </div>
            <div class="form-group col col-sm-6  col-md-4">
               <label for="senha">Número</label>
               <input type="text" class="form-control" id="number" placeholder="Número da sua casa" name="number">
            </div>
         </div>
         <button type="submit" class="btn btn btn-primary" id="botao" >Cadastrar</button>
      </form>
   </div>
</div>
	<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
	<script type="text/javascript" src="res/js/validarCadastro.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous">
	   </script>
	   <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	   <script>
	   
	   $(document).ready(function() {
			
		   $("#email").blur(function(){
			   
			   var verificar_email = $("#email").val();
			
			   verificarLoginRepetido(verificar_email);
			   
			});
		});
	   
	   function verificarLoginRepetido(verificar_email){
		   
		 
			$.ajax({
				url:"verificarLoginRepetido",
				type: "POST",
				async:false,
				data: data,
				cache: true,
				contentType: 'application/x-www-form-urlencoded; charset=iso-8859-1;', 
				success: function(parametro){
						
					// como receber o retorno?
				}
			});
			
			if (verificar_email) {
				alert("Este E-mail ja esta cadastrado, por favor escolha outro");
			} 
			// oq colocar no else?
	   }

	   </script> 
	  
</body>
</html>