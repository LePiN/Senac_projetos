package br.com.senac.leandro.correcaominhapedidaapp;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@DatabaseTable
public class Categoria implements Serializable {
    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;
    @DatabaseField
    private String nome;
    @ForeignCollectionField(eager = true)
    private Collection<Produto> listaProdutos;

    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Collection<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(Collection<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public ArrayList<Produto> getArrayListProdutos(){

        ArrayList<Produto> selecaoProdutos= new ArrayList<Produto>();

        Iterator<Produto> iterator = this.getListaProdutos().iterator();
        while (iterator.hasNext()){
            Produto p = iterator.next();
            selecaoProdutos.add(p);
        }

        return selecaoProdutos;
    }

    @Override
    public String toString() {
        try {
            return id + ": " + nome + " (" + listaProdutos.size() + ")";
        }catch (Exception e){
            return id + ": " + nome + " (0)";
        }

    }
}
