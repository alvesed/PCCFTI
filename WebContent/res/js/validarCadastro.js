$(document).ready(function() {
	
	
  $("input").blur(function() {
   if ($(this).val() == "") {
     $(this).addClass('error');
   } else {
     $(this).removeClass('error');
   }
   
   
   if(regex = /[a-z]*\s[a-z]*/gi.exec($("#nome").val())){
	   $("#nome").removeClass('error');
	   reason = false;
	}else{
		$("#nome").addClass('error');
	   reason = true;
	}
  });

  $("select").val("").blur(function() {
    if ($(this).val() == "selecione") {
      $(this).addClass('error');
    } else {
      $(this).removeClass('error');
    }
  });
  
   $("#botao").click(function(e) {
	var cpf = $("#cpf").val();
	var dataNascimento = $("#dataNascimento").val();
	var telefone = $("#telefone").val();
	var email = $("#email").val();
	
	
      e.preventDefault();
	  
     if (!validarCadastro(cpf,dataNascimento,telefone,email)) { 
       console.log("Erro ao cadastrar");
         return false;
     } else {
    	 $("#form").submit();
         alert("Cadastrado com Sucesso");
     }
 });
   
   

});
function validarCadastro(cpf,dataNascimento,telefone,email) {
    var retorno = true;
    
    if(regex = /[a-z]*\s[a-z]*/gi.exec($("#nome").val())){
    	alert("Digite um Nome completo");
 	  retorno = false;
 	}else{
 		retorno = true;
 	}

	if(!testaCPF(cpf)){
		alert("Digite um CPF valido Por favor");
		retorno = false;
	}else{
		retorno = true;		
	}
	
	if(!validarData(dataNascimento)){
		alert("Digite uma Data valida Por favor");
		retorno = false;
	}else{
		retorno = true;		
	}
	
	if(!validaTelefone(telefone)){
		alert("Digite uma Telefone valido Por favor");
		retorno = false;
	}else{
		retorno = true;		
	}
	
	if(!validaEmail(email)){
		alert("Digite uma E-mail valido Por favor");
		retorno = false;
	}else{
		retorno = true;		
	}
	
	
	


    var formValues = {
		
      nome: { label: 'nome', value: $('#nome').val() },
      cpf: { label: 'cpf', value: $('#cpf').val() },
	  dataNascimento: { label: 'Data De Nascimento', value: $('#dataNascimento').val() },
	  telefone: { label: 'telefone', value: $('#telefone').val() },
	  sexo: { label: 'sexo', value: $('#sexo').val() },
	  email: { label: 'email', value: $('#email').val() },
	  senha: { label: 'senha', value: $('#senha').val() },
	  endereco: { label: 'endereco' , value: $('#endereco').val() }
 
    }
    
    var keys = Object.keys(formValues);
    
    for(var i = 0; i < keys.length; i++) {
    	console.log(i);
      if (!formValues[keys[i]].value) { 
        messageValidation(formValues[keys[i]].label);
        retorno = false;
      }
    }
	console.log(retorno);
    return retorno;
}

function messageValidation(field) {
    alert('Campo ' + field + ' obrigatório.')
 }

 function testaCPF(strCPF) {
 
	var Soma;
	var Resto;
	Soma = 0;
	if (strCPF == "00000000000")
		
		return false;

	for (i = 1; i <= 9; i++)
		Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (11 - i);
	Resto = (Soma * 10) % 11;

	if ((Resto == 10) || (Resto == 11))
		Resto = 0;
	if (Resto != parseInt(strCPF.substring(9, 10)))
		return false;
	
	Soma = 0;
	for (i = 1; i <= 10; i++)
		Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (12 - i);
	Resto = (Soma * 10) % 11;

	if ((Resto == 10) || (Resto == 11))
		Resto = 0;
	if (Resto != parseInt(strCPF.substring(10, 11)))
		return false;
	return true;
}

function validarData(dataNascimento) {
	
	  var expReg = /^((0[1-9]|[12]\d)\/(0[1-9]|1[0-2])|30\/(0[13-9]|1[0-2])|31\/(0[13578]|1[02]))\/(19|20)?\d{2}$/;
	  var aRet = true;
	  console.log(dataNascimento);
	  if ((dataNascimento) && (dataNascimento.match(expReg)) && (dataNascimento != '')) {

		var dia = dataNascimento.substring(0,2);
		var mes = dataNascimento.substring(3,5);
		var ano = dataNascimento.substring(6,10);
   	  console.log(dia +  '-' + mes + '-' + ano);
		if (mes == 4 || mes == 6 || mes == 9 || mes == 11 && dia > 30) 
		  aRet = false;
		else 
		  if ((ano % 4) != 0 && mes == 2 && dia > 28) 
			aRet = false;
		  else
			if ((ano%4) == 0 && mes == 2 && dia > 29)
			  aRet = false;
	  }  else 
		aRet = false;  
	  return aRet;
	}
	
function validaTelefone(telefone){
	
    telefone = telefone.replace(/\D/g,'');
    
    if(!(telefone.length >= 10 && telefone.length <= 11)) return false;
    
    if (telefone.length == 11 && parseInt(telefone.substring(2, 3)) != 9) return false;
   
    for(var n = 0; n < 10; n++){
   
    	if(telefone == new Array(11).join(n) || telefone == new Array(12).join(n)) return false;
    }
    return true;
}

function validaEmail(email){
	
	if(email.length <= 5 && email.length >= 30){
		return false;
	}else if(!email.includes("@", 1)){
		alert("Seu e-mail deve conter @");
		return false;
	}else{
		return true;
	}
	
}	
