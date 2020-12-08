//Ouvinte que será disparado quando o documento estiver completo
document.addEventListener("DOMContentLoaded",iniciar);

function iniciar(){
	var botaoAdicionar = document.getElementById("btnAdicionarLink");
	botaoAdicionar.addEventListener("click", adicionarLink);
}

function adicionarLink(){
	//Selecionar o elemento que vai receber o link
	elemento = document.getElementById("div1"); 
	var links = document.getElementsByClassName("linkAtividade2");
	
	//Criar o link para uma classe linkAtividade2
	link = "<div class='linkAtividade2 col-md-12 col-lg-12'>" +
	"<a href='http://www.sc.senac.br'>Novo link ("+ (links.length + 1) +")</a>" +
	"</div>";
	
	//Adicionar o link criado no elemento selecionado
	elemento.innerHTML += link;
}

function adicionarLinkVersao2(){
	var link = document.createElement("a");
	var textoLink = document.createTextNode("Link para o site W3schools");
	
	link.appendChild(textoLink);
	link.href = "http://www.w3schools.com";
	link.setAttribute("class", "linkAtividade2  ");
	link.target = "_blank";
	
	var div = document.getElementById("div1");
	div.appendChild(link);
}

function removerLink(){
	//Selecionar o link por classe, id, etc.
	//Exemplos: https://www.w3schools.com/jsref/dom_obj_document.asp
	
	//Remover o link (apenas um, caso selecionar por classe)
	var links = document.getElementsByClassName("linkAtividade2");
	var ultimoLink = links[links.length - 1]; 
	
	if(ultimoLink){
		elemento = document.getElementById("div1"); 
		elemento.removeChild(ultimoLink);
	}else{
		alert("Não há link para remover");
	}
}