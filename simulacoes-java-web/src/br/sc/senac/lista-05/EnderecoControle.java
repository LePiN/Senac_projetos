package br.sc.senac.lista05;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.sc.senac.lista04.dao.EnderecoDAO;
import br.sc.senac.lista04.entity.Endereco;

@ManagedBean
@SessionScoped
public class EnderecoControle implements Serializable {

	private static final long serialVersionUID = 6155657443874936494L;
	
	private String rua;
	private int numero;
	private String bairro;
	private Endereco endereco;
	private List<Endereco> enderecos = new ArrayList<Endereco>();
		
	public EnderecoControle(String rua, int numero, String bairro) {
		super();
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
	}

	public EnderecoControle() {
		super();
		//EnderecoDAO lista = new EnderecoDAO();
		
		//for(Endereco e:lista.obterEnderecos()) {
		//	this.enderecos.add(e.toString());
		//}	
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
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public ArrayList<Endereco> getEnderecos() {
		return (ArrayList<Endereco>) enderecos;
	}

	public void setEnderecos(ArrayList<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	public List<Endereco> completaConsulta(String query){
		EnderecoDAO endao = new EnderecoDAO();
		this.enderecos = endao.obterEnderecos();
		List<Endereco> sugestoes = new ArrayList<Endereco>();
		for(Endereco e:this.enderecos){
			System.out.println(e);
			if(e.toString().startsWith(query)){
				sugestoes.add(e); 
			}
		}
		return sugestoes;
	}

	public void incluirEndereco(){
		
		EnderecoDAO endao = new EnderecoDAO();
		Endereco e = new Endereco(this.rua,this.numero,this.bairro);
		endao.salvarEndereco(e);				
	}
	
	
}
