<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" type="text/css" href="res/css/teste.css">
<link rel="stylesheet" type="text/css" href="res/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="res/css/font-awesome.css">



<script type="text/javascript" src="res/js/jquery.js"></script>
<script type="text/javascript" src="res/js/bootstrap.js"></script>

<style>
img {
	max-width: 150px;
	max-height: 200px;
}
</style>

<title>Menu Principal</title>
</head>
<body>
<jsp:include page = "header.jsp" />

	<div class="container">
	

		<div class="row">
			<div class="row">
				<div class="col-md-9">
					<h1>NOVOS PRODUTOS</h1>
				</div>
				<div class="col-md-3">
					<!-- Controls -->
					<div class="controls pull-right hidden-xs">
						<a class="left fa fa-chevron-left btn btn-primary"
							href="#carousel-example-generic" data-slide="prev"></a><a
							class="right fa fa-chevron-right btn btn-primary"
							href="#carousel-example-generic" data-slide="next"></a>
					</div>
				</div>
			</div>
			
				<!-- carrosel de produtos -->
			<div id="carousel-example-generic" class="carousel slide hidden-xs" data-ride="carousel">
				<!-- Wrapper for slides -->
				<div class="carousel-inner">
				
				
						<div class="item active">
							<div class="row">
								<c:forEach items="${produto}" var="produto" begin="0" end="2" > 
						   
							
								<div class="col-sm-4">
								
								
								
								<div class="col-item">
								
									<div class="photo">
										<img src="res/img/png/dog5.png" class="img-responsive" alt="a" />
									</div>
									
									<div class="info">
										<div class="row">
											<div class="price col-md-6">
												<h5>${produto.nome}</h5>
												<h5>${produto.descricao}</h5>
												<h5>${produto.condicao}</h5>
												
												<h5 class="price-text-color" >$ ${produto.valor}</h5>
											</div>
											<div class="rating hidden-sm col-md-6">	</div>
										</div>
										
										
										<div class="separator clear-left">
											<p class="btn-add">
												<i class="fa fa-shopping-cart"></i><a
													href="http://www.jquery2dotnet.com" class="hidden-sm">Add
													to cart</a>
											</p>
											
											
											<p class="btn-details">
												<i class="fa fa-list"></i><a
													href="verProduto?codigoProduto=${produto.codProduto}" class="hidden-sm">More
													details</a>
											</p>
											
											
										</div>
										<div class="clearfix"></div>
									</div>
								</div>
							</div>
							
							
						</c:forEach>
						</div>
					</div>
						<div class="item ">
							<div class="row">
								<c:forEach items="${produto}" var="produto" begin="3" end="5" varStatus="img" > 
						   
							
								<div class="col-sm-4">
								
								
								
								<div class="col-item">
								
									<div class="photo">
										<img src="res/img/png/dog5.png" class="img-responsive" alt="a" />
									</div>
									
									<div class="info">
										<div class="row">
											<div class="price col-md-6">
												<h5>${produto.nome}</h5>
												<h5>${produto.descricao}</h5>
												<h5>${produto.condicao}</h5>
												
												<h5 class="price-text-color" >$ ${produto.valor}</h5>
											</div>
											<div class="rating hidden-sm col-md-6">	</div>
										</div>
										
										
										<div class="separator clear-left">
											<p class="btn-add">
												<i class="fa fa-shopping-cart"></i><a
													href="http://www.jquery2dotnet.com" class="hidden-sm">Add
													to cart</a>
											</p>
											<p class="btn-details">
												<i class="fa fa-list"></i><a
													href="verProduto?codigoProduto=${produto.codProduto}" class="hidden-sm">More
													details</a>
											</p>
										</div>
										<div class="clearfix"></div>
									</div>
								</div>
							</div>
							
							
						</c:forEach>
						</div>
					</div>
					
					
				</div>
			</div>
		</div>
		
			<!-- Tabela de produtos -->

	<div class="row">
					<div class="row">
				<div class="col-md-9">
					<h1>PRODUTOS MAIS VENDIDOS</h1>
				</div>
			
			</div>
	 
	
								<c:forEach items="${produto}" var="produto" begin="0" end="5" varStatus="img" > 
						   
							
								<div class="col-lg-4 col-md-6 mb-4">
								
								
								
								<div class="col-item">
								
									<div class="photo">
										<img src="res/img/png/dog5.png" class="img-responsive" alt="a" />
									</div>
									
									<div class="info">
										<div class="row">
											<div class="price col-md-6">
												<h5>${produto.nome}</h5>
												<h5>${produto.descricao}</h5>
												<h5>${produto.condicao}</h5>
												
												<h5 class="price-text-color" >$ ${produto.valor}</h5>
											</div>
											<div class="rating hidden-sm col-md-6">	</div>
										</div>
										
										
										<div class="separator clear-left">
											<p class="btn-add">
												<i class="fa fa-shopping-cart"></i><a
													href="http://www.jquery2dotnet.com" class="hidden-sm">Add
													to cart</a>
											</p>
											<p class="btn-details">
												<i class="fa fa-list"></i><a
													href="verProduto?codigoProduto=${produto.codProduto}" class="hidden-sm">More
													details</a>
											</p>
										</div>
										<div class="clearfix"></div>
									</div>
								</div>
							</div>
							
							
						</c:forEach>
						</div>
		
		
	</div>
	
	

					
	

</body>
</html>