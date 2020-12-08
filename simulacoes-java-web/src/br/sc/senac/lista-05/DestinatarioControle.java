package br.sc.senac.lista05;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class DestinatarioControle implements Serializable {

	private static final long serialVersionUID = -6077067858478437162L;
	
	private String dataSaida;

	public DestinatarioControle(String dataSaida) {
		super();
		this.dataSaida = dataSaida;
	}

	public DestinatarioControle() {
		super();
	}

	public String getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}
	
	public void setAction(String data) {
		this.dataSaida = data;
	}
	

}
