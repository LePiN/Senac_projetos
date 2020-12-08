package br.sc.senac.lista04.bo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sc.senac.lista04.dao.PessoaDAO;
import br.sc.senac.lista04.entity.Pessoa;

public class ListarPessoas implements Acao {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) {
		
		PessoaDAO p = new PessoaDAO();
		List<Pessoa> pessoas = p.obterPessoas();
		request.setAttribute("pessoas", pessoas);
		
		return "/ListaExercicios/lista04/L4-exercicio-2_listar.jsp";
	}

}
