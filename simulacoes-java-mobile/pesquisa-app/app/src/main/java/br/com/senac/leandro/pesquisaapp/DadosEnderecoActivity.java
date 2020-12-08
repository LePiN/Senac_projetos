package br.com.senac.leandro.pesquisaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class DadosEnderecoActivity extends AppCompatActivity {

    EditText etCEP, etCidade;
    Spinner spEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_endereco);
        etCEP = findViewById(R.id.editCEP);
        etCidade = findViewById(R.id.editCidade);
        montarSpinner();
    }

    public void montarSpinner(){
        ArrayList<String> dados = new ArrayList<>();
        dados.add(new String ("SC"));
        dados.add(new String ("PR"));
        dados.add(new String ("RS"));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dados);
        spEstado = findViewById(R.id.spinnerEstado);
        spEstado.setAdapter(adapter);
    }

    public void salvar(View view) {

        Intent it = new Intent();
        it.putExtra("pCEP", Integer.parseInt(etCEP.getText().toString()));
        it.putExtra("pCidade", etCidade.getText().toString());
        it.putExtra("pEstado", spEstado.getSelectedItem().toString());
        setResult(RESULT_OK, it);
        finish();
    }

    public void retornar(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
