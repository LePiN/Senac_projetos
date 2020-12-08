function mostrarNomes(){
	var pessoas = ["João", "Maria", "José", "Luiza", "Rafael", "Laura"];
	var texto = "<br>";
	for (var i = 0; i < pessoas.length; i++) {
	    texto += pessoas[i] + "<br>";
	}

	document.getElementById("divNomes").innerHTML += texto;
}

function mostrarCarro(){
	var carro = {nome:"Gol", marca:"VW", placa:"ABC1234", ano: 2015};
	var texto = "<br>";
	var atributo;
	for (atributo in carro) {
		texto += carro[atributo] + " ";
	}
	document.getElementById("divCarro").innerHTML += texto;
}