package br.com.senac.leandro.correcaominhapedidaapp;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "produtos")
public class Produto implements Serializable{

    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;
    @DatabaseField(columnName = "nome", canBeNull = false, width = 100)
    private String nome;
    @DatabaseField
    private double valor;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Categoria categoria;


    public Produto() {
    }

    public Produto(String nome, double valor){
        this.nome = nome;
        this.valor = valor;
    }

    public Produto(String nome, double valor, Categoria categoria) {
        this.nome = nome;
        this.valor = valor;
        this.categoria = categoria;
    }

    public Produto(Integer id, String nome, double valor, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        try {
            return id + ": " + nome + ", R$ " + valor + ", " + categoria.getNome() + "." ;
        }catch (Exception e){
            return id + ": " + nome + ", R$ " + valor + ", sem categoria." ;
        }

    }
}
