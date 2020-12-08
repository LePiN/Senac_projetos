package br.sc.senac.aula10.entity;

/**
 * Classe que modela a entidade Pessoa
 * 
 * @author Vilmar C. Pereira Junior
 * 
 *         Disciplina de Desenvolvimento Web Senac 2017.1
 *
 */
public class Endereco {

	private int id;
	private String rua;
	private int numero;
	private String bairro;

	public Endereco() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

		String mensagemEndereco = "Endereço #" + this.getId();

		if (this.getRua() != null) {
			mensagemEndereco += ". Rua: " + this.getRua();
		}

		if (this.getNumero() > 0) {
			mensagemEndereco += ". Numero: " + this.getNumero();
		}

		if (this.getBairro() != null) {
			mensagemEndereco += ". Bairro: " + this.getBairro();
		}
		return mensagemEndereco;
	}

}
