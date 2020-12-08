$(document).ready(function() {    
	$("#botao1").click(function(event) {  
        $("p:first").hide();
    });
	
	$("#botao2").dblclick(function(event) {  
        $("p:first").show("slow", function(event) {  
            alert("Efeito show concluído");
        });
	});
	
	$('h1').mouseenter(function(event) {  
		$(this).fadeTo(1000, 0.1);
    });
	
	$('h1').mouseleave(function(event) {  
		$(this).fadeTo(5000, 1);
    });
	
	$('#slide').click(function(event) {  
		$("#painel").slideToggle("slow", function(event) {  
            alert("Efeito slide concluído");
		});
	});
	
	$('#iniciar').click(function(){
        var div = $("#divAnimation");
        div.animate({height: '300px', opacity: '0.4'}, "slow");
        div.animate({width: '300px', opacity: '0.8'}, "slow");
        div.animate({height: '100px', opacity: '0.4'}, "slow");
        div.animate({width: '100px', opacity: '1'}, "slow");
	});
	
	$('#parar').click(function(event) {  
		$("#divAnimation").stop(true, true);
    });
	
});