package br.sc.senac.aula07;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.sc.senac.aula07.entity.Trabalhador;

/**
 * Anotação usada para manter o objeto do controlador instaciado durante toda a
 * sessão.
 * 
 * @author Vilmar C. Pereira Júnior
 *
 */
@SessionScoped
@ManagedBean(name = "exemplo04Controller")
public class Exemplo04Bean implements Serializable {

	private static final long serialVersionUID = 3551938274141784483L;
	// Atributos
	private String nome;
	private String profissao;
	private char sexo;
	private ArrayList<Trabalhador> trabalhadores;

	// Construtor vazio
	public Exemplo04Bean() {
	}

	// Métodos
	public String salvar() {
		Trabalhador t = new Trabalhador(nome, profissao, sexo);

		if (this.getTrabalhadores() == null) {
			trabalhadores = new ArrayList<>();
		}

		trabalhadores.add(t);
		
		//Vai encaminhar
		return "atividade04_listagem.xhtml?faces-redirect=true";
	}

	public String voltar() {
		return "atividade04_cadastro.xhtml?faces-redirect=true";
	}

	// Gets e sets dos atributos
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public ArrayList<Trabalhador> getTrabalhadores() {
		return trabalhadores;
	}

	public void setTrabalhadores(ArrayList<Trabalhador> trabalhadores) {
		this.trabalhadores = trabalhadores;
	}
}
