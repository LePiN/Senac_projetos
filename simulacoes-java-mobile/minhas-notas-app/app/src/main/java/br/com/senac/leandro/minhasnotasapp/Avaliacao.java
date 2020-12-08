package br.com.senac.leandro.minhasnotasapp;

import java.io.Serializable;

public class Avaliacao implements Serializable {

    private String titulo;
    private double nota;

    public Avaliacao() {
    }

    public Avaliacao(String titulo, double nota) {
        this.titulo = titulo;
        this.nota = nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "titulo='" + titulo + '\'' +
                ", nota=" + nota +
                '}';
    }
}
