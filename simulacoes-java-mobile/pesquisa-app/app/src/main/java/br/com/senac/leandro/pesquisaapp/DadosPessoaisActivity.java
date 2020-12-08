package br.com.senac.leandro.pesquisaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DadosPessoaisActivity extends Activity {

    EditText editNome, editIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_pessoais);
        editNome = findViewById(R.id.editNome);
        editIdade = findViewById(R.id.editIdade);

        Bundle parametros = getIntent().getExtras();
        Pessoa p = (Pessoa) parametros.getSerializable("pPessoa");
        editNome.setText(p.getNome());
        editIdade.setText(String.valueOf(p.getIdade()));
    }

    public void salvar(View view) {
        Intent it = new Intent();
        it.putExtra("pNome", editNome.getText().toString());
        it.putExtra("pIdade", Integer.parseInt(editIdade.getText().toString()));
        setResult(RESULT_OK, it);
        finish();
    }

    public void retornar(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
