package br.sc.senac.aula05.entity;

public class Carro {

	private int id;
	private Montadora montadora;
	private String modelo;
	private int ano;
	private double valor;
	
	public Carro() {
		
	}
	
	public Carro(Montadora montadora, String modelo, int ano, double valor) {
		this.montadora = montadora;
		this.modelo = modelo;
		this.ano = ano;
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Carro [id=" + id + ", montadora=" + montadora + ", modelo=" + modelo + ", ano=" + ano + ", valor="
				+ valor + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Montadora getMontadora() {
		return montadora;
	}

	public void setMontadora(Montadora montadora) {
		this.montadora = montadora;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}