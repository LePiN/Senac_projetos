package br.sc.senac.lista04.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sc.senac.lista04.bo.Acao;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Lista06_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Lista06_Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.processa(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.processa(request, response);
	}
	
	private void processa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8");
		
		String nomeClasse = "br.sc.senac.lista04.bo." + request.getParameter("classe");
		
		try {
			
			Class<?> classe = Class.forName(nomeClasse);
			Acao acao = (Acao) classe.newInstance();
			String paginaRetorno = acao.executar(request, response);
			request.getRequestDispatcher(paginaRetorno).forward(request, response);
			
		} catch (ClassNotFoundException e) {
			response.sendRedirect("/ListaExercicios/lista04/L4-exercicio-3_erro.jsp");
		} catch (InstantiationException e) {
			response.sendRedirect("/ListaExercicios/lista04/L4-exercicio-3_erro.jsp");
		} catch (IllegalAccessException e) {
			response.sendRedirect("/ListaExercicios/lista04/L4-exercicio-3_erro.jsp");
		}
	}

}
