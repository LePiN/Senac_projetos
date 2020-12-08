package br.sc.senac.aula07;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

/**
 * Classe de controladora da tela do exemplo 01 (aula 07 - JSF)
 * 
 * @author Vilmar C. Pereira Júnior
 * 
 */

@ManagedBean(name = "objetoExemplo01")
public class Exemplo01Bean implements Serializable {

	private static final long serialVersionUID = -5699950136687451903L;
	// Atributos
	public String campo1;
	public String campo2;
	public String mensagem;

	// Construtor do Bean
	public Exemplo01Bean() {
		super();
	}

	public void getCampos() {
		setMensagem("Campo 1: " + this.getCampo1() + ". Campo 2: " + this.getCampo2());
	}

	public String getCampo1() {
		return campo1;
	}

	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}

	public String getCampo2() {
		return campo2;
	}

	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}