package br.sc.senac.lista3.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Exercicio1Servlet
 */
@WebServlet("/salvarCarros")
public class Exercicio1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Exercicio1Servlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] marcasInformadas = request.getParameterValues("marca");
		String modelo1 = request.getParameter("modelo1");
		String modelo2 = request.getParameter("modelo2");
		
		response.getWriter().println(" *** Valores de parâmetros obtidos com request.getParameter() *** ");
		response.getWriter().println("Modelo 1 informado: " + modelo1);
		response.getWriter().println("Modelo 2 informado: " + modelo2);
		response.getWriter().println("");
		
		//Nomes dos atributos informados
		Enumeration<String> chaves = request.getParameterNames();
		String chave;
		response.getWriter().println(" *** Chaves de parâmetros obtidas com request.getParameterNames() *** ");
		while(chaves.hasMoreElements()) {
			chave = chaves.nextElement();
			response.getWriter().println("Chave (atributo da requisição): " + chave);
		}

		response.getWriter().println("");
		response.getWriter().println(" *** Chaves de parâmetros obtidas com request.getParameterValues('marca') *** ");
		for (int i = 0; i < marcasInformadas.length; i++) {
			String marcaAtual = marcasInformadas[i];
			response.getWriter().println("Marca (" + i + ") informada: " + marcaAtual); 
		}
		
	}

}
