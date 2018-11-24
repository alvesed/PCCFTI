<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Vender Item</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	</head>
	<body>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous">
	    </script>
	    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		
		<div id="main" class="container-fluid">
			<h3 class="page-header" align="center">Cadastrar Produto</h3>
			
			<form action="adicionaItem" method="post">
				<div class="row">
					<div class="form-group col-md-2">
						<!-- label e input-->
						<label for="campoNome">Nome do produto:</label>
						<input class="form-control" id="nome" type="text" name="nome" minlength="1" maxlength="50" value="">
					</div>
				</div>
			</form>
		</div>
		<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
		<script>
			/*$(document).ready(function () { 
	   			var $seuCampoCpf = $("#cpf");
	    		$seuCampoCpf.mask('000.000.000-00', {reverse: true});
	    		$("#telefone").mask('(00) 0000-0000');
	    		$("#salario").mask('000.000.000.000.000,00', {reverse: true});
	    		$("#va").mask('000.000.000.000.000,00', {reverse: true});
	    		$("#vr").mask('000.000.000.000.000,00', {reverse: true});
	    		$("#vt").mask('000.000.000.000.000,00', {reverse: true});
				if ($("#cargo").val() != "Professor") {
	    			$("#disciplina").hide();
				}
			 });*/
		</script>
	</body>
</html>