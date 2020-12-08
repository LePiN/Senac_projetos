package br.sc.senac.controles;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.context.RequestContext;
import br.sc.senac.entidades.Carro;
import br.sc.senac.filtros.FiltroCarro;
import br.sc.senac.perisistencia.CarroDAO;


@ManagedBean
@RequestScoped
public class ListarCarrosControle implements Serializable {

	private static final long serialVersionUID = -6280088058866546242L;
	private Carro carro;
	private List<Carro> carros;
	private FiltroCarro filtro;

	@PostConstruct 	
	public void init() {
		filtro = new FiltroCarro();
	}
	
		
	public FiltroCarro getFiltro() {
		return filtro;
	}
	public void setFiltro(FiltroCarro filtro) {
		this.filtro = filtro;
	}
	public Carro getCarro() {
		return carro;
	}
	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	public List<Carro> getCarros() {
		return carros;
	}
	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}
	
	public void preencherTabela() {
		CarroDAO cdao = new CarroDAO();
		carros = cdao.listarPorFiltro(this.filtro);
		//ListarCarrosControle novo = new ListarCarrosControle();
		//System.out.println(carros);
	}
	
	public void limparConsulta() {
		RequestContext.getCurrentInstance().reset("formulario:tabela");
		//carro = null;
		//RequestContext.getCurrentInstance().reset("formulario");
	}
}
