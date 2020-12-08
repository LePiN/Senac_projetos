package br.com.senac.leandro.correcaominhapedidaapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

public class GerenciarProdutoActivity extends Activity {

    EditText etNomeProduto, etValor;
    Spinner spCategorias;
    ListView lvProdutos;
    ArrayList<Categoria> listaCategorias;
    ArrayList<Produto> listaProdutos;
    ArrayAdapter<Categoria> adapterCategoria;
    AdapterProdutos adapterProduto;
    Produto produto;
    MyORMLiteHelper banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_produto);
        etNomeProduto = findViewById(R.id.editNomeProduto);
        etValor = findViewById(R.id.editValorProduto);
        spCategorias = findViewById(R.id.spinnerCategoria);
        lvProdutos = findViewById(R.id.listViewProdutos);
        lvProdutos.setOnItemClickListener(cliqueCurto());
        lvProdutos.setOnItemLongClickListener(cliqueLongo());

        banco = MyORMLiteHelper.getInstance(this);

        try {
            listaProdutos = (ArrayList<Produto>) banco.getProdutoDao().queryForAll();
            listaCategorias = (ArrayList<Categoria>) banco.getCategoriaDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        adapterProduto = new AdapterProdutos(this, listaProdutos);
        adapterCategoria = new ArrayAdapter<Categoria>(this, android.R.layout.simple_spinner_item, listaCategorias);

        lvProdutos.setAdapter(adapterProduto);
        spCategorias.setAdapter(adapterCategoria);

    }

    private AdapterView.OnItemLongClickListener cliqueLongo() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                produto = (Produto) adapterProduto.getItem(i);
                AlertDialog.Builder alerta = new AlertDialog.Builder(GerenciarProdutoActivity.this);
                alerta.setTitle("Excluindo produto");
                alerta.setIcon(android.R.drawable.ic_menu_delete);
                alerta.setMessage("Deseja excluir a produto " + produto.getNome() + " ?");
                alerta.setNegativeButton("Não", null);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapterProduto.remove(produto);
                        try {
                            banco.getProdutoDao().delete(produto);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        produto=null;
                    }
                });

                alerta.show();
                return true;
            }
        };
    }

    private AdapterView.OnItemClickListener cliqueCurto() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                produto = (Produto) adapterProduto.getItem(i);
                AlertDialog.Builder alerta = new AlertDialog.Builder(GerenciarProdutoActivity.this);
                alerta.setTitle("Edição de dados");
                alerta.setIcon(android.R.drawable.ic_menu_view);
                alerta.setMessage(produto.toString());
                alerta.setNeutralButton("Fechar", null);
                alerta.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        etNomeProduto.setText(produto.getNome());
                        etNomeProduto.requestFocus();
                        etValor.setText(String.valueOf(produto.getValor()));

                        if(produto.getCategoria()==null){
                            spCategorias.setSelection(0);
                        }else {

                            for (int x = 0; x < adapterCategoria.getCount(); x++) {
                                if (adapterCategoria.getItem(x).getId() == produto.getCategoria().getId()) {
                                    spCategorias.setSelection(x);
                                    break;
                                }

                            }
                        }
                    }
                });
                alerta.show();
            }
        };
    }

    public void gerenciarCategoria(View view) {
        Intent it = new Intent(this, GerenciarCategoriaActivity.class);
        startActivityForResult(it,1);
    }

    public void salvar(View view) {

        if(produto==null){
            produto = new Produto();

            produto.setNome(etNomeProduto.getText().toString());

            produto.setCategoria((Categoria) spCategorias.getSelectedItem());

            try {
                produto.setValor(Double.parseDouble(etValor.getText().toString()));
            }catch(Exception e){
                Toast.makeText(this, "Preencha o valor do produto!", Toast.LENGTH_SHORT).show();
                etValor.requestFocus();
                produto = null;
                return;
            }

            adapterProduto.add(produto);

        } else{
            produto.setNome(etNomeProduto.getText().toString());
            try {
                produto.setValor(Double.parseDouble(etValor.getText().toString()));
            }catch(Exception e){
                Toast.makeText(this, "Preencha o valor do produto!", Toast.LENGTH_SHORT).show();
                etValor.requestFocus();
                produto = null;
                return;
            }
            produto.setCategoria((Categoria) spCategorias.getSelectedItem());
            adapterProduto.notifyDataSetChanged();

        }
        try {
            Dao.CreateOrUpdateStatus res = banco.getProdutoDao().createOrUpdate(produto);
            if(res.isCreated()){
                Toast.makeText(this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            }else if(res.isUpdated()){
                Toast.makeText(this, "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        produto=null;
        etNomeProduto.setText("");
        etNomeProduto.requestFocus();
        etValor.setText("");
        atualizarCategorias();
        spCategorias.setSelection(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        atualizarCategorias();

    }

    public void fechar(View view) {
        Intent it = new Intent();
        setResult(RESULT_OK, it);
        finish();
    }

    public void filtrar(View view) {
        Categoria categoria = (Categoria) spCategorias.getSelectedItem();
        listaProdutos= categoria.getArrayListProdutos();
        adapterProduto = new AdapterProdutos(this, listaProdutos);
        lvProdutos.setAdapter(adapterProduto);
    }

    public void atualizarCategorias(){
        try {
            listaCategorias = (ArrayList<Categoria>) banco.getCategoriaDao().queryForAll();
            adapterCategoria = new ArrayAdapter<Categoria>(this, android.R.layout.simple_spinner_item, listaCategorias);
            spCategorias.setAdapter(adapterCategoria);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
