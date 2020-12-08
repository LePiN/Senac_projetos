$(document).ready(function() {                        
   
	$("#btnMultiplicar").click(function(){
		//Chamada POST via AJAX
		//$.post(url, data, callback);
		var numero1 = $("#txtNumero1").val();
		var numero2 = $("#txtNumero2").val();
		
		$.post("/DW20172/CalculadoraServlet", {n1 : numero1, n2: numero2}, retornoCalculadora);
	});
	
	var retornoCalculadora = function(data, status){
		//https://www.w3schools.com/jquery/
		$("#divResultado").text("Resultado: " + data + ". Status do retorno: " + status);
	};
});