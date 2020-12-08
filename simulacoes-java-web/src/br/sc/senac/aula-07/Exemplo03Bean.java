package br.sc.senac.aula07;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import br.sc.senac.aula07.entity.Animal;
import br.sc.senac.aula07.entity.Fruta;

@ManagedBean(name = "meuBeanExemplo03")
public class Exemplo03Bean implements Serializable {

	private static final long serialVersionUID = 4917779772303274710L;

	// Atributo para armazenar qual tipo foi selecionado (animais ou frutas)
	private String tipoSelecionado;
	private ArrayList<Animal> animais;
	private ArrayList<Fruta> frutas;

	public Exemplo03Bean() {
		super();
	}

	// Método que gera uma lista de acordo com o tipo selecionado
	public void gerarLista() {

		if (this.getTipoSelecionado() != null) {
			if (this.getTipoSelecionado().equals("animal")) {
				this.setAnimais(gerarAnimais());
			} else if (this.getTipoSelecionado().equals("fruta")) {
				this.setFrutas(gerarFrutas());
			}
		}
	}

	private ArrayList<Animal> gerarAnimais() {
		Animal papagaio = new Animal("Papagaio", 500, "Ave");
		Animal tainha = new Animal("Tainha", 2000, "Peixe");
		Animal cachorro = new Animal("Cachorro", 5000, "Mamífero");

		ArrayList<Animal> animais = new ArrayList<>();
		animais.add(papagaio);
		animais.add(tainha);
		animais.add(cachorro);

		return animais;
	}

	private ArrayList<Fruta> gerarFrutas() {
		Fruta laranja = new Fruta("Laranja", 50, "Azedo");
		Fruta tomate = new Fruta("Tomate", 45, "Salgado");
		Fruta uva = new Fruta("Uva", 30, "Doce");

		ArrayList<Fruta> frutas = new ArrayList<>();
		frutas.add(laranja);
		frutas.add(tomate);
		frutas.add(uva);

		return frutas;
	}

	public String getTipoSelecionado() {
		return tipoSelecionado;
	}

	public void setTipoSelecionado(String tipoSelecionado) {
		this.tipoSelecionado = tipoSelecionado;
	}

	public ArrayList<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(ArrayList<Animal> animais) {
		this.animais = animais;
	}

	public ArrayList<Fruta> getFrutas() {
		return frutas;
	}

	public void setFrutas(ArrayList<Fruta> frutas) {
		this.frutas = frutas;
	}
}
