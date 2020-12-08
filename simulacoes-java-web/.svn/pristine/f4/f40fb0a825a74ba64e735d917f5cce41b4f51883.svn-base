package br.sc.senac.aula07.entity;

public class Trabalhador {

	private String nome;
	private String profissao;
	private char sexo;
	
	public Trabalhador(String nome, String codigoProfissao, char sexo) {
		super();
		this.nome = nome;
		this.profissao = getNomeProfissao(codigoProfissao);
		this.sexo = sexo;
	}
	
	private String getNomeProfissao(String codigoProfissao) {
		
		String nomeProfissao = "";
		if(codigoProfissao != null && codigoProfissao != ""){
			if(codigoProfissao.equals("c")){
				nomeProfissao = "Carpinteiro";
			}else if(codigoProfissao.equals("m")){
				nomeProfissao = "Motorista";
			}else if(codigoProfissao.equals("p")){
				nomeProfissao = "Pedreiro";
			}
		}
		
		return nomeProfissao;
	}

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
}