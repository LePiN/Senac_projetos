$(document).ready(function(){
	
	var mensagemErro ="Entrada errada!\n";
	var numeroControle = 1;
	var partesData = "";
		
	var verificarNome = function(){
		
		if($("#txNome").val()){
			$("#txNome").css({"color":"blue", "background-color":"gray"})
		}else{
			alert("Campo Obrigatório! Preencha o nome.")
		}	
	}
	
	var verificarTamanhoData = function(){
		
		if(($("#txDtNascimento").val().length)!=10){
			
			mensagemErro += "- Número de digítos deve ser igual a 10.\n";
			//alert("Entrada errada! Número de digítos deve ser igual a 10.");
			
			return true;
		}else{
			return false;
		}			
	}
	
	var verificarBarras = function(){
		
		if(partesData.length != 3){
			
			mensagemErro += "- A data deve ser dividida por duas '/'.\n";
			//alert("Entrada errada! A data deve ser dividida por duas /");
			
			return true;
		} else{
			return false;
		}	
	}
	
	var verificarMes = function(){
				
		if((partesData[1]<1)||(partesData[1]>12)){
			
			mensagemErro += "- O mês está fora do intervalo.\n";
			return true;
		}else{
			return false;
		}
	}
	
	var inserirMensagemSucesso = function(dia, mes, ano){
		
		if(numeroControle == 1){
			$("#retorno").html("<p>dia: " + dia + " mês: " + mes + " ano: " + ano + "</p>");
			numeroControle = numeroControle + 1;
			//alert(numeroControle);
		}else{
			$("#retorno").empty();
			numeroControle = 1;
			$("#retorno").html("<p>dia: " + dia + " mês: " + mes + " ano: " + ano + "</p>");
			//alert(numeroControle);
		}
		
	}
	
	$("#salvar").click(function(){
		
		partesData = $("#txDtNascimento").val().split("/");
		verificarNome();
		var validacao1 = verificarTamanhoData();
		var validacao2 = verificarBarras();
		var validacao3 = verificarMes();
		
		if((validacao1==true)||(validacao2==true)||(validacao3==true)){
			alert(mensagemErro);
		}else{
			inserirMensagemSucesso(partesData[0], partesData[1], partesData[2]);	
		}
		
		mensagemErro ="Entrada errada!\n";
	});
	
});