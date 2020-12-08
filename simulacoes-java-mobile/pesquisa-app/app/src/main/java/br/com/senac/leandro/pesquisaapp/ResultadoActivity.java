package br.com.senac.leandro.pesquisaapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultadoActivity extends Activity {

    LinearLayout lyResultado;
    Pessoa pessoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        Bundle parametros = getIntent().getExtras();
        pessoa = (Pessoa) parametros.getSerializable("pPessoa");

        lyResultado = findViewById(R.id.layoutResultado);

        TextView tvNome = new TextView(this);
        tvNome.setText(getString(R.string.hint_nome) + ": " + pessoa.getNome());
        lyResultado.addView(tvNome);

        TextView tvIdade = new TextView(this);
        tvIdade.setText(getString(R.string.hint_idade) + ": " + pessoa.getIdade());
        lyResultado.addView(tvIdade);

        TextView tvTelefone = new TextView(this);
        tvTelefone.setText(getString(R.string.hint_telefone) + ": " + pessoa.getTelefone());
        lyResultado.addView(tvTelefone);

        TextView tvEmail = new TextView(this);
        tvEmail.setText(getString(R.string.hint_email) + ": " + pessoa.getEmail());
        lyResultado.addView(tvEmail);

        TextView tvCEP = new TextView(this);
        tvCEP.setText(getString(R.string.hint_cep) + ": " + pessoa.getCep());
        lyResultado.addView(tvCEP);

        TextView tvCidade = new TextView(this);
        tvCidade.setText(getString(R.string.hint_cidade) + ": " + pessoa.getCidade());
        lyResultado.addView(tvCidade);

    }

    public void retornar(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
