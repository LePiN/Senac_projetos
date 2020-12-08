package br.com.senac.leandro.correcaominhapedidaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

public class AddProdutoActivity extends Activity {

    NumberPicker npQuantidade;
    Spinner spProduto;
    ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
    ArrayAdapter<Produto> adapterProdutos;
    MyORMLiteHelper banco;
    Item item = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produto);
        npQuantidade = findViewById(R.id.numberPickerQtd);
        spProduto = findViewById(R.id.spinnerProdutos);
        banco = MyORMLiteHelper.getInstance(this);

        try {
            listaProdutos = (ArrayList<Produto>) banco.getProdutoDao().queryForAll();
            if(listaProdutos.size()==0){
                montarListaProdutos();
                listaProdutos = (ArrayList<Produto>) banco.getProdutoDao().queryForAll();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        configurarNumberPicker();
        configurarSpinner();
    }

    private void configurarNumberPicker(){
        npQuantidade.setMinValue(1);
        npQuantidade.setMaxValue(10);
        npQuantidade.setValue(1);
    }

    private void configurarSpinner(){

        adapterProdutos = new ArrayAdapter<Produto>(this, android.R.layout.simple_spinner_dropdown_item, listaProdutos);
        spProduto.setAdapter(adapterProdutos);
    }

    public void enviar(View view) {
        Intent it = new Intent();
        Produto p = (Produto) spProduto.getSelectedItem();
        double parcial = npQuantidade.getValue()*p.getValor();
        item = new Item(p,npQuantidade.getValue(), parcial);
        it.putExtra("pPedido", item);
        setResult(5, it);
        finish();
    }

    private void montarListaProdutos(){
        Produto refrigerante = new Produto("Refrigerante", 3.0);
        //listaProdutos.add(refrigerante);
        Produto cerveja = new Produto("Cerveja", 5.0);
        //listaProdutos.add(cerveja);
        Produto batataFrita = new Produto("Batata Frita", 10.0);
        //listaProdutos.add(batataFrita);
        Produto agua = new Produto("Água", 2.5);
        //listaProdutos.add(agua);
        Produto pastel = new Produto("Pastel", 3.5);
        // listaProdutos.add(pastel);
        Produto petiscos = new Produto("Petiscos", 6.0);
        //listaProdutos.add(petiscos);

        try {
            banco.getProdutoDao().create(refrigerante);
            banco.getProdutoDao().create(cerveja);
            banco.getProdutoDao().create(batataFrita);
            banco.getProdutoDao().create(agua);
            banco.getProdutoDao().create(pastel);
            banco.getProdutoDao().create(petiscos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProduto(View view) {
        Intent it = new Intent(this, GerenciarProdutoActivity.class);
        startActivityForResult(it,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            listaProdutos = (ArrayList<Produto>) banco.getProdutoDao().queryForAll();
            adapterProdutos = new ArrayAdapter<Produto>(this, android.R.layout.simple_spinner_dropdown_item, listaProdutos);
            spProduto.setAdapter(adapterProdutos);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
