package br.sc.senac.aula05.filter;

public class FiltroCarro {

	private String modelo;

	// Utilizado para filtrar uma faixa de valores
	// (ex.: carros entre R$3000.00 e R$50000.00)
	private double valorInicial;
	private double valorFinal;
	private int anoInicial;
	private int anoFinal;
	private String nomeMontadora;
	private String paisMontadora;

	public FiltroCarro() {
		temCampoPreenchido();
	}

	public boolean temCampoPreenchido() {
		boolean temCampoPreenchido = false;

		if (this.getModelo() != null && !this.getModelo().isEmpty()) {
			temCampoPreenchido = true;
		}

		if (this.getNomeMontadora() != null && !this.getNomeMontadora().isEmpty()) {
			temCampoPreenchido = true;
		}

		if (this.getPaisMontadora() != null && !this.getPaisMontadora().isEmpty()) {
			temCampoPreenchido = true;
		}

		if (this.getAnoInicial() > 0 || this.getAnoFinal() > 0) {
			temCampoPreenchido = true;
		}

		if (this.getValorInicial() > 0 || this.getValorFinal() > 0) {
			temCampoPreenchido = true;
		}

		return temCampoPreenchido;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNomeMontadora() {
		return nomeMontadora;
	}

	public void setNomeMontadora(String nomeMontadora) {
		this.nomeMontadora = nomeMontadora;
	}

	public double getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(double valorInicial) {
		this.valorInicial = valorInicial;
	}

	public double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(double valorFinal) {
		this.valorFinal = valorFinal;
	}

	public int getAnoInicial() {
		return anoInicial;
	}

	public void setAnoInicial(int anoInicial) {
		this.anoInicial = anoInicial;
	}

	public int getAnoFinal() {
		return anoFinal;
	}

	public void setAnoFinal(int anoFinal) {
		this.anoFinal = anoFinal;
	}

	public String getPaisMontadora() {
		return paisMontadora;
	}

	public void setPaisMontadora(String paisMontadora) {
		this.paisMontadora = paisMontadora;
	}
}