package br.com.senac.leandro.correcaominhapedidaapp;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable
public class Item implements Serializable {
   @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;
   @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Produto produto;
   @DatabaseField(canBeNull = false)
    private int quantidade;
   @DatabaseField (canBeNull = false)
    private double valorParcial;

    public Item() {
    }

    public Item(Produto produto, int quantidade, double valorParcial) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorParcial = valorParcial;
    }

    public Item(Produto produto, int quantidade, double valorParcial, int id) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorParcial = valorParcial;
        this.id = id;
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
