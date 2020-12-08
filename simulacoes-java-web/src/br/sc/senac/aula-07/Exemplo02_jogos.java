package br.sc.senac.aula07;

public class Exemplo02_jogos {
	
	public String nome;
	public String genero;
	public String produtora;
	public double preco;
	public int anoLancamento;
	
	public Exemplo02_jogos(String nome, String genero, String produtora, double preco, int anoLancamento) {
		super();
		this.nome = nome;
		this.genero = genero;
		this.produtora = produtora;
		this.preco = preco;
		this.anoLancamento = anoLancamento;
	}

	public Exemplo02_jogos() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getProdutora() {
		return produtora;
	}

	public void setProdutora(String produtora) {
		this.produtora = produtora;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	
	
	
	

}
