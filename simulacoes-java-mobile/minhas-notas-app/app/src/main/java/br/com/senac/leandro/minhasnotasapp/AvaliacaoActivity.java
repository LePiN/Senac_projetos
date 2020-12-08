package br.com.senac.leandro.minhasnotasapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AvaliacaoActivity extends Activity {

    EditText etTitulo, etNota;
    LinearLayout layoutTitulo;
    TextView tituloAvaliacao;
    Avaliacao avaliacao;
    Disciplina disciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);
        etTitulo = findViewById(R.id.editTitulo);
        etNota = findViewById(R.id.editNota);
        layoutTitulo = findViewById(R.id.layoutCabecalho);
        disciplina = (Disciplina) getIntent().getExtras().getSerializable("pDisciplina");

        if(getIntent().getExtras().getInt("pAvaliacao1")==Constantes.REQUEST_AVALIACAO1){
            tituloAvaliacao = new TextView(this);
            tituloAvaliacao.setText(R.string.lb_avaliacao1);
            tituloAvaliacao.setTextSize(20);
            etTitulo.setText(disciplina.getAvaliacao1().getTitulo());
            etNota.setText(String.valueOf(disciplina.getAvaliacao1().getNota()));
        }
        if(getIntent().getExtras().getInt("pAvaliacao2")==Constantes.REQUEST_AVALIACAO2){
            tituloAvaliacao = new TextView(this);
            tituloAvaliacao.setText(R.string.lb_avaliacao2);
            tituloAvaliacao.setTextSize(20);
            etTitulo.setText(disciplina.getAvaliacao2().getTitulo());
            etNota.setText(String.valueOf(disciplina.getAvaliacao2().getNota()));
        }
        layoutTitulo.addView(tituloAvaliacao);
    }

    public void salvar(View view) {
        Intent it = new Intent();
        avaliacao = new Avaliacao();
        avaliacao.setTitulo(etTitulo.getText().toString());
        try {
            avaliacao.setNota(Double.parseDouble(etNota.getText().toString()));
        }catch (Exception e){
            Toast.makeText(this, "Digite o valor da nota", Toast.LENGTH_SHORT).show();
        }
        it.putExtra("pAvaliacao", avaliacao);
        setResult(RESULT_OK,it);
        finish();
    }

    public void cancelar(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
