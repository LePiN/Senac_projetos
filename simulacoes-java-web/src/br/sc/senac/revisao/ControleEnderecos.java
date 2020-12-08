package br.sc.senac.revisaoProva;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.sc.senac.lista04.dao.EnderecoDAO;
import br.sc.senac.lista04.entity.Endereco;


@ManagedBean
@SessionScoped
public class ControleEnderecos implements Serializable {
	
	private static final long serialVersionUID = 7062071298282517436L;
	private Endereco endereco;
	private ArrayList<Endereco> listaEndereco;
	private EnderecoDAO endao;
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public ArrayList<Endereco> getListaEndereco() {
		return listaEndereco;
	}
	public void setListaEndereco(ArrayList<Endereco> listaEndereco) {
		this.listaEndereco = listaEndereco;
	}
	
	@PostConstruct
	public void init() {
		listaEndereco = (ArrayList<Endereco>) endao.obterEnderecos();
	}

}
