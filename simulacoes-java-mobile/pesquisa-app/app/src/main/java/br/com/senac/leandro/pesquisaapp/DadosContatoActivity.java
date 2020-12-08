package br.com.senac.leandro.pesquisaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DadosContatoActivity extends Activity {
    EditText etTelefone, etEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_contato);
        etTelefone = findViewById(R.id.editTelefone);
        etEmail = findViewById(R.id.editEmail);
    }

    public void salvar(View view) {
        Intent it = new Intent();
        it.putExtra("pTelefone", etTelefone.getText().toString());
        it.putExtra("pEmail", etEmail.getText().toString());
        setResult(RESULT_OK, it);
        finish();
    }

    public void retornar(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
