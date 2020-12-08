package br.com.senac.leandro.minhapedidaapp;

import java.io.Serializable;

public class Produto implements Serializable {

    private String nome;
    private double valor;
    private int id;

    public Produto() {
    }

    public Produto(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return nome + ", valor unitário:R$ " + valor + "." ;
    }
}
