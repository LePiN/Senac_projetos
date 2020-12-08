package gabriel.br.com.projetofinal.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import gabriel.br.com.projetofinal.Entity.Shopping;
import gabriel.br.com.projetofinal.R;

/**
 * Created by Aluno on 29/06/2018.
 */

public class AdapterlistFavoritos extends ArrayAdapter<Shopping>{
    ArrayList<Shopping> listShopping;
    LayoutInflater inflate;

    public AdapterlistFavoritos(Context ctx, ArrayList<Shopping> lista){
        super(ctx, R.layout.list_view_shoppingfavoritos, lista);
        listShopping = lista;
        inflate = LayoutInflater.from(ctx);
    }


    @Override
    public int getCount() {
            return listShopping.size();
    }

    @Override
    public Shopping getItem(int position) {
        return listShopping.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void remove(Shopping s){
        listShopping.remove(s);
        notifyDataSetChanged();
    }

    public void add(Shopping s){
        listShopping.add(s);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Shopping s = listShopping.get(position);

        convertView = inflate.inflate(R.layout.list_view_shoppingfavoritos, null);

        TextView tvNomeShopping = convertView.findViewById(R.id.tvIdNomeShopping);

        TextView tvNome = convertView.findViewById(R.id.tvIdNomeShopping);
        if(s.getNome().equals("")) {
            tvNome.setText("Sem nome");
        }else{
            tvNome.setText(s.getNome());
        }

        return convertView;
    }

}
