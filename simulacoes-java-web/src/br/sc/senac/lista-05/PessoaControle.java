package br.sc.senac.lista05;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.Util;

import br.sc.senac.lista04.dao.PessoaDAO;
import br.sc.senac.lista04.entity.Endereco;
import br.sc.senac.lista04.entity.Pessoa;

@ManagedBean
@SessionScoped
@ViewScoped 
public class PessoaControle implements Serializable {

	private static final long serialVersionUID = 6615431277083751981L;

	private String nome;
	private String sobrenome;
	private Date dtNascimento;
	private String cpf;
	private Endereco endereco;

	public PessoaControle(String nome, String sobrenome, Date dtNascimento, String cpf, Endereco endereco) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dtNascimento = dtNascimento;
		this.cpf = cpf;
		this.endereco = endereco;

	}

	public PessoaControle() {
		super();
		
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

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void incluirPessoa(){
		
		PessoaDAO pdao = new PessoaDAO();
		java.sql.Timestamp sq = new java.sql.Timestamp(this.dtNascimento.getTime());
		cpf=cpf.replaceAll("-","");
		Pessoa p = new Pessoa(nome, sobrenome, cpf, sq, endereco);
		//System.out.println(p);
		pdao.salvarPessoa(p);
		
		
	}
	
	public void mensagemStatus(){
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage("Wololo", new FacesMessage("Sucesso", "A pessoa foi salva no Banco de Dados." ));
		
	}
}
