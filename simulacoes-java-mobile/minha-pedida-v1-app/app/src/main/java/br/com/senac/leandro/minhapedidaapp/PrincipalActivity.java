package br.com.senac.leandro.minhapedidaapp;

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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PrincipalActivity extends Activity {

    TextView tvTotal;
    ListView lvComanda;
    ArrayList<Pedido> listaPedidos;
    ArrayAdapter<Pedido> adapterPedidos;
    private ProdutoBD bd;
    double total;
    Pedido pedido = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        bd = new ProdutoBD(this);
        bd.popularBD();
        tvTotal = findViewById(R.id.editTextTotal);
        lvComanda = findViewById(R.id.listViewComanda);
        montarListView();

        lvComanda.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                pedido = adapterPedidos.getItem(i);
                AlertDialog.Builder alerta = new AlertDialog.Builder(PrincipalActivity.this);
                alerta.setTitle("Excluir Item do Pedido");
                alerta.setMessage("Deseja realmente cancelar o pedido: " + pedido.toString());
                alerta.setNeutralButton("Fechar", null);
                alerta.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      adapterPedidos.remove(pedido);
                        total=0;
                    }
                });
                alerta.show();
                return true;
            }
        });
    }

    private void montarListView(){
        listaPedidos = new ArrayList<Pedido>();
        adapterPedidos = new ArrayAdapter<Pedido>(this,R.layout.list_view, listaPedidos );
        lvComanda.setAdapter(adapterPedidos);
    }


    public void addProduto(View view) {
        Intent it = new Intent(this, AddProdutoActivity.class);
        startActivityForResult(it,1);
    }

    public void limpar(View view) {
        listaPedidos = new ArrayList<Pedido>();
        adapterPedidos = new ArrayAdapter<Pedido>(this,R.layout.list_view, listaPedidos );
        total=0;
        tvTotal.setText(" R$" + String .valueOf(total));
        pedido = null;
        lvComanda.setAdapter(adapterPedidos);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_CANCELED){
            Toast.makeText(this, "Nada voltou!", Toast.LENGTH_SHORT).show();
        }else{
            Bundle parametros = data.getExtras();
            pedido = (Pedido) parametros.getSerializable("pPedido");
            total = total + pedido.getValorParcial();
            adapterPedidos.add(pedido);
            //System.out.println(total);
            tvTotal.setText(" R$" + String .valueOf(total));
            pedido = null;
        }
    }
}
