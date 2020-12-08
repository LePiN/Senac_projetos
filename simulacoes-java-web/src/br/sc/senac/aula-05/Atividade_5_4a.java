package br.sc.senac.aula05;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Atividade_5_4a
 */
@WebServlet("/Atividade_5_4a")
public class Atividade_5_4a extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Atividade_5_4a() {
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
		
		String acao = request.getParameter("acao");
		request.getSession().setAttribute("atributo1", "Eu sou o primeiro atributo criado");
		
		if(acao.equals("encaminhar")) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Atividade_5_4b");
			dispatcher.forward(request, response);
		}else if(acao.equals("redirecionar")) {
			response.sendRedirect("/leandropn-tcs/Atividade_5_4b");
		}
		
	}

}
