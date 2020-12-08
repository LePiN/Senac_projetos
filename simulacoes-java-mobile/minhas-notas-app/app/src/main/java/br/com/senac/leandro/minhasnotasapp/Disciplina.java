package br.com.senac.leandro.minhasnotasapp;

import java.io.Serializable;

public class Disciplina implements Serializable {

    private String nome;
    private String professor;
    private Avaliacao avaliacao1;
    private Avaliacao avaliacao2;

    public Disciplina() {
        Avaliacao av1 = new Avaliacao();
        Avaliacao av2 = new Avaliacao();
       this.avaliacao1 = av1;
       this.avaliacao2 = av2;
    }

    public Disciplina(String nome, String professor, Avaliacao avaliacao1, Avaliacao avalianao2) {
        this.nome = nome;
        this.professor = professor;
        this.avaliacao1 = avaliacao1;
        this.avaliacao2 = avalianao2;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public Avaliacao getAvaliacao1() {
        return avaliacao1;
    }

    public void setAvaliacao1(Avaliacao avaliacao1) {
        this.avaliacao1 = avaliacao1;
    }

    public Avaliacao getAvaliacao2() {
        return avaliacao2;
    }

    public void setAvaliacao2(Avaliacao avaliacao2) {
        this.avaliacao2 = avaliacao2;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "nome='" + nome + '\'' +
                ", professor='" + professor + '\'' +
                ", avaliacao1=" + avaliacao1 +
                ", avaliacao2=" + avaliacao2 +
                '}';
    }
}
