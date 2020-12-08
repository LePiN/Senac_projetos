package br.sc.senac.aula10.dao;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.sc.senac.aula10.bo.PermissaoBO;
import br.sc.senac.aula10.entity.Pessoa;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 276995838750556459L;
	public String login;
	public String senha;
	public Pessoa pessoa;


	public LoginBean() {
	}
	
	public String fazerLogout() {
		//Obter a sessão
		
		//Invalidar a sessão
		
		//Redirecionar para a tela inicial de login
		return "login.xhtml";
	}

	public String autenticar() {
		String paginaDestino;

		// Chamar o DAO para verificar se o login e a senha estão corretos
		PessoaDAO dao = new PessoaDAO();
		Pessoa pessoaFazendoLogin = dao.obterPessoa(login, senha);

		// Obtem a Sessao (chamada encapsulada na classe SessionUtils)
		HttpSession sessao = SessionUtils.getInstance().getSession();
		
		// Caso sim:
		if (pessoaFazendoLogin != null) {
			// Adicionar a pessoa na Session
			sessao.setAttribute("pessoaLogada", pessoaFazendoLogin);
			
			PermissaoBO permissaoBO = new PermissaoBO();
			if(permissaoBO.temPermissao(pessoaFazendoLogin)) {
				// encaminhar para a página de sucesso
				paginaDestino = "/aula10/restrito/sucessoLogin.xhtml";
			}else {
				paginaDestino = permissaoBO.obterDestino(pessoaFazendoLogin);
			}
		} else {
			sessao.invalidate();

			// Caso contrário: encaminhar para a página de erro
			paginaDestino = "erroLogin.xhtml";
		}

		return paginaDestino;

	}

	public String voltar() {
		return "login.xhtml";
	}

	public Pessoa getPessoa() {
		//Consulta a sessão para preencher o atributo pessoa
		HttpSession sessao = SessionUtils.getInstance().getSession();

		this.setPessoa((Pessoa) sessao.getAttribute("pessoaLogada"));

		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}