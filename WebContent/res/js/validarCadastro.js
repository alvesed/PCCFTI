$("#botao").click(function()  {
		var cont = 0;
		$("#form input[type=text]").each(function() {  // input[type=text] foi colocado isso pq na hora de validar ele pegava um input que estava hidden 
			if ($(this).val() == "") {
				cont++;
			}else{
				if(!validarCadastro($(this))){
					cont++;
				} 
			}
		});
	
		if (!validarCadastro($("#sexo"))) { // esta validação esta aqui pq é combobox!!!!
			cont++;
		}
	
		if (cont == 0) {
			$("#form").submit();
			alert("Cadastrado com Sucesso");
		}else{
			return false;
		}
		
function validarCadastro(elemento) {
	
	 var retorno = true;
	
		if ($(elemento).prop('name') == "nome") {
			if(!validaNome($(elemento).val())){
				alert("Digite um Nome valido");
				retorno = false;
			}
		}
		if ($(elemento).prop('senha') == "senha") {
			if(!validaSenha($(elemento).val())){
				alert("Digite uma Senha Valida");
				retorno = false;
			}
		}
		
		if ($(elemento).prop('name') == "cpf") {
			if(!testaCPF($(elemento).val())){
				alert("Digite um CPF valido");
				retorno = false;
			}
		}
		if($(elemento).prop('name') == "dataNascimento"){
			console.log($(elemento).val());
			if(!validarData($(elemento).val())){
				alert("Digite um data Valida.");
				retorno = false;
			}

		}
		
		if ($(elemento).prop('name') == "email") {
			if(($(elemento).val() == "")){
				alert("Digite um E-Mail valido");
				retorno = false;
			}
		}
		
		if($(elemento).prop('name') == "telefone"){
			if(!validaTelefone($(elemento).val())){
				alert("Digite um Telefone Valido");
				retorno = false;
			}
		}
		return retorno;
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

function validarData(data) {
	
	  var expReg = /^((0[1-9]|[12]\d)\/(0[1-9]|1[0-2])|30\/(0[13-9]|1[0-2])|31\/(0[13578]|1[02]))\/(19|20)?\d{2}$/;
	  var aRet = true;
	  console.log(data);
	  if ((data) && (data.match(expReg)) && (data != '')) {

		var dia = data.substring(0,2);
		var mes = data.substring(3,5);
		var ano = data.substring(6,10);
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

	function validaSenha(senha){
		
		if(senha.length <=0  || senha == ""){
			alert("Preencha o campo senha");
			return false;
		}
		
		return true;
		
	}

	function validaNome(nome){
		
		if(nome.length <=0  || nome == ""){
			alert("entrou no nome");
			return false;
		}
		
		return true;
	}

	function validaSexo(sexo){
		return !!sexo;
	}

});