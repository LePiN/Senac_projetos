package br.com.senac.leandro.appboletim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity {

    EditText etNomeAluno;
    EditText etNota1;
    EditText etNota2;
    TextView tvNome, tvMedia, tvSituacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Resgatar campos
        etNomeAluno = (EditText) findViewById(R.id.editNomeAluno);
        etNota1 = (EditText) findViewById(R.id.editNota1);
        etNota2 = (EditText) findViewById(R.id.editNota2);
        tvNome = (TextView) findViewById(R.id.tvNome);
        tvMedia = (TextView) findViewById(R.id.tvMedia);
        tvSituacao = (TextView) findViewById(R.id.tvSituacao);
    }

    public void calcular(View v) {

        // Resgatar valor dos campos
        String nomeAluno = etNomeAluno.getText().toString();

        if(nomeAluno.isEmpty()){
            Toast.makeText(this, "Digite o nome!", Toast.LENGTH_SHORT).show();
            return;
        }

        double nota1;
        double nota2;

        try {
            nota1 = Double.parseDouble(etNota1.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, "Você errou a nota 1!", Toast.LENGTH_SHORT).show();
            etNota1.requestFocus();
            return;
        }

        try {
            nota2 = Double.parseDouble(etNota2.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, "Você errou a nota 2!", Toast.LENGTH_SHORT).show();
            etNota2.requestFocus();
            return;
        }

        if (nota1 > 10 || nota1 < 0 || nota2 > 10 || nota2 < 0) {
            Toast.makeText(this, "Valores absurdos, você errou!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calculo da média
        double media = (nota1 + nota2) / 2;

        // Retorno valores
        tvNome.setText(getString(R.string.label_nome_aluno) + " " + nomeAluno);
        tvMedia.setText(getString(R.string.label_media) + " " + media);

        if (media >= 7) {
            tvSituacao.setText(getString(R.string.label_situacao) + " " + getString(R.string.label_aprovado));
            // Toast.makeText(this, "Aprovado", Toast.LENGTH_SHORT).show();
        } else {
            tvSituacao.setText(getString(R.string.label_situacao) + " " + getString(R.string.label_reprovado));
            // Toast.makeText(this, "Reprovado", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpar(View v) {

        etNomeAluno.setText("");
        etNota1.setText("");
        etNota2.setText("");
        tvNome.setText("");
        tvMedia.setText("");
        tvSituacao.setText("");
    }

    public void enviar(View v){

    }
}
