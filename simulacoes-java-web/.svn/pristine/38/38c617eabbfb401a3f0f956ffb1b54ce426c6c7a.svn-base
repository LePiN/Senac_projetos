package br.sc.senac.aula07;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

/**
 * Classe de controladora das telas de exemplo de redirecionamento
 * 
 * @author Vilmar C. Pereira Júnior
 * 
 */

@ManagedBean(name="redirecionador")
public class ExemploRedirectBean implements Serializable {

	// Atributos
	private static final long serialVersionUID = 8942352292745693801L;

	// Construtor do Bean
	public ExemploRedirectBean() {
		super();
	}
	
	public String irParaPagina1(){
		return "exemploRedirecionamento1?faces-redirect=true";
	}
	
	public String irParaPagina2(){
		return "exemploRedirecionamento2?faces-redirect=true";
	}

}