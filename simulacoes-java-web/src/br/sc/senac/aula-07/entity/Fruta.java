package br.sc.senac.aula07.entity;

public class Fruta {
	
	public String nome;
	public double peso;
	public String sabor;

	public Fruta(String nome, double peso, String sabor) {
		super();
		this.nome = nome;
		this.peso = peso;
		this.sabor = sabor;
	}

	public Fruta(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getSabor() {
		return sabor;
	}

	public void setSabor(String sabor) {
		this.sabor = sabor;
	}
	
}
