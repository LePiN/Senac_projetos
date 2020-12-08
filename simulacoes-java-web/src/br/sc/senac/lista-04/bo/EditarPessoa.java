package br.sc.senac.lista04.bo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sc.senac.lista04.dao.EnderecoDAO;
import br.sc.senac.lista04.dao.PessoaDAO;
import br.sc.senac.lista04.entity.Endereco;
import br.sc.senac.lista04.entity.Pessoa;

public class EditarPessoa implements Acao {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) {

		Endereco end = new Endereco();
		Pessoa p = new Pessoa();
		PessoaDAO pdao = new PessoaDAO();
		EnderecoDAO endao = new EnderecoDAO();
		
		end.setIdEndereco(Integer.parseInt(request.getParameter("idEndereco")));
		end.setRua(request.getParameter("rua"));
		end.setNumero(Integer.parseInt(request.getParameter("numero")));
		end.setBairro(request.getParameter("bairro"));
		
		p.setIdPessoa(Integer.parseInt(request.getParameter("idPessoa")));
		p.setNome(request.getParameter("nome"));
		p.setSobrenome(request.getParameter("sobrenome"));
		p.setCpf(request.getParameter("cpf"));
		p.setEndereco(end);
		
		if((endao.atualizarEndereco(end))==true && (pdao.atualizarPessoa(p))) {
			return "/Controller?classe=ListarPessoas";
		}
		
		
		return null;
	}

	
	
}
