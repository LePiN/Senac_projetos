package br.sc.senac.revisao;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.sc.senac.entidades.Endereco;
import br.sc.senac.perisistencia.EnderecoDAO;

@ManagedBean(name = "listagem")
@SessionScoped
public class Listagem {
	
	
	public List<Endereco> preencherTabela(){
		List<Endereco> lista = new ArrayList<Endereco>();
		EnderecoDAO endao = new EnderecoDAO();
		lista = (ArrayList<Endereco>) endao.obterEnderecos();
		//System.out.println(lista);
	
		return lista;
	}
		
}
