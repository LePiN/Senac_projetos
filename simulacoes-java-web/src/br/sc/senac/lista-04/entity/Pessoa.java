package br.sc.senac.lista04.entity;

import java.sql.Timestamp;

public class Pessoa {
	
	private int idPessoa;
	private String nome;
	private String sobrenome;
	private String cpf;
	private Timestamp dtNascimento;
	private Endereco endereco;

	public Pessoa(int idPessoa, String nome, String sobrenome, String cpf, Timestamp dtNascimento, Endereco endereco) {
		super();
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
		this.endereco = endereco;
	}
	
	public Pessoa(String nome, String sobrenome, String cpf, Timestamp dtNascimento, Endereco endereco) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
		this.endereco = endereco;
	}

	public Pessoa() {
		super();
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Timestamp getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Timestamp dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Pessoa [idPessoa=" + idPessoa + ", nome=" + nome + ", sobrenome=" + sobrenome + ", cpf=" + cpf
				+ ", dtNascimento=" + dtNascimento + ", endereco=" + endereco + "]";
	}

		
	
}
