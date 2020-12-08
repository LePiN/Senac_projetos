package br.com.senac.leandro.eleitorapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ResultadoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        //Resgatar dados
        Bundle param = getIntent().getExtras();
        String nome = param.getString("pNome","");
        int idade = param.getInt("pIdade",0);
    //Resgatar layout para saída de dados
        LinearLayout layout = findViewById(R.id.layoutResultado);

        TextView tvNome = new TextView(this);
        tvNome.setText(getString(R.string.label_nome)+ ":" + nome);
        layout.addView(tvNome);

        TextView tvIdade = new TextView(this);
        tvIdade.setText(getString(R.string.label_idade) + ":" + idade);
        layout.addView(tvIdade);

        TextView tvResultado = new TextView(this);
        if(idade>= 16){
            tvResultado.setText(getString(R.string.pode_votar_questao) + getString(R.string.sim));
        }else{
            tvResultado.setText(getString(R.string.pode_votar_questao) + getString(R.string.nao));
        }
        layout.addView(tvResultado);

        Button bt = new Button(this);
        bt.setText(R.string.concluir);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ResultadoActivity.this, "Retornando",0);
                finish();
            }
        });




        layout.addView(bt);
    }
}


