package br.sc.senac.aula06.bo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Acao {
	public String executar(HttpServletRequest request, HttpServletResponse response);
}
