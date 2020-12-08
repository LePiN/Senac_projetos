package br.sc.senac.lista04.entity;

public class Endereco {
	
	private int idEndereco;
	private String rua;
	private int numero;
	private String bairro;
	
	public Endereco(int idEndereco, String rua, int numero, String bairro) {
		super();
		this.idEndereco = idEndereco;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
	}

	public Endereco(String rua, int numero, String bairro) {
		super();
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
	}

	public Endereco() {
		super();
	}

	public int getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Override
	public String toString() {
		return rua + ", numero " + numero + ", " + bairro;
	}
	
	
}
