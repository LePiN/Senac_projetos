package br.sc.senac.lista04.bo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sc.senac.lista04.dao.PessoaDAO;

public class RemoverPessoa implements Acao {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) {
		
		int id =Integer.parseInt(request.getParameter("idPessoa"));
		PessoaDAO p = new PessoaDAO();
		boolean status = p.removerPessoa(id);
		String mensagem;
		if(status) {
			mensagem = "Pessoa (id=" + id + ") foi removida";
		}else {
			mensagem="Erro na remoção";
		}
		request.setAttribute("msg", mensagem);
				
		return "/ListaExercicios/lista04/L4-exercicio-2_listar.jsp";
	}

}
