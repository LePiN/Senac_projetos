/**
 * 
 */
$(document).ready(function() {
	
	//coloca o footer no fundo
	function redefinirFoteer() {
	
		let h = $(".main").innerHeight()
		let hw = $(window).innerHeight()
	
		$('.main').css({
			'min-height' : hw - 116
		});
		//alert('tamanho h'+ h + ' === tamanho hw ' + hw)	
	}
	
	//carregar metodos
	redefinirFoteer()
	//resize quando mudar o tamanho da tela
	$(window).resize(function() {
		redefinirFoteer()
	});
	
	
	
	// resize modal de a cordo com o tamanho da tela (so a altura)
	const maxh = $(window).height() - 250; //altura
	$(".body-dialog-listar").css('max-height', maxh);
	
	// resize card historico
	const maxhiscorico = $(window).height() - 255; //altura
	$(".historico-premio > .body-card").css('max-height', maxhiscorico);
	
	// resize mapa Busca shoppings
	const maxMapa = $(window).height() - 300; //altura
	const wdMapa = $(window).width()
	if (wdMapa<640) {
		$(".div-map .mapa-google-mob").css('height', maxMapa);
	}
	
	$(".btn-mapa-mob").click(function(){
        $(".mapa-google-mob").slideToggle();
        $(".box-lista-mini-cards-shop").toggleClass("mob");
        $(".hide-map-mob").toggleClass("hide");
    });
	
	// Modal footer check-in
	const alturaCheck = $(window).height() -100;
	$(".modal-check .modal-check-content .modal-body").css('max-height', alturaCheck);
	
});
