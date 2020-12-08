package br.sc.senac.entidades;

public class Montadora {
	
	//Atributos
	private int id;
	private String nome;
	private String pais;
	
	//Construtores
	public Montadora() {
		super();
	}
	
	public Montadora(String nome, String pais) {
		super();
		this.nome = nome;
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "Montadora [id=" + id + ", nome=" + nome + ", pais=" + pais + "]";
	}

	//Getters e Setters
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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
}