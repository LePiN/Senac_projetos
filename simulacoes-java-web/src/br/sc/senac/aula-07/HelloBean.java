package br.sc.senac.aula07;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class HelloBean implements Serializable{

	private static final long serialVersionUID = 5671406881800731454L;
	
	private String mensagem;

	public HelloBean() {
		super();
	}
	
	public void hello() {
		mensagem = "Hello World!";
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	
}
