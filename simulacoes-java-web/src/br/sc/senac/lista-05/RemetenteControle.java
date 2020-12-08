package br.sc.senac.lista05;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class RemetenteControle implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String dataEntrada;
	private String tipo;
	
	public RemetenteControle(String data, String tipo) {
		super();
		this.dataEntrada = data;
		this.tipo = tipo;
	}

	public RemetenteControle() {
		super();
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String data) {
		this.dataEntrada = data;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String enviar() {
		if(this.tipo.equals("Forward")){
			return "L5-exercicio02_destinatario.xhtml";
		}else if(this.tipo.equals("Redirect")){
			return "L5-exercicio02_destinatario.xhtml?faces-redirect=true";
		}		
		return null;
	}
	
	


}
