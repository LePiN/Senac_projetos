package br.sc.senac.aula07;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name="beanRF")
public class Atividade03Bean implements Serializable{

	private static final long serialVersionUID = -8363296722786327504L;

	public Atividade03Bean() {

	}

	public String forwardLocal() {
		return "atividade1.xhtml";
	}

	public String forwardExterno() {
		return "www.sc.senac.br";
	}

	public String redirectLocal() {
		return "atividade1.xhtml?faces-redirect=true";
	}

	public void redirectExterno() {
		try {
			ExternalContext contexto = FacesContext.getCurrentInstance().getExternalContext(); 
			contexto.redirect("http://www.sc.senac.br");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}