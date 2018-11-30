<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"	%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"	%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-maskmoney/3.0.2/jquery.maskMoney.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<jsp:include page="header.jsp" ></jsp:include>
	<div id="divContainer">
	
		
		<form action="autenticacao">
		
		  <div class="form-group">
		    <label for="InputEmail1">Email:</label>
		    <input type="email" class="form-control" id="InputEmail1" aria-describedby="emailHelp" placeholder="Email" name="email">
		  </div>
		  
		  <div class="form-group">
		    <label for="InputPassword1">Senha:</label>
		    <input type="password" class="form-control" id="InputPassword1" placeholder="******" name="senha">
		     <c:if test="${not empty erroLogin}">
  		   	<span id="aviso" class="form-text text-muted">${erroLogin}</span></br>
  		   </c:if>
		  </div>
  		  
  		   
		  <button type="submit" class="btn btn-primary">Login</button>
		</form>
  </div>


</body>
</html>