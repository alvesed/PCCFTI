<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Ver Item</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	</head>
	<body>
	<jsp:include page = "header.jsp" />
	<div id="divContainer">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous">
	    </script>
	    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		
		<div id="main" class="container-fluid">
			<h3 class="page-header" align="center">Ver Produto</h3>
			<form id="form">
				<div class="row">
                    <div class="form-group col-md-2">
                        <label hidden id="cod_label" for="campoCod">Código do Produto:</label>
                        <input hidden class="form-control" id="cod" type="text" name="codProduto" value="${produto.codProduto}" readonly>
                        <input class="form-control" id="qtd_files" type="hidden" name="qtdFiles" value="${produto.qtdFiles}" readonly>
                    </div>
                </div>
				<div class="row">
					<div class="form-group col-md-2">
						<!-- label e input-->
						<label for="campoNome">Nome do produto</label>
						<input  readonly class="form-control" id="nome" type="text" name="nome" minlength="1" maxlength="50" value="${produto.nome}">
						<span style="color: red" class="erro_nome">Nome inválido.</span>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-3">
						<!-- label e input-->
						<label>Descrição</label>
						<textarea readonly style="resize: none" class="form-control" id="descricao" name="descricao" rows="4" cols="40">${produto.descricao}</textarea>
						<span style="color: red" class="erro_descricao">Descrição inválida.</span>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-2">
						<!-- label e input-->
						<label>Condição</label>
						<select readonly onchange="this.value = '${produto.condicao}'" class="form-control" name="condicao" id="condicao">
							<option value="novo" <c:if test="${produto.condicao eq 'novo'}">selected</c:if>>Novo</option>
							<option value="usado" <c:if test="${produto.condicao eq 'usado'}">selected</c:if>>Usado</option>
						</select>
					</div>
					<div class="form-group col-md-2" id="estado">
						<!-- label e input-->
						<label>Estado de conservação</label>
						<select class="form-control" name="estadoProduto" id="sta" readonly onchange="this.value = '${produto.estadoProduto}'">
							<option value="seminovo" <c:if test="${produto.estadoProduto eq 'seminovo'}">selected</c:if>>Seminovo</option>
							<option value="usado" <c:if test="${produto.estadoProduto eq 'usado'}">selected</c:if>>De 3 à 6 meses de uso</option>
							<option value="muitousado" <c:if test="${produto.estadoProduto eq 'muitousado'}">selected</c:if>>De 6 à 12 meses de uso</option>
							<option value="velho" <c:if test="${produto.estadoProduto eq 'velho'}">selected</c:if>>Acima de 1 ano de uso</option>
						</select>
					</div>
				</div>
				
				  <c:forEach items="${produto.listaFotos}" var="listFotos">
						<img style="max-height: 250px;max-width: 250px; width: auto; height: auto" src="${listFotos.urlFoto}" />
				  </c:forEach>
				
				<div class="row">
					<div class="form-group col-md-2">
						<!-- label e input-->
						<label>Valor(R$)</label>
						<input type="text" class="form-control" id="valor" name="valor" value="${produto.valor}" readonly>
						<span style="color: red" class="erro_valor">Valor inválido.</span>
					</div>
				</div>
				<hr />
                <div id="actions" class="row">
                    <div class="col-md-12">
                        <a href='verProdutos' class="btn btn-dark">Voltar</a>
                    </div>
                </div>
                <br>
			</form>
		</div>
		</div>
		<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
		<script>		
			var erro_foto = false;
			$(document).ready(function () {			
	    		$("#valor").mask('000.000.000.000.000,00', {reverse: true});
				if ($("#condicao").val() != "usado") {
	    			$("#estado").hide();
				}
				
				$(".erro_nome").hide();
				$(".erro_descricao").hide();
				$(".erro_foto").hide();
				$(".erro_valor").hide();
				
				if ($("#qtd_files").val() < 1 && $("#cod").val() > 0) {
					erro_foto = true;
					$("#fotos").css("border", "1px solid red");
					$(".erro_foto").show();
				} else {
					erro_foto = false;
				}
			 })
			
			//VALIDAÇÕES
			var erro_nome = true;
			var erro_descricao = true;
			var erro_valor = true;
			if ($("#cod").val() > 0) {
				var erro_nome = false;
				var erro_descricao = false;
				var erro_foto = true;
				var erro_valor = false;
			}
			
			function Checkfiles(){
			    var fup = document.getElementById('filename');
			    var fileName = fup.value;
			    var ext = fileName.substring(fileName.lastIndexOf('.') + 1);

			    if(ext =="jpeg" || ext=="png"){
			        return true;
			    }
			    else {
			    	return false;
			    }
			}
			
			$("#uploadFile").on("click", function() {
				if ($("#fotos").val() != 0) {
					if ($("#qtd_files").val() < 5) {
						var qtd = $("#qtd_files").val();
						$("#qtd_files").val(+qtd + 1);
						erro_foto = false;
						$("#form").submit();
					} else {
						alert("Você atingiu o limite de fotos por produto.");
						return false;
					}
				} else {
					alert("Adicione uma imagem antes de clicar em 'Upload'");
					return false;
				}
			})
			
			$("#condicao").on("change", function() {
	    		if ($(this).val() == "usado") {
	    			$("#estado").show();
	    		} else {
	    			$("#estado").hide();
	    		}
	    	})
	    	
	    	$("#nome").on("input", function() {
	    		if ($(this).val() == "") {
	    			$(".erro_nome").show();
	    			$("#nome").css("border", "1px solid red");
	    			erro_nome = true;
	    		} else {
	    			$(".erro_nome").hide();
	    			$("#nome").css("border", "1px solid green");
	    			erro_nome = false;
	    		}
	    	})
	    	
	    	$("#descricao").on("input", function() {
	    		if ($(this).val().trim() == "") {
	    			$(".erro_descricao").show();
	    			$("#descricao").css("border", "1px solid red");
	    			erro_descricao = true;
	    		} else {
	    			$(".erro_descricao").hide();
	    			$("#descricao").css("border", "1px solid green");
	    			erro_descricao = false;
	    		}
	    	})
	    	
	    	$("#valor").on("change", function() {
	    		if ($(this).val().trim() == "") {
	    			$(".erro_valor").show();
	    			$("#valor").css("border", "1px solid red");
	    			erro_valor = true;
	    		} else {
	    			$(".erro_valor").hide();
	    			$("#valor").css("border", "1px solid green");
	    			erro_valor = false;
	    		}
	    	})
	    	
	    	//BOTÃO SALVAR
	    	$("#salvar").on("click", function() {
	    		if (erro_nome || erro_descricao || erro_valor || erro_foto) {
	    			alert("Existem erros de preenchimento. Verifique os campos em vermelho.")
	    			if (erro_nome) {
	    				$(".erro_nome").show();
	    				$("#nome").css("border", "1px solid red");
	    			} if (erro_descricao) {
	    				$(".erro_descricao").show();
	    				$("#descricao").css("border", "1px solid red");
	    			} if (erro_valor) {
	    				$(".erro_valor").show();
	    				$("#valor").css("border", "1px solid red");
	    			} if (erro_foto) {
	    				$("#fotos").css("border", "1px solid red");
	    			}
	    			return false;
	    		} else {
	    			if ($("#cod").val() > 0) {
	    				$("#form").attr("action", "concluirItem")
		    			//alert("Produto cadastrado com sucesso!");
		    			$("#form").submit();
	    			}	    			
	    		}
	    	})
		</script>
	</body>
</html>