package br.com.senac.leandro.correcaominhapedidaapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

public class PrincipalActivity extends Activity {

    TextView tvTotal;
    ListView lvComanda;
    ArrayList<Item> listaPedidos;
    ArrayAdapter<Item> adapterPedidos;
    //private ProdutoBD bd;
    final int REQUEST_QR_CODE = 10;
    final int REQUEST_BAR_CODE=11;
    double total;
    Item item = null;
    MyORMLiteHelper banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        banco = MyORMLiteHelper.getInstance(this);
        tvTotal = findViewById(R.id.editTextTotal);
        lvComanda = findViewById(R.id.listViewComanda);
        try {
            montarListView();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        lvComanda.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = adapterPedidos.getItem(i);
                AlertDialog.Builder alerta = new AlertDialog.Builder(PrincipalActivity.this);
                alerta.setTitle("Excluir Item do Pedido");
                alerta.setMessage("Deseja realmente cancelar o pedido: " + item.toString());
                alerta.setNeutralButton("Fechar", null);
                alerta.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        total=total - item.getValorParcial();
                        adapterPedidos.remove(item);
                        try {
                            banco.getItemDao().delete(item);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        tvTotal.setText(" R$" + String .valueOf(total));

                    }
                });
                alerta.show();
                return true;
            }
        });

       lvComanda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               item = adapterPedidos.getItem(i);
               AlertDialog.Builder alerta = new AlertDialog.Builder(PrincipalActivity.this);
               alerta.setTitle("Adicionar mais um");
               alerta.setMessage("Deseja adicionar mais uma unidade do item: " + item.toString() + " ?");
               alerta.setNeutralButton("Fechar", null);
               alerta.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       item.setQuantidade(item.getQuantidade() + 1);
                       item.setValorParcial(item.getProduto().getValor() * item.getQuantidade());
                       total=total + item.getProduto().getValor();
                       adapterPedidos.notifyDataSetChanged();
                       try {
                           banco.getItemDao().update(item);
                       } catch (SQLException e) {
                           e.printStackTrace();
                       }
                       tvTotal.setText(" R$" + String .valueOf(total));

                   }
               });
               alerta.show();
           }
       });
    }

    private void montarListView() throws SQLException {
        listaPedidos = (ArrayList<Item>) banco.getItemDao().queryForAll();
        adapterPedidos = new ArrayAdapter<Item>(this,R.layout.list_view, listaPedidos );
        lvComanda.setAdapter(adapterPedidos);
        for(Item i:listaPedidos){
            total = total + i.getValorParcial();
        }
        tvTotal.setText(" R$" + String .valueOf(total));
    }


    public void addProduto(View view) {
        Intent it = new Intent(this, AddProdutoActivity.class);
        startActivityForResult(it,1);
    }

    public void limpar(View view) throws SQLException {
        listaPedidos = new ArrayList<Item>();
        adapterPedidos = new ArrayAdapter<Item>(this,R.layout.list_view, listaPedidos );
        int i = banco.deleteAllItens();
        Toast.makeText(this, ""+i, Toast.LENGTH_SHORT).show();

        total=0;
        tvTotal.setText(" R$" + String .valueOf(total));
        item = null;
        lvComanda.setAdapter(adapterPedidos);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_CANCELED){
            Toast.makeText(this, "Nada voltou!", Toast.LENGTH_SHORT).show();
        }else if(resultCode==5){
            Bundle parametros = data.getExtras();
            item = (Item) parametros.getSerializable("pPedido");
            total = total + item.getValorParcial();
            try {
                banco.getItemDao().createOrUpdate(item);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            adapterPedidos.add(item);
            tvTotal.setText(" R$" + String .valueOf(total));
            item = null;
        }else if(resultCode==RESULT_OK){
            Toast.makeText(this, "QRCode:\n" + data.getStringExtra("SCAN_RESULT"), Toast.LENGTH_SHORT).show();
            //try{
                Integer idQrCode = Integer.parseInt(data.getStringExtra("SCAN_RESULT"));
            Produto p = null;
            try {
                p = (Produto) banco.getProdutoDao().queryForId(idQrCode);
            } catch (SQLException e) {
                Toast.makeText(this, "QRCode inválido!", Toast.LENGTH_SHORT).show();
            }
            Item item = new Item(p, 1, p.getValor());

            try {
                banco.getItemDao().createOrUpdate(item);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            adapterPedidos.add(item);
                total = total + item.getValorParcial();
                tvTotal.setText(" R$" + String .valueOf(total));
                item = null;

        }

    }


    public void scanQRCode(View view) {

        try {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, REQUEST_QR_CODE);
        }catch(ActivityNotFoundException aff){
            Toast.makeText(this, "Baixe o qr code", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }


}
