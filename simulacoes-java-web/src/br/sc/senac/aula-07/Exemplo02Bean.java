package br.sc.senac.aula07;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import br.sc.senac.aula07.entity.Animal;
import br.sc.senac.aula07.entity.Fruta;

@ManagedBean(name = "exemplo02")
public class Exemplo02Bean implements Serializable {

	private static final long serialVersionUID = 4917779772303274710L;

	// Atributo para armazenar qual tipo foi selecionado (animais ou frutas)
	private String tipoSelecionado;
	private ArrayList<String> lista;

	public Exemplo02Bean() {
		super();
	}

	// Método que gera uma lista de acordo com o tipo selecionado
	public void gerarLista() {
		ArrayList<String> lista = new ArrayList<>();

		if (this.getTipoSelecionado() != null) {
			if (this.getTipoSelecionado().equals("animal")) {
				lista = gerarAnimais();
			} else if (this.getTipoSelecionado().equals("fruta")) {
				lista = gerarFrutas();
			}
		}
		this.setLista(lista);
	}

	private ArrayList<String> gerarAnimais() {
		Animal a1 = new Animal("Papagaio");
		Animal a2 = new Animal("Coruja");
		Animal a3 = new Animal("Cachorro");

		ArrayList<String> animais = new ArrayList<>();
		animais.add(a1.getNome());
		animais.add(a2.getNome());
		animais.add(a3.getNome());

		return animais;
	}

	private ArrayList<String> gerarFrutas() {
		Fruta a1 = new Fruta("Laranja");
		Fruta a2 = new Fruta("Tomate");
		Fruta a3 = new Fruta("Abacaxi");

		ArrayList<String> frutas = new ArrayList<>();
		frutas.add(a1.getNome());
		frutas.add(a2.getNome());
		frutas.add(a3.getNome());

		return frutas;
	}

	public String getTipoSelecionado() {
		return tipoSelecionado;
	}

	public void setTipoSelecionado(String tipoSelecionado) {
		this.tipoSelecionado = tipoSelecionado;
	}

	public ArrayList<String> getLista() {
		return lista;
	}

	public void setLista(ArrayList<String> lista) {
		this.lista = lista;
	}

}
