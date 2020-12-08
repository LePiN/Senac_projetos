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
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

public class GerenciarCategoriaActivity extends Activity {

    EditText etNomeCategoria;
    ListView lvCategorias;
    ArrayList<Categoria> listaCategorias;
    ArrayAdapter<Categoria> adapterCategorias;
    Categoria categoria = null;
    MyORMLiteHelper banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_categoria);
        etNomeCategoria = findViewById(R.id.editNomeCategoria);
        lvCategorias = findViewById(R.id.listViewCategoria);
        lvCategorias.setOnItemClickListener(cliqueCurto());
        lvCategorias.setOnItemLongClickListener(cliqueLongo());

        banco = MyORMLiteHelper.getInstance(this);
        try {
            listaCategorias = (ArrayList<Categoria>) banco.getCategoriaDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapterCategorias = new ArrayAdapter<Categoria>(this, android.R.layout.simple_list_item_1, listaCategorias);
        lvCategorias.setAdapter(adapterCategorias);



    }

    private AdapterView.OnItemClickListener cliqueCurto() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(GerenciarCategoriaActivity.this, "Clique Curto!", Toast.LENGTH_LONG).show();

                categoria = adapterCategorias.getItem(i);
                AlertDialog.Builder alerta = new AlertDialog.Builder(GerenciarCategoriaActivity.this);
                alerta.setTitle("Visualizando dados");
                alerta.setIcon(android.R.drawable.ic_menu_view);
                alerta.setMessage(categoria.toString());
                alerta.setNeutralButton("Fechar", null);
                alerta.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        etNomeCategoria.setText(categoria.getNome());
                        etNomeCategoria.requestFocus();
                    }
                });

                alerta.show();
            }
        };
    }

    private AdapterView.OnItemLongClickListener cliqueLongo() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(GerenciarCategoriaActivity.this,"Clique Longo!", Toast.LENGTH_SHORT).show();

                categoria = adapterCategorias.getItem(i);
                AlertDialog.Builder alerta = new AlertDialog.Builder(GerenciarCategoriaActivity.this);
                alerta.setTitle("Excluindo categoria");
                alerta.setIcon(android.R.drawable.ic_menu_delete);
                alerta.setMessage("Deseja excluir a categoria " + categoria.getNome() + " ?");
                alerta.setNegativeButton("Não", null);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapterCategorias.remove(categoria);
                        try {
                            banco.getCategoriaDao().delete(categoria);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        categoria=null;
                    }
                });

                alerta.show();
                return true;
            }
        };
    }

    public void salvar(View view) {

        if(categoria==null){
            categoria = new Categoria();
            categoria.setNome(etNomeCategoria.getText().toString());
            adapterCategorias.add(categoria);

               // banco.getCategoriaDao().create(categoria);

        } else{
            categoria.setNome(etNomeCategoria.getText().toString());
            adapterCategorias.notifyDataSetChanged();

               // banco.getCategoriaDao().update(categoria);

        }
        try {
            Dao.CreateOrUpdateStatus res = banco.getCategoriaDao().createOrUpdate(categoria);
            if(res.isCreated()){
                Toast.makeText(this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            }else if(res.isUpdated()){
                Toast.makeText(this, "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        categoria=null;
        etNomeCategoria.setText("");
    }

    public void fechar(View view) {
        Intent it = new Intent();
        setResult(RESULT_OK, it);
        finish();
    }
}
