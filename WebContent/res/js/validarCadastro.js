$(document).ready(function() {

  $("input").blur(function() {
   if ($(this).val() == "") {
     $(this).addClass('error');
   } else {
     $(this).removeClass('error');
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
      e.preventDefault();
     if (!validarCadastro()) {
         alert("Erro ao cadastrar");
         return false;
     } else {
    	 $("#form").submit();
         alert("Cadastrar com Sucesso");
     }
 });
 
});


function validarCadastro() {
	
    var retorno = true;
    var formValues = {
      nome: { label: 'nome', value: $('#nome').val() },
      cpf: { label: 'cpf', value: $('#cpf').val() },
      endereco: { label: 'endereco', value: $('#endereco').val() },
      telefone: { label: 'telefone', value: $('#telefone').val() },
      sexo: { label: 'sexo', value: $('#sexo').val() },
      senha: { label: 'senha', value: $('#senha').val() },
      dataNascimento: { label: 'data de nascimento', value: $('#dataNascimento').val() }
      
    }
    
    
    var keys = Object.keys(formValues);
    
    for(var i = 0; i < keys.length; i++) {
    	console.log(i);
      if (!formValues[keys[i]].value) {
        messageValidation(formValues[keys[i]].label);
        retorno = false;
      }
    }
    
    return retorno;
}

function messageValidation(field) {
    alert('Campo ' + field + ' obrigatório.')
 }
 
 function hasValue(value) {
   return value;
 }