package br.sc.senac.aula07;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
public class Exemplo03_controlador implements Serializable{

	private static final long serialVersionUID = -6578854353L;
	
	public String forwardLocal() {
		return "Exemplo02-menu.xhtml";
	}
	
	public String forwardExterno() {
		return "https://www.mkyong.com/jsf2/jsf-2-convertnumber-example/";
	}
	
	public String redirectLocal() {
		return "Exemplo01.xhtml?faces-redirect=true";
	}
	
	public void redirectExterno() throws IOException {
		ExternalContext contexto = FacesContext.getCurrentInstance().getExternalContext();
		contexto.redirect("http://br.ign.com/yooka-laylee/53708/news/yooka-laylee-data-para-versao-de-switch-nao-e-revelada-por-p");
		
	}
}
