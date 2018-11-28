<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>

		<title> WhiteMarket Shop. 2018 </title>

		<link rel="stylesheet" type="text/css" href="<spring:url value='res/css/headerStyle.css' />">

	</head>

	<body>
		
		<div id="divHeaderFull">

			<div id="divHeaderTop">
				
				<a href="#"> <img id="imgHeaderTopLogo" src="<spring:url value='res/img/png/headerStyle.css' />" alt="white market" /> </a>

				<a href="#"> <img id="imgHeaderTopLogin" src="<spring:url value='res/img/png/enter.png' />" alt="white market" /> </a>

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

				</div>

		

			</div>

		</div>

		<script type="text/javascript">

			/*for (var i = Things.length - 1; i >= 0; i--) {
				Things[i]
			}*/

		</script>

	</body>

</html>