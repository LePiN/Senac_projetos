$(document).ready(function(){
		
	var mensagemErro ="Entrada errada!\n";
	var numeroControle = 1;
		
	var verificarNome = function(){
			
			if($("#txNome").val()){
				$("#txNome").css({"color":"green"});	
				return false;
			}else{
				alert("Nome obrigatório, Preencha o campo.\n");
				return true;
			}	
	}
	
	var verificarCPFtamanho = function(){
		
		if(($("#txCPF").val().length)!=14){
			
			mensagemErro += "- CPF deve possuir 14 digítos.\n";
			return true;
		}else{
			return false;
		}
	}
		
	var verificarCPFpontos = function(){
		
		var partesPontos = "";
		
		partesPontos = $("#txCPF").val().split(".");
		
		if(partesPontos.length != 3){
			mensagemErro += "- CPF deve possuir dois pontos.\n";
			
			return true;
		}
	}
	
	var verificarCPFtraco = function(){
		
		var partesTraco="";
		
		partesTraco = $("#txCPF").val().split("-");
		
		if(partesTraco.length != 2){
			mensagemErro += "- CPF deve possuir um traço.\n";
			
			return true;
		}
	}
	
	var verificarCPFnumeros = function(){
		
		var numerosCPF = $("#txCPF").val().replace(/[^\d]+/g,'');
		
		if($.isNumeric(numerosCPF)){
			return false;
		}else{
			mensagemErro += "- CPF não deve conter letras ou caracteres estranhos.\n";
			return true;
		}
	}	
	
	var retornoServlet = function(data, status){
				
		if(numeroControle == 1){
			$("#retorno").html("<p>" + data + "</p>");
			numeroControle = numeroControle + 1;
			
		}else{
			$("#retorno").empty();
			numeroControle = 1;
			$("#retorno").html("<p>" + data + "</p>");			
		}		
	};
		
	$("#busca").click(function(){
		verificarNome();	
		var val1 = verificarCPFtamanho();
		var val2 = verificarCPFpontos();
		var val3 = verificarCPFtraco();
		var val4 = verificarCPFnumeros();
				
		if((val1==true)||(val2==true)||(val3==true)||(val4==true)){
			alert(mensagemErro);
			mensagemErro ="Entrada errada!\n";
			
		}else{
			
			var numero = $("#txCPF").val().replace(/[^\d]+/g,'');
			$.post("/ProvaDW-LeandroPN/ConsultarPessoa", {cpf : numero}, retornoServlet);
		}
			
	});
	
});