package br.sc.senac.aula04.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Atividade7ServletA
 */
@WebServlet("/atividade7ServletA")
public class Atividade7ServletA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Atividade7ServletA() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Obtém a ação chamada
		String acao = request.getParameter("acao");
		
		//Incluir um atributo na Sessão
		request.getSession().setAttribute("atributo1", "Eu sou o primeiro atributo criado");
		
		if(acao.equals("encaminhar")) {
  			//Forward
			request.getRequestDispatcher("/atividade7ServletB").forward(request, response);
		}else if (acao.equals("redirecionar")) {
  			//Redirect
			response.sendRedirect("/DW20172/atividade7ServletB");
		}
	}
}
