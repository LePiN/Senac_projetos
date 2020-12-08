package br.sc.senac.revisao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import br.sc.senac.entidades.Endereco;
import br.sc.senac.perisistencia.EnderecoDAO;

@ManagedBean
@SessionScoped
public class ConsultaEnderecos implements Serializable {

	private static final long serialVersionUID = 4966098996743502132L;
	private Endereco endereco;
	private List<Endereco> enderecos;
	
	@ManagedProperty("#{listagem}")
	private Listagem tabela;
	
	@PostConstruct
	public void init() {
		enderecos = tabela.preencherTabela();
		//System.out.println(enderecos);
	}
	
	public ConsultaEnderecos() {
		super();	
		
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

	public Listagem getTabela() {
		return tabela;
	}

	public void setTabela(Listagem tabela) {
		this.tabela = tabela;
	}	
	
	public void editarEndereco() {
		EnderecoDAO endao = new EnderecoDAO();
		endao.atualizarEndereco(this.endereco);	
		System.out.println(this.endereco);
	}
	
	public void removerEndereco(){
		EnderecoDAO endao = new EnderecoDAO();
		endao.removerEndereco(this.endereco.getIdEndereco());
		enderecos = tabela.preencherTabela();
	}
		
	//public static void main(String[] args) {
		//ConsultaEnderecos teste = new ConsultaEnderecos();
		//Listagem teste2 = new Listagem();
		//List<Endereco> resultado = new ArrayList<Endereco>();
		//resultado =teste2.preencherTabela();
		//System.out.println(resultado);
	//}
}
