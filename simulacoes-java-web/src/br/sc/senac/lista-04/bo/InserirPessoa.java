package br.sc.senac.lista04.bo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.sc.senac.lista04.dao.EnderecoDAO;
import br.sc.senac.lista04.dao.PessoaDAO;
import br.sc.senac.lista04.entity.Endereco;
import br.sc.senac.lista04.entity.Pessoa;

public class InserirPessoa implements Acao {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) {
		
		Endereco end = new Endereco();
		Pessoa p = new Pessoa();
		PessoaDAO pdao = new PessoaDAO();
		EnderecoDAO endao = new EnderecoDAO();
		
		end.setRua(request.getParameter("rua"));
		end.setNumero(Integer.parseInt(request.getParameter("numero")));
		end.setBairro(request.getParameter("bairro"));
		
		p.setSobrenome(request.getParameter("sobrenome"));
		p.setNome(request.getParameter("nome"));		
		p.setCpf(request.getParameter("cpf"));
		
		Pessoa pessoaVerificada = pdao.obterPessoaCPF(request.getParameter("cpf"));
		if(pessoaVerificada.getCpf() != null){
			end.setIdEndereco(pessoaVerificada.getEndereco().getIdEndereco());
			endao.atualizarEndereco(end);
			p.setEndereco(end);
			p.setIdPessoa(pessoaVerificada.getIdPessoa());
			pdao.atualizarPessoa(p);		
		}else {
			end.setIdEndereco(endao.salvarEndereco(end));
			p.setEndereco(end);
			pdao.salvarPessoa(p);
		}
		
		
		return "/Controller?classe=ListarPessoas";
	
	}

}
