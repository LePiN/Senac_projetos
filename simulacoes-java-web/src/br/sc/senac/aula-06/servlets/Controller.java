package br.sc.senac.aula06.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sc.senac.aula06.bo.Acao;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.processa(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.processa(request, response);
	}

	private void processa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			// Obtém a classe que implementa a ação requisitada
			String nomeClasse = "br.sc.senac.aula06.bo." + request.getParameter("classe");
			Class<?> classe = Class.forName(nomeClasse);

			// Instancia por reflexão
			Acao acao = (Acao) classe.newInstance();
			String paginaRetorno = acao.executar(request, response);

			// Encaminha para a página informada pela ação
			request.getRequestDispatcher(paginaRetorno).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}