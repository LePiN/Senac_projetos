//Arquivo javascript com as funções do exercício 1 (Aula 09)
$(document).ready(function() {                        
   
	$("#btnAdicionar").click(function(){
		var divBotoes = $("#divBotoes");
		
		var quantidadeBotoes = $("#divBotoes button").size();
		var corBotaoBootstrap;
		var idBotao = "btn" + (quantidadeBotoes + 1);
		
		if((quantidadeBotoes % 2) == 0){
			corBotaoBootstrap = "btn-danger";
		}else{
			corBotaoBootstrap = "btn-success";
		}
		
		//Cria um novo botão
		divBotoes.append
		("<button class='btn " + corBotaoBootstrap + "' id='" 
				+ idBotao + "'>Clique aqui</button>");
		
		$("#" + idBotao).click(onClickBotaoNovo);
	});
	
	$("#btnRemover").click(function(){
		//TODO implementar a função (remover o primeiro botão da classe btnNovo)
	});
	
	var onClickBotaoNovo = function(){
		alert("Olá, sou um botão dinâmico");
	};
	
	$("#botao1").click(function(event) {  
        $("p:first").hide();
    });
	
	$('#botao2').dblclick(function(event) {  
        $("p:first").show();
    });
	
	$('h1').mouseenter(function(event) {  
		$(this).css("color", "blue");
    });
	
	$('h1').mouseleave(function(event) {  
		$(this).css("color", "green");
    });
	
	$('.campoFormulario').focus(function(event) {  
		$(this).css("background-color", "LightCoral");
    });
	
	$('#nome').blur(function(event) {
		var nomePreenchido = $(this).val();
		
		if(nomePreenchido){
			$(this).css("background-color", "LightGreen");
		}else{
			alert("Preencha o nome");
		}
    });
	
	$('#apagar').mouseup(function(event) {  
        $("*").hide();
    });
	
	$(document).dblclick(function(event) {  
        $("*").show();
    });
});