package br.sc.senac.controles;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sc.senac.entidades.Pessoa;
import br.sc.senac.perisistencia.PessoaDAO;

/**
 * Servlet implementation class ConsultarPessoa
 */
@WebServlet("/ConsultarPessoa")
public class ConsultarPessoa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarPessoa() {
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
		
		String consulta = request.getParameter("cpf");
		
		System.out.println(consulta);
		
		PessoaDAO pdao = new PessoaDAO();
		
		String resultado;
		
		Pessoa p = pdao.obterPessoaCPF(consulta);
		if(p.getIdPessoa() > 0){
			resultado = "O CPF corresponde ao usuario " + p.getNome() + " " + p.getSobrenome() + ".";
		}else {
			resultado = "O CPF informado nao corresponde a nenhum usuario.";
		}
		
		
		response.getWriter().print(resultado);
	}

}
