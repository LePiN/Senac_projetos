package br.com.senac.leandro.eleitorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

public class PrincipalActivity extends AppCompatActivity {

    EditText etNome;
    NumberPicker npIdade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        etNome = findViewById(R.id.editNome);
        npIdade = findViewById(R.id.npIdade);

        //Configuração number picker
        npIdade.setMinValue(0);
        npIdade.setMaxValue(150);
        npIdade.setValue(21);
    }

    public void concluir(View v){

        String nome = etNome.getText().toString();
        int idade = npIdade.getValue();

       Intent it = new Intent (this, ResultadoActivity.class);
       it.putExtra("pNome", nome);
       it.putExtra("pIdade", idade);
       startActivity(it);

    }
}
