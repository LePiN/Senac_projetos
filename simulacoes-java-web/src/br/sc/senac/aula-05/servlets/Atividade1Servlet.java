package br.sc.senac.aula05.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sc.senac.dao.ConnectionFactory;

/**
 * Servlet implementation class Atividade1Servlet
 */
@WebServlet("/aula05Atividade1")
public class Atividade1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Atividade1Servlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//A tela deve retornar uma informação dizendo se a conexão é válida ou não
		ConnectionFactory fabrica = new ConnectionFactory(); 
		Connection conexao = fabrica.obterConexao();
		
		if(conexao != null) {
			response.getWriter().println("Conexão válida");
		}else {
			response.getWriter().println("Conexão inválida");
		}
	}
}