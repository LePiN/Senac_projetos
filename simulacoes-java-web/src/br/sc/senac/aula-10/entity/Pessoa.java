package br.sc.senac.aula10.entity;

/**
 * Classe que modela a entidade Pessoa
 * 
 * @author Vilmar C. Pereira Junior
 * 
 *         Disciplina de Desenvolvimento Web Senac 2017.1
 *
 */
public class Pessoa {

	private int id;
	private String nome;
	private String sobrenome;
	private Endereco endereco; // Representa a FK no banco
	private String login;
	private String senha;
	private int tipoPermissao;

	public Pessoa() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		String mensagem = "Pessoa #" + this.getId();

		if (this.getNome() != null) {
			mensagem += ". Nome: " + this.getNome();
		}

		if (this.getSobrenome() != null) {
			mensagem += ". Sobrenome: " + this.getSobrenome();
		}

		if (this.getEndereco() != null) {
			mensagem += this.getEndereco().toString();
		}

		return mensagem;
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

	public int getTipoPermissao() {
		return tipoPermissao;
	}

	public void setTipoPermissao(int tipoPermissao) {
		this.tipoPermissao = tipoPermissao;
	}

}
