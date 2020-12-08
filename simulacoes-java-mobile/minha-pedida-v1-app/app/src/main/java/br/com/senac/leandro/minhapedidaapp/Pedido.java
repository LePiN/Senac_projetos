package br.com.senac.leandro.minhapedidaapp;

import java.io.Serializable;

public class Pedido implements Serializable {

    private Produto produto;
    private int quantidade;
    private double valorParcial;
    private int id;

    public Pedido() {
    }

    public Pedido(Produto produto, int quantidade, double valorParcial) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorParcial = valorParcial;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorParcial() {
        return valorParcial;
    }

    public void setValorParcial(double valorParcial) {
        this.valorParcial = valorParcial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return produto.getNome() + " - valor unitário: R$ " + produto.getValor() + " - qtd: " + quantidade + " - valor parcial:R$ " + valorParcial + ".";
    }
}
