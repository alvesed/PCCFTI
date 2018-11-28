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
  
  
 function messageValidation(field) {
    alert('Campo ' + field + ' obrigatório.')
 }
 
 function hasValue(value) {
   return value;
 }

 $("#botao").click(function(e) {
     e.preventDefault();

     if (!validarCadastro()) {
         alert("Não foi possivel Cadastrar");
     } else {
         alert("Cadastro Realizado Com Sucesso");
     }

     function validarCadastro() {
         var retorno = true;
         var formValues = {
           nome: { label: 'nome', value: $('#nome').val() },
           cpf: { label: 'cpf', value: $('#cpf').val() },
           endereco: { label: 'endereço', value: $('#endereco').val() },
           telefone: { label: 'telefone', value: $('#telefone').val() },
           sexo: { label: 'sexo', value: $('#sexo').val() },
           senha: { label: 'senha', value: $('#senha').val() },
           dataNascimento: { label: 'data de nascimento', value: $('#dataNascimento').val() },
         }
         

         var keys = Object.keys(formValues);
         
         for(var i = 0; keys.length; i++) {
           if (!formValues[keys[i]].value) {
             messageValidation(formValues[keys[i]].label)
           }
         }
     }

     
     //
     //    function testaCPF(strCPF) {
     //     var Soma;
     //     var Resto;
     //     Soma = 0;
     //     if (strCPF == "00000000000")

     //      return false;

     //     for (i = 1; i <= 9; i++)
     //      Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (11 - i);
     //     Resto = (Soma * 10) % 11;

     //     if ((Resto == 10) || (Resto == 11))
     //      Resto = 0;
     //     if (Resto != parseInt(strCPF.substring(9, 10)))
     //      return false;

     //     Soma = 0;
     //     for (i = 1; i <= 10; i++)
     //      Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (12 - i);
     //     Resto = (Soma * 10) % 11;

     //     if ((Resto == 10) || (Resto == 11))
     //      Resto = 0;
     //     if (Resto != parseInt(strCPF.substring(10, 11)))
     //      return false;
     //     return true;
     //    }

     //     function validarData(data) {

     //      var expReg = /^((0[1-9]|[12]\d)\/(0[1-9]|1[0-2])|30\/(0[13-9]|1[0-2])|31\/(0[13578]|1[02]))\/(19|20)?\d{2}$/;
     //      var aRet = true;
     //      console.log(data);
     //      if ((data) && (data.match(expReg)) && (data != '')) {

     //       var dia = data.substring(0, 2);
     //       var mes = data.substring(3, 5);
     //       var ano = data.substring(6, 10);
     //       console.log(dia + '-' + mes + '-' + ano);
     //       if (mes == 4 || mes == 6 || mes == 9 || mes == 11 && dia > 30)
     //        aRet = false;
     //       else
     //       if ((ano % 4) != 0 && mes == 2 && dia > 28)
     //        aRet = false;
     //       else
     //       if ((ano % 4) == 0 && mes == 2 && dia > 29)
     //        aRet = false;
     //      } else
     //       aRet = false;
     //      return aRet;
     //     }


     //     function validaTelefone(telefone) {

     //      telefone = telefone.replace(/\D/g, '');

     //      if (!(telefone.length >= 10 && telefone.length <= 11)) return false;

     //      if (telefone.length == 11 && parseInt(telefone.substring(2, 3)) != 9) return false;

     //      for (var n = 0; n < 10; n++) {

     //       if (telefone == new Array(11).join(n) || telefone == new Array(12).join(n)) return false;
     //      }
     //      return true;
     //     }
 });
});