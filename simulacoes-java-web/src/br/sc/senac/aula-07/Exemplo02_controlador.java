package br.sc.senac.aula07;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;


@ManagedBean 
public class Exemplo02_controlador implements Serializable {
	
	private static final long serialVersionUID = -2567567578245193L;
	
	private String tipoSelecionado;
	private ArrayList<Exemplo02_consoles> consoles;
	private ArrayList<Exemplo02_jogos> jogos;
	
	public Exemplo02_controlador() {
		super();
	}

	public String getTipoSelecionado() {
		return tipoSelecionado;
	}

	public void setTipoSelecionado(String tipoSelecionado) {
		this.tipoSelecionado = tipoSelecionado;
	}

	public ArrayList<Exemplo02_consoles> getConsoles() {
		return consoles;
	}

	public void setConsoles(ArrayList<Exemplo02_consoles> consoles) {
		this.consoles = consoles;
	}

	public ArrayList<Exemplo02_jogos> getJogos() {
		return jogos;
	}

	public void setJogos(ArrayList<Exemplo02_jogos> jogos) {
		this.jogos = jogos;
	}
	
	private ArrayList<Exemplo02_consoles> gerarConsoles(){
		ArrayList<Exemplo02_consoles> lista = new ArrayList<>();
		Exemplo02_consoles c1 = new Exemplo02_consoles("Atari 2600", "Segunda", "Atari", 1200.00, 1978);
		Exemplo02_consoles c2 = new Exemplo02_consoles("SNES", "Quarta", "Nintendo", 1000.00, 1990);
		Exemplo02_consoles c3 = new Exemplo02_consoles("PS4", "Oitava", "Sony", 2000.00, 2014);
		Exemplo02_consoles c4 = new Exemplo02_consoles("Switch", "Oitava", "Nintendo", 4000.00, 2017);
		
		lista.add(c1);
		lista.add(c2);
		lista.add(c3);
		lista.add(c4);
		
		return lista;
	}
	
	private ArrayList<Exemplo02_jogos> gerarJogos(){
		ArrayList<Exemplo02_jogos> lista = new ArrayList<>();
		Exemplo02_jogos j1 = new Exemplo02_jogos("Pitfall", "Aventura", "Activision", 80.00, 1982);
		Exemplo02_jogos j2 = new Exemplo02_jogos("Doom", "FPS", "Bethesda", 120.00, 1991);
		Exemplo02_jogos j3 = new Exemplo02_jogos("GTA V", "Sandbox", "Rockstar", 200.00, 2013);
		Exemplo02_jogos j4 = new Exemplo02_jogos("Super Mario World", "Mario", "Nintendo", 50.00, 1992);
		
		lista.add(j1);
		lista.add(j2);
		lista.add(j3);
		lista.add(j4);
		
		return lista;
	}
	
	public String gerar() {
				
		if(tipoSelecionado.equals("c")) {
			setConsoles(gerarConsoles());
			return "Exemplo02_consoles.xhtml";
		}else if(tipoSelecionado.equals("j")) {
			setJogos(gerarJogos());
			return "Exemplo02_jogos.xhtml";
		}
		return null;
	}
}
