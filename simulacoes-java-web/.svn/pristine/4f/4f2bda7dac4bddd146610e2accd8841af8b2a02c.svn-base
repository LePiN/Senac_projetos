$(document).ready(function() {    
	$("#btnSalvar").click(function(event) {  
		//Obter o nome da pessoa no campo de input
		var nomeDigitado = $("#nmPessoa").val();
		
		//Salvar o nome da pessoa em um cookie (chave "nomePessoa")
		//Referência: https://github.com/js-cookie/js-cookie
		Cookies.set('nomePessoa', nomeDigitado, {expires:5});
		
		//Redirecionar para exemploCookies2.html
		//Encaminha para a proxima pagina
		url = "exemploCookies2.html";
		$(location).attr("href", url);
    });
	
	if($("#h1NomePessoa").length > 0){
		//Obter o cookie "nomePessoa"
		var cookiePessoa = Cookies.get('nomePessoa');
		
		//Escreve o valor do cookie no h1 "h1NomePessoa" -> tela 2
		$("#h1NomePessoa").html(cookiePessoa);
		
		//Apagar o cookie
		Cookies.remove('nomePessoa');
	}
});