package br.sc.senac.aula05.entity;

public class Veiculo {

	private int id_veiculo;
	private Montadora montadora;
	private String modelo;
	private int ano;
	private double valor;
	
	public Veiculo(int id_veiculo, Montadora montadora, String modelo, int ano, double valor) {
		super();
		this.id_veiculo = id_veiculo;
		this.montadora = montadora;
		this.modelo = modelo;
		this.ano = ano;
		this.valor = valor;
	}

	public Veiculo() {
		super();
	}

	public int getId_veiculo() {
		return id_veiculo;
	}

	public void setId_veiculo(int id_veiculo) {
		this.id_veiculo = id_veiculo;
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
