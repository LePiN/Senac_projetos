package gabriel.br.com.projetofinal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;

import gabriel.br.com.projetofinal.DAO.MyORMLiteHelper;

import gabriel.br.com.projetofinal.Entity.AdapterAutocomplete;
import gabriel.br.com.projetofinal.Entity.Shopping;
import gabriel.br.com.projetofinal.model.AdapterlistFavoritos;

public class MainActivity extends Activity {

    MyORMLiteHelper banco;
    ArrayList<Shopping> listaShopping;
    AdapterAutocomplete adapterShoppings;
    AutoCompleteTextView textView;
    ArrayList<Shopping> shoppingFavoritos;
    ListView listShoppingsFavoritos;
    AdapterlistFavoritos adapterShoppingFavoritos;
    Shopping s = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banco = MyORMLiteHelper.getInstance(this);
        shoppingFavoritos = new ArrayList<>();



        // list view dos shoppings favoritos populando
        try {
            shoppingFavoritos = (ArrayList<Shopping>) banco.getShoppingDAO().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        try {
//           popularShopping();
//        } catch (SQLException e) {'
//           e.printStackTrace();
//       }

        try {
            listaShopping = (ArrayList<Shopping>) banco.getShoppingDAO().queryForAll();
            if (listaShopping == null) {
                listaShopping = (ArrayList<Shopping>) banco.getShoppingDAO().queryForAll();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        adapterShoppings = new AdapterAutocomplete(this, listaShopping);
        adapterShoppings.setShoppingFavoritos(shoppingFavoritos);
        textView = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        textView.setAdapter(adapterShoppings);

        listShoppingsFavoritos = findViewById(R.id.listFavoritos);

        adapterShoppingFavoritos = new AdapterlistFavoritos(this, shoppingFavoritos);
        listShoppingsFavoritos.setAdapter(adapterShoppingFavoritos);

        listShoppingsFavoritos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                s = adapterShoppingFavoritos.getItem(position);
                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                alerta.setTitle("Visualizando Shopping");
                alerta.setMessage(s.toString());
                alerta.setNeutralButton("fechar", null);
                alerta.setPositiveButton("Visitar Shopping", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(i);
                    }
                });
                alerta.setNeutralButton("remover favorito", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            banco.getShoppingDAO().delete(s);
                            adapterShoppingFavoritos.remove(s);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
                alerta.show();
                return false;
            }
        });

//        listShoppingsFavoritos.setOnLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                return false;
//            }
//        });
//    }


        }







    public void teste(View v){
        textView.setText(String.valueOf(v.getTag()));
        textView.dismissDropDown();
    }

    public void popularShopping() throws SQLException {
        Shopping shop1 = new Shopping("Shopping itaguaçu");
        Shopping shop2 = new Shopping("Shopping Beiramar");
        banco.getShoppingDAO().create(shop1);
        banco.getShoppingDAO().create(shop2);
    }


}




