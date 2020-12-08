package br.sc.senac.aula07;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import br.sc.senac.aula07.entity.Console;
import br.sc.senac.aula07.entity.Jogo;

@ManagedBean
public class Atividade1Bean implements Serializable{

	private static final long serialVersionUID = -2970865214288245193L;
	
	//Atributos do controlador
	private String tipoSelecionado;
	private ArrayList<Console> consoles;
	private ArrayList<Jogo> jogos;

	public Atividade1Bean() {
		super();
	}

	public String gerar() {
		String proximaTela = "";
		
		//Este método deve retornar uma lista de jogos ou de consoles, 
		//de acordo com a seleção na tela
		if(tipoSelecionado != null && !tipoSelecionado.isEmpty()) {
			if(tipoSelecionado.equals("c")) {
				consoles = gerarConsoles();
				proximaTela = "atividade1_listaConsoles.xhtml";
			}
			
			if(tipoSelecionado.equals("j")) {
				jogos = gerarJogos();
				proximaTela = "atividade1_listaJogos.xhtml";
			}
		}
		
		//Encaminhe para telas distintas (listaJogos.xhtml e listaConsoles.xhtml)
		return proximaTela;
	}
	
	public String voltarTelaPrincipal() {
		return "atividade1.xhtml";
	}
	
	private ArrayList<Console> gerarConsoles(){
		ArrayList<Console> lista = new ArrayList<>();
		Console c1 = new Console("Atari 2600", "Segunda", "Atari", 1200.00, 1978);
		Console c2 = new Console("SNES", "Quarta", "Nintendo", 1000.00, 1990);
		Console c3 = new Console("PS4", "Oitava", "Sony", 2000.00, 2014);
		Console c4 = new Console("Switch", "Oitava", "Nintendo", 4000.00, 2017);
		
		lista.add(c1);
		lista.add(c2);
		lista.add(c3);
		lista.add(c4);
		
		return lista;
	}
	
	private ArrayList<Jogo> gerarJogos(){
		ArrayList<Jogo> lista = new ArrayList<>();
		Jogo j1 = new Jogo("Pitfall", "Aventura", "Activision", 80.00, 1982);
		Jogo j2 = new Jogo("Doom", "FPS", "Bethesda", 120.00, 1991);
		Jogo j3 = new Jogo("GTA V", "Sandbox", "Rockstar", 200.00, 2013);
		Jogo j4 = new Jogo("Super Mario World", "Mario", "Nintendo", 50.00, 1992);
		
		lista.add(j1);
		lista.add(j2);
		lista.add(j3);
		lista.add(j4);
		
		return lista;
	}
	
	public String getTipoSelecionado() {
		return tipoSelecionado;
	}

	public void setTipoSelecionado(String tipoSelecionado) {
		this.tipoSelecionado = tipoSelecionado;
	}

	public ArrayList<Console> getConsoles() {
		return consoles;
	}

	public void setConsoles(ArrayList<Console> consoles) {
		this.consoles = consoles;
	}

	public ArrayList<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(ArrayList<Jogo> jogos) {
		this.jogos = jogos;
	}
	
	
}