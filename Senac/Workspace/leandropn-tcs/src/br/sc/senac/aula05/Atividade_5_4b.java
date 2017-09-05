package br.sc.senac.aula05;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Atividade_5_4b
 */
@WebServlet("/Atividade_5_4b")
public class Atividade_5_4b extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Atividade_5_4b() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String campo1 = request.getParameter("campo1");
		String acao = request.getParameter("acao");
		String atributo1 = (String) request.getSession().getAttribute("atributo1");
		
		response.getWriter().println("Ação chamada " + acao);
		response.getWriter().println("Parâmetro passado " + campo1);
		response.getWriter().println("Atributo passado " + atributo1);
	}

}
