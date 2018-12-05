<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
	
		<link rel="stylesheet" type="text/css" href="<spring:url value='res/css/headerStyle.css' />">
		<style type="text/css"> @font-face { font-family:'ubuntu'; src: url("<spring:url value='res/font/ubuntu.ttf' />"); font-weight:normal; font-style:normal; } </style>

	</head>
	
	<body>
		<div id="divHeaderFull">
		
			<div id="divHeaderMenu">
			
			<c:choose>
				<c:when test="${not empty usuarioLogado.email }">
				
					<div class="divHeaderMenuOptionContainer">
					
					
						<!-- Op��es do menu -->
						<div class="divHeaderMenuOption">
						
							<div class="front face">
								<img src="<spring:url value='res/img/ico/card_buy.png' />"/>
							</div>
							
							<!-- Imagem e tag de identifica��o -->
							<div class="back face center">
								<img src="<spring:url value='res/img/ico/card_buy_white.png' />"/>
								<p class="optionTag">Comprar</p>
								
								
								
								<div class="divHeaderMenuOptionSubContainer">
								
									<!-- Op��es do submenu -->
									<a href="">
										<div class="divHeaderMenuOptionSubOption">
										
											<!-- Tag de identifica��o -->
											<p class="subOptionTag" >Categoria 1</p>
										</div>
									</a>
									
									<a href="">
										<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">Categoria 2</p>
										</div>
									</a>
									
									<a href="">
										<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">Categoria 3</p>
										</div>
									</a>
									
									<a href="">
										<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">Categoria 4</p>
										</div>
									</a>
									
									<a href="">
										<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">Categoria 5</p>
										</div>
									</a>
									
									<a href="">
										<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">Categoria 6</p>
										</div>
									</a>
									
								</div>
								
								
								
							</div>
						</div>
					</div>
				
				
				
					<div class="divHeaderMenuOptionContainer">
						<div class="divHeaderMenuOption">
							<div class="front face">
								<img src="<spring:url value='res/img/ico/shop-facade.png' />"/>
							</div>
							<div class="back face center">
								<img src="<spring:url value='res/img/ico/shop-facade-white.png' />"/>
								<p class="optionTag">Vender</p>
								
								
								
								<div class="divHeaderMenuOptionSubContainer">
								
									<a href="cadastrarItem">
										<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">Cadastrar novo</p>
										</div>
									</a>
									
									<a href="verProdutos">
										<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">Ver produtos</p>
										</div>
									</a>
									
								</div>
								
								
								
							</div>
						</div>
					</div>
					
					
				
					<div class="divHeaderMenuOptionContainer">
						<div class="divHeaderMenuOption">
							<div class="front face">
								<img src="<spring:url value='res/img/ico/cart.png' />"/>
							</div>
							<div class="back face center">
								<img src="<spring:url value='res/img/ico/cart-white.png' />"/>
								<p class="optionTag">Carrinho</p>
								
								
								<div class="divHeaderMenuOptionSubContainer">
								
									<a href="verCarrinho">
										<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">Abrir carrinho</p>
										</div>
									</a>
									
									<a href="verPedidos">
										<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">Ver compras</p>
										</div>
									</a>
									
								</div>
								
								
								
							</div>
						</div>
					</div>



					<div class="divHeaderMenuOptionContainer">
						<div class="divHeaderMenuOption">
							<div class="front face">
								<img src="<spring:url value='res/img/ico/investments-speech.png' />"/>
							</div>
							<div class="back face center">
								<img src="<spring:url value='res/img/ico/investments-speech-white.png' />"/>
								<p class="optionTag">Ofertas</p>
								
								
								
								<div class="divHeaderMenuOptionSubContainer">
								
									<a href="">
										<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">Ver promo��es</p>
										</div>
									</a>
									
									<a href="">
										<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">Resgatar coupon</p>
										</div>
									</a>
									
								</div>
								
								
								
							</div>
						</div>
					</div>
					
					
					<div class="divHeaderMenuOptionContainer">
						<div class="divHeaderMenuOption">
							<div class="front face">
								<img src="<spring:url value='res/img/ico/user-white.png' />"/>
							</div>
							<div class="back face center">
								<img src="<spring:url value='res/img/ico/user-black.png' />"/>
								<p class="optionTag">Perfil</p>
								
								
								<div class="divHeaderMenuOptionSubContainer">
								
									<a href="">
										<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">Configura��es</p>
										</div>
									</a>
									
									<a href="logout">
										<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">Desconectar</p>
										</div>
									</a>
									
								</div>
								
								
							</div>
						</div>
					</div>

				</c:when>
				
				<c:otherwise>
				
					<div class="divHeaderMenuOptionContainer">
					
						<div class="divHeaderMenuOption">
							
							<div class="front face">
								<img src="<spring:url value='res/img/ico/card_buy.png' />"/>
							</div>
							
							<!-- Imagem e tag de identifica��o -->
							<div class="back face center">
								<img src="<spring:url value='res/img/ico/card_buy_white.png' />"/>
								<p class="optionTag">Comprar</p>
								
								<div class="divHeaderMenuOptionSubContainer">
								
									<!-- Op��es do submenu -->
									<a href="">
										<div class="divHeaderMenuOptionSubOption">
										
										<!--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
											<!-- Tag de identifica��o -->
											<p class="subOptionTag" id="automotivo" >Automotivo</p>
										</div>
									</a>
									
									<a href="">
										<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">Vestuario</p>
										</div>
									</a>
									
									<a href="">
										<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">Brinquedos</p>
										</div>
									</a>
									
									<a href="">
										<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">Celulares</p>
										</div>
									</a>

									<a href="">
										<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">Informatica</p>
										</div>
									</a>
									
									<a href="">
										<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">Eletrodom�sticos</p>
										</div>
									</a>
									
									<a href="">
									<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">Eletr�nicos</p>
										</div>
									</a>
									
									
									<a href="">
									<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">M�veis</p>
										</div>
									</a>
									
									<a href="">
									<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">Moda e decora��o</p>
										</div>
									</a>
									
									<a href="">
									<div class="divHeaderMenuOptionSubOption">
											<p class="subOptionTag">Games e Livros</p>
										</div>
									</a>
									
									
									
								</div>
									
									
									
							</div>
						</div>
					</div>
					
					
					
					<a href="cadastrarCliente">
						<div class="divHeaderMenuOptionContainer">
							<div class="divHeaderMenuOption">
								<div class="front face">
									<img src="<spring:url value='res/img/ico/pack/005-login-4.png' />"/>
								</div>
								<div class="back face center">
									<img src="<spring:url value='res/img/ico/pack/005-login-4-white.png' />"/>
									<p class="optionTag">Registrar</p>
								</div>
							</div>
						</div>
					</a>
					
					<a href="login">
						<div class="divHeaderMenuOptionContainer">
							<div class="divHeaderMenuOption">
								<div class="front face">
									<img src="<spring:url value='res/img/ico/pack/016-login.png' />"/>
								</div>
								<div class="back face center">
									<img src="<spring:url value='res/img/ico/pack/016-login-white.png' />"/>
									<p class="optionTag">Entrar</p>
								</div>
							</div>
						</div>
					</a>
				
				</c:otherwise>
			</c:choose>
				
			</div>
		
		</div>
		
		<input type="text" id="inputFind" placeholder="Encontrar algo..."><img alt="" src="<spring:url value='res/img/ico/magnifying-glass-finder.png' />" id="inputFindIcon">
		
		<a href="telaPrincipal"><img id="imgLogo" src="<spring:url value='res/img/png/logo-novo.png' />"/></a>
		
		<script type="text/javascript">
		
			$(document).ready(function() {

				$("#searchbutton").click(function() {
					var find = {
							busca: $(this).parent().find("#searchtext").val()
						}
					
					$.ajax({
						url: "telaPrincipal/busca",
						type: "POST",
						data: find,
						contentType: "application/x-www-form-urlencoded; charset = iso-8859-1;",
						success: function(data){
							
							window.location.href="telaPrincipal";
						}
					});
					
				});
				
				
				$("#automotivo").click(function() {
					var find = {
							buscaCategoria: 1
						}
					
					$.ajax({
						url: "telaPrincipal/buscaCategoria",
						type: "POST",
						data: find,
						contentType: "application/x-www-form-urlencoded; charset = iso-8859-1;",
						success: function(data){
							
							window.location.href="telaPrincipal";
						}
					});
				});
				
				
				
				
				
				
			});
		
		</script>
		
	</body>
</html>