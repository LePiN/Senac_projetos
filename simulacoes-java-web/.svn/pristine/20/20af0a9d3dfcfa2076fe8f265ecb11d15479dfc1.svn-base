package br.sc.senac.aula10.dao;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

	private static SessionUtils instance;

	public static SessionUtils getInstance() {
		if(instance == null) {
			instance = new SessionUtils();
		}

		return instance;
	}

	public HttpSession getSession() {
		// Obtem a Sessao
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) 
				context.getExternalContext().getRequest();

		HttpSession sessao = request.getSession();
		
		return sessao;
	}
}
