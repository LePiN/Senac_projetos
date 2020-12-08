package br.com.senac.leandro.estadosapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends Activity {

    EditText etNome, etSigla;
    ListView lvResultado;
    ArrayList<Estado> listaEstados;
    ArrayAdapter<Estado> adapterEstado;
    Estado estado = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        etNome = findViewById(R.id.editNome);
        etSigla = findViewById(R.id.editSigla);
        lvResultado = findViewById(R.id.listViewResultado);
        montarListView();
    }

    private void montarListView(){

        listaEstados = new ArrayList<Estado>();
        listaEstados.add(new Estado("Santa Catarina", "SC"));
        listaEstados.add(new Estado("Paraná", "PR"));

        adapterEstado = new ArrayAdapter<Estado>(this, android.R.layout.simple_list_item_1, listaEstados);
        lvResultado.setAdapter(adapterEstado);

        lvResultado.setOnItemClickListener(cliqueCurto());

        lvResultado.setOnItemLongClickListener(cliqueLongo());


    }

    private AdapterView.OnItemLongClickListener cliqueLongo(){

        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                estado = adapterEstado.getItem(i);
                AlertDialog.Builder alerta = new AlertDialog.Builder(PrincipalActivity.this);
                alerta.setTitle("Excluindo estado");
                alerta.setMessage("Deseja realmente excluir o estado " + estado.getNome() + "?");
                alerta.setIcon(android.R.drawable.ic_menu_delete);
                alerta.setPositiveButton("Não", null);
                alerta.setNegativeButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapterEstado.remove(estado);
                        estado = null;
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

                estado = adapterEstado.getItem(i);
                AlertDialog.Builder alerta = new AlertDialog.Builder(PrincipalActivity.this);
                alerta.setTitle("Visualizando dados: ");
                alerta.setIcon(android.R.drawable.ic_menu_view);
                alerta.setMessage(estado.toString());
                alerta.setPositiveButton("Fechar", null);
                alerta.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        etNome.setText(estado.getNome());
                        etSigla.setText(estado.getSigla());
                    }
                });
                alerta.show();
            }
        };
    }

    public void salvar(View view) {

        if(estado==null){
            estado = new Estado();
            estado.setNome(etNome.getText().toString());
            estado.setSigla(etSigla.getText().toString());
            adapterEstado.add(estado);
        }else{
            estado.setNome(etNome.getText().toString());
            estado.setSigla(etSigla.getText().toString());
            adapterEstado.notifyDataSetChanged();
        }
        estado = null;
        etNome.setText("");
        etSigla.setText("");
    }
}

