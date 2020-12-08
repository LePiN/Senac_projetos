package br.com.senac.leandro.minhapedidaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddProdutoActivity extends Activity {

    NumberPicker npQuantidade;
    Spinner spProduto;
    ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
    ArrayAdapter<Produto> adapterProdutos;
    ProdutoBD bd;
    Pedido pedido = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produto);
        npQuantidade = findViewById(R.id.numberPickerQtd);
        spProduto = findViewById(R.id.spinnerProdutos);
        bd = new ProdutoBD(this);
        listaProdutos = bd.listarTodos();
        //montarListaProdutos();
        configurarNumberPicker();
        configurarSpinner();
    }

    private void configurarNumberPicker(){
        npQuantidade.setMinValue(1);
        npQuantidade.setMaxValue(99);
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
        pedido = new Pedido(p,npQuantidade.getValue(), parcial);
        it.putExtra("pPedido", pedido);
        setResult(RESULT_OK, it);
        finish();
    }

    private void montarListaProdutos(){
        Produto refrigerante = new Produto("Refrigerante", 3.0);
        listaProdutos.add(refrigerante);
        Produto cerveja = new Produto("Cerveja", 5.0);
        listaProdutos.add(cerveja);
        Produto batataFrita = new Produto("Batata Frita", 10.0);
        listaProdutos.add(batataFrita);
        Produto agua = new Produto("Água", 2.5);
        listaProdutos.add(agua);
        Produto pastel = new Produto("Pastel", 3.5);
        listaProdutos.add(pastel);
        Produto petiscos = new Produto("Petiscos", 6.0);
        listaProdutos.add(petiscos);
    }
}
