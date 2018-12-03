<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
	
		<style type="text/css"> @font-face { font-family:'ubuntu'; src: url("<spring:url value='res/font/ubuntu.ttf' />"); font-weight:normal; font-style:normal; } </style>
		<style type="text/css">
		
			body {
				padding: 0px;
				margin: 0px;
			}
			
			body * {
				font-family: 'ubuntu', sans-serif;
			}
			
			#divHeaderFull {
				position: fixed;
				width: 100px;
				height: 100%;
				background-color: black;
				
				transition: 300ms linear;
			}
			
			#divHeaderMenu {
				height: 50%;
				width: 100%;
				background-color: silver;
				position: relative;
				float: top;
				top: 50%;
			}
			
			#divHeaderMenu a {
				text-decoration: none;
			}
			
			.divHeaderMenuOptionContainer {
				position: relative;
				float: top;
				height: 25%;
				width: 100%;
				background-color: gray;
				
				transition: 300ms linear;
				perspective: 1000;
			}
			
			.divHeaderMenuOption {
				width: 100%;
				height: 100%;
				
				transition: 300ms linear;
			}
			
			.divHeaderMenuOption:hover  {
				width: 100%;
				transform: rotateY(180deg);
			}
			
			.divHeaderMenuOption {
				
				transform-style: preserve-3d;
				transition: all .9s linear;
			}
			
			.divHeaderMenuOptionContainer:hover .divHeaderMenuOption {
				transform: rotateY(180deg);
			}
			
			.face {
				position: relative;
				width: 100%;
				height: 100%;
				backface-visibility: hidden;
			}
			
			.face.back {
				position: relative;
				top: -100%;
				width: 400%;
				left: -300%;
				display: block;
				transform: rotateY(180deg);
				background-color: #aaa;
			}
			
			.back p {
				margin-top: 0px;
				text-decoration: none;
				color: rgba(245, 245, 245, 1);
				line-height: 200%;
				font-size: 300%;
				text-align: right;
				padding-right: 20px;
			}
			
		</style>
	</head>
	<body>
		<div id="divHeaderFull">
		
			<div></div>
		
			<div id="divHeaderMenu">
			
				<a href="">
					<div class="divHeaderMenuOptionContainer">
						<div class="divHeaderMenuOption" class="shadow">
							<div class="front face">
								<img src=""/>
							</div>
							<div class="back face center">
								<p>Comprar</p>
							</div>
						</div>
					</div>
				</a>
				
				<a href="">
					<div class="divHeaderMenuOptionContainer">
						<div class="divHeaderMenuOption" class="shadow">
							<div class="front face">
								<img src=""/>
							</div>
							<div class="back face center">
								<p>Vender</p>
							</div>
						</div>
					</div>
				</a>
				
				<a href="">
					<div class="divHeaderMenuOptionContainer">
						<div class="divHeaderMenuOption" class="shadow">
							<div class="front face">
								<img src=""/>
							</div>
							<div class="back face center">
								<p>Perfil</p>
							</div>
						</div>
					</div>
				</a>
				
			</div>
		
		</div>
	</body>
</html>