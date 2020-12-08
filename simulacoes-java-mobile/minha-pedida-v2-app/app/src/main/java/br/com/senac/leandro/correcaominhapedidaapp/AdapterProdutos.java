package br.com.senac.leandro.correcaominhapedidaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterProdutos extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Produto> lista;

    public AdapterProdutos(Context ctx, ArrayList<Produto> listagem){
        lista = listagem;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lista.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Produto produto = lista.get(i);
        view = inflater.inflate(R.layout.my_list_view, null);

        TextView tvValorUnitario = view.findViewById(R.id.textViewValorUnitario);
        TextView tvCategoria = view.findViewById(R.id.textViewNomeCategoria);
        TextView tvProduto = view.findViewById(R.id.textViewNomeProduto);
        TextView tvConclusao = view.findViewById(R.id.textViewConclusao);

        tvValorUnitario.setText("R$ " + String.valueOf(produto.getValor()));

        try{
            tvCategoria.setText("Categoria: " + produto.getCategoria().getNome());
        }catch (Exception e){
            tvCategoria.setText("Sem Categoria");
        }

        tvProduto.setText(produto.getNome());

        if(produto.getValor()< 10){
            tvConclusao.setText("BARATINHO!");
        }else{
            tvConclusao.setText("TÁ CARO!");
        }

        return view;
    }

    public void remove(Produto p){
        lista.remove(p);
        notifyDataSetChanged();
    }

    public void add(Produto p){
        lista.add(p);
        notifyDataSetChanged();
    }
}
