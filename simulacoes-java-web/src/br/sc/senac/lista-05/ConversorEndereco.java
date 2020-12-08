package br.sc.senac.lista05;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.sc.senac.lista04.dao.EnderecoDAO;
import br.sc.senac.lista04.entity.Endereco;

@FacesConverter (value="conversorEndereco")
public class ConversorEndereco implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		String pedacos[] = new String[3];
		pedacos =arg2.split(",");		
		int numero = Integer.parseInt(pedacos[1].substring(8));
		pedacos[2] = pedacos[2].substring(1);
		
		//System.out.println(pedacos[0] + numero + pedacos[2]);;
		
		EnderecoDAO endao = new EnderecoDAO();
		Endereco e = endao.obterEndereco(pedacos[0], numero, pedacos[2]);
		
		//System.out.println(e);
		
		return e;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		
		Endereco e = new Endereco();
		e = (Endereco) arg2;
		
		return e.toString();
	}

}
