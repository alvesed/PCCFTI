<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
	
		<link rel="stylesheet" type="text/css" href="<spring:url value='res/css/headerH.css' />">
		<style type="text/css"> @font-face { font-family:'ubuntu'; src: url("<spring:url value='res/font/ubuntu.ttf' />"); font-weight:normal; font-style:normal; } </style>

	</head>
	
	<body>
		<div id="divHeaderFull">
		
			<div id="divHeaderMenu">
			
			<c:choose>
				<c:when test="${empty usuarioLogado.email }">
				
					<a href="">
						<div class="divHeaderMenuOptionContainer">
							<div class="divHeaderMenuOption" class="shadow">
								<div class="front face">
									<img src="<spring:url value='res/img/ico/card_buy.png' />"/>
								</div>
								<div class="back face center">
									<img src="<spring:url value='res/img/ico/card_buy_white.png' />"/>
									<p>Comprar</p>
									<div class="divHeaderMenuOptionSubContainer">Ola</div>
								</div>
							</div>
						</div>
					</a>
					
					<a href="">
						<div class="divHeaderMenuOptionContainer">
							<div class="divHeaderMenuOption" class="shadow">
								<div class="front face">
									<img src="<spring:url value='res/img/ico/shop-facade.png' />"/>
								</div>
								<div class="back face center">
									<img src="<spring:url value='res/img/ico/shop-facade-white.png' />"/>
									<p>Vender</p>
								</div>
							</div>
						</div>
					</a>
					
					<a href="">
						<div class="divHeaderMenuOptionContainer">
							<div class="divHeaderMenuOption" class="shadow">
								<div class="front face">
									<img src="<spring:url value='res/img/ico/user-white.png' />"/>
								</div>
								<div class="back face center">
									<img src="<spring:url value='res/img/ico/user-black.png' />"/>
									<p>Perfil</p>
								</div>
							</div>
						</div>
					</a>
					
					<a href="">
						<div class="divHeaderMenuOptionContainer">
							<div class="divHeaderMenuOption" class="shadow">
								<div class="front face">
									<img src="<spring:url value='res/img/ico/cart.png' />"/>
								</div>
								<div class="back face center">
									<img src="<spring:url value='res/img/ico/cart-white.png' />"/>
									<p>Carrinho</p>
								</div>
							</div>
						</div>
					</a>
					
					<a href="">
						<div class="divHeaderMenuOptionContainer">
							<div class="divHeaderMenuOption" class="shadow">
								<div class="front face">
									<img src="<spring:url value='res/img/ico/investments-speech.png' />"/>
								</div>
								<div class="back face center">
									<img src="<spring:url value='res/img/ico/investments-speech-white.png' />"/>
									<p>Ofertas</p>
								</div>
							</div>
						</div>
					</a>
				</c:when>
				
				<c:otherwise>
				
					<a href="">
						<div class="divHeaderMenuOptionContainer">
							<div class="divHeaderMenuOption" class="shadow">
								<div class="front face">
									<img src="<spring:url value='res/img/ico/card_buy.png' />"/>
								</div>
								<div class="back face center">
									<img src="<spring:url value='res/img/ico/card_buy_white.png' />"/>
									<p>Comprar</p>
								</div>
							</div>
						</div>
					</a>
					
					<a href="">
						<div class="divHeaderMenuOptionContainer">
							<div class="divHeaderMenuOption" class="shadow">
								<div class="front face">
									<img src="<spring:url value='res/img/ico/pack/005-login-4.png' />"/>
								</div>
								<div class="back face center">
									<img src="<spring:url value='res/img/ico/pack/005-login-4-white.png' />"/>
									<p>Registrar</p>
								</div>
							</div>
						</div>
					</a>
					
					<a href="">
						<div class="divHeaderMenuOptionContainer">
							<div class="divHeaderMenuOption" class="shadow">
								<div class="front face">
									<img src="<spring:url value='res/img/ico/pack/016-login.png' />"/>
								</div>
								<div class="back face center">
									<img src="<spring:url value='res/img/ico/pack/016-login-white.png' />"/>
									<p>Entrar</p>
								</div>
							</div>
						</div>
					</a>
				
				</c:otherwise>
			</c:choose>
				
				
			</div>
			
			
		
		</div>
	</body>
</html>