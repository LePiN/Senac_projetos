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
@WebServlet("/atividade7ServletB")
public class Atividade7ServletB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Atividade7ServletB() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String campo1 = request.getParameter("campo1");
		String acao = request.getParameter("acao");
		String atributo1 = (String) request.getSession().getAttribute("atributo1");
		
		response.getWriter().println("Ação chamada: " + acao);
		response.getWriter().println("Valor do campo 1: " + campo1);
		response.getWriter().println("Valor do atributo 1: " + atributo1);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String campo1 = request.getParameter("campo1");
		String acao = request.getParameter("acao");
		String atributo1 = (String) request.getSession().getAttribute("atributo1");
		
		response.getWriter().println("Ação chamada: " + acao);
		response.getWriter().println("Valor do campo 1: " + campo1);
		response.getWriter().println("Valor do atributo 1: " + atributo1);
	}
}
