package br.sc.senac.aula07;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean 
public class Exemplo01 implements Serializable {

	private static final long serialVersionUID = 1L;
	private String campo1;
	private String campo2;
	private String mensagem;
	
	public Exemplo01() {
		super();
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
	
	public void mostrarMensagem() {
		this.mensagem = campo1 + campo2;
	}

}
