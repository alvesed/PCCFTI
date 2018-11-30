<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>

		<title> WhiteMarket Shop. 2018 </title>

		<link rel="stylesheet" type="text/css" href="<spring:url value='res/css/headerStyle.css' />">
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>

	</head>

	<body>
		
		<div id="divHeaderFull">

			<div id="divHeaderTop">
				
				<a href="telaPrincipal"> <img id="imgHeaderTopLogo" src="<spring:url value='res/img/png/logo.png' />" alt="white market" /> </a>
				
				<c:choose>
				
					<c:when test="${ not empty usuarioLogado.email }">
					
						<a href="#"> <img id="imgHeaderTopLogin" src="<spring:url value='res/img/png/logout.png' />" alt="white market" /> </a>
					
					</c:when>
					
					<c:otherwise>
					
						<a href="login"> <img id="imgHeaderTopLogin" src="<spring:url value='res/img/png/login.png' />" alt="white market" /> </a>
					
					</c:otherwise>
					
				</c:choose>
				


				<a href="verCarrinho"> <div id="imgHeaderTopCart" style="background-image: url('<spring:url value="res/img/png/cartVerified.png" />');" > </div> </a>

				
				
			</div>

			<div id="divHeaderMenu">

				<div class="divHeaderMenuOption">

					<a href="#">
						<p class="pHeaderMenuOption"> OptionMenu 1 </p>
					</a>

					<div class="divHeaderSubMenuOption">

						<a href="#">
							<p class="pHeaderSubMenuOption"> SubOptionMenu 1 </p>
						</a>

						<div class="divHeaderInnerSubMenuOption">

							<a href="#">
								<p class="pHeaderInnerSubMenuOption"> Inner SubOption 1 </p>
							</a>

						</div>

						<div class="divHeaderInnerSubMenuOption">

							<a href="#">
								<p class="pHeaderInnerSubMenuOption"> Inner SubOption 2 </p>
							</a>

						</div>

						<div class="divHeaderInnerSubMenuOption">

							<a href="#">
								<p class="pHeaderInnerSubMenuOption"> Inner SubOption 3 </p>
							</a>

						</div>

						<div class="divHeaderInnerSubMenuOption">

							<a href="#">
								<p class="pHeaderInnerSubMenuOption"> Inner SubOption 4 </p>
							</a>

						</div>

						<div class="divHeaderInnerSubMenuOption">

							<a href="#">
								<p class="pHeaderInnerSubMenuOption"> Inner SubOption 5 </p>
							</a>

						</div>

					</div>

					<div class="divHeaderSubMenuOption">

						<a href="#">
							<p class="pHeaderSubMenuOption"> SubOptionMenu 2 </p>
						</a>

						<div class="divHeaderInnerSubMenuOption">

							<a href="#">
								<p class="pHeaderInnerSubMenuOption"> Inner SubOption 1 </p>
							</a>

						</div>

						<div class="divHeaderInnerSubMenuOption">

							<a href="#">
								<p class="pHeaderInnerSubMenuOption"> Inner SubOption 2 </p>
							</a>

						</div>

					</div>

					<div class="divHeaderSubMenuOption">

						<a href="#">
							<p class="pHeaderSubMenuOption"> SubOptionMenu 3 </p>
						</a>

					</div>

				</div>

				<div class="divHeaderMenuOption">

					<a href="#">
						<p class="pHeaderMenuOption"> OptionMenu 2 </p>
					</a>

					<div class="divHeaderSubMenuOption">

						<a href="#">
							<p class="pHeaderSubMenuOption"> SubOptionMenu 1 </p>
						</a>

						<div class="divHeaderInnerSubMenuOption">

							<a href="#">
								<p class="pHeaderInnerSubMenuOption"> Inner SubOption 1 </p>
							</a>

						</div>

						<div class="divHeaderInnerSubMenuOption">

							<a href="#">
								<p class="pHeaderInnerSubMenuOption"> Inner SubOption 2 </p>
							</a>

						</div>

					</div>

				</div>

				<div class="divHeaderMenuOption">

					<a href="#">
						<p class="pHeaderMenuOption"> OptionMenu 3 </p>
					</a>

					<div class="divHeaderSubMenuOption">

						<a href="#">
							<p class="pHeaderSubMenuOption"> SubOptionMenu 1 </p>
						</a>

					</div>

					<div class="divHeaderSubMenuOption">

						<a href="#">
							<p class="pHeaderSubMenuOption"> SubOptionMenu 2 </p>
						</a>

					</div>

				</div>

				<div class="divHeaderMenuOption">

					<a href="#">
						<p class="pHeaderMenuOption"> OptionMenu 4 </p>
					</a>

					<div class="divHeaderSubMenuOption">

						<a href="#">
							<p class="pHeaderSubMenuOption"> SubOptionMenu 1 </p>
						</a>

						<div class="divHeaderInnerSubMenuOption"> 

							<a href="#">
								<p class="pHeaderInnerSubMenuOption"> Inner SubOption 1 </p>
							</a>

						</div>

					</div>

				</div>
				
				<div class="divHeaderMenuOption">

					<a href="#">
						<p class="pHeaderMenuOption"> OptionMenu 5 </p>
					</a>
				
				</div>

				<c:choose>
				
					<c:when test="${ not empty usuarioLogado.email }">
					
						<div class="divHeaderMenuOption">
		
							<a href="#">
								<p class="pHeaderMenuOption"> Minha Conta </p>
							</a>
		
							<div class="divHeaderSubMenuOption">
		
								<a href="verPedidos">
									<p class="pHeaderMenuOption">Meus Pedidos</p>
								</a>
		
							</div>
		
							<div class="divHeaderSubMenuOption">
		
								<a href="verProdutos">
									<p class="pHeaderMenuOption">Meus Produtos</p>
								</a>
		
							</div>
							
							<div class="divHeaderSubMenuOption">
		
								<a href="cadastrarItem">
									<p class="pHeaderMenuOption">Vender Produto</p>
								</a>
		
							</div>
							
							<div class="divHeaderSubMenuOption">
		
								<a href="logout">
									<p class="pHeaderMenuOption">Sair</p>
								</a>
		
							</div>
		
						</div>
					
					</c:when>
					
					<c:otherwise>
					
						<div class="divHeaderMenuOption">
		
							<a href="#">
								<p class="pHeaderMenuOption"> Minha Conta </p>
							</a>
							
							<div class="divHeaderSubMenuOption">
		
								<a href="login">
									<p class="pHeaderMenuOption">Logar</p>
								</a>
		
							</div>
							
						</div>
					
					</c:otherwise>
					
				</c:choose>
				
			</div>

		</div>

		<script type="text/javascript">

			$(document).ready(function() {
				$.ajax{
					
				}
			});

		</script>

	</body>

</html>