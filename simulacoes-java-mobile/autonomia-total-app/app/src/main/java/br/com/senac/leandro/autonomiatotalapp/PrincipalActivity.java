package br.com.senac.leandro.autonomiatotalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity {

    EditText etNomeCarro, etKMPercorrida, etQtdCombustivel;
    TextView tvConsumoMedio,tvConsumoGeral;
    LinearLayout rsLayout;
    double mediaFrota;
    int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        etNomeCarro = findViewById(R.id.editNomeCarro);
        etKMPercorrida = findViewById(R.id.editKmPercorrida);
        etQtdCombustivel = findViewById(R.id.editQtdCombustivel);
        tvConsumoGeral = findViewById(R.id.txConsumoGeral);
        rsLayout = findViewById(R.id.layoutResultado);

    }

    public void calcular(View v){

        String nome = etNomeCarro.getText().toString();
        if(nome.isEmpty()){
            Toast.makeText(this, "Digite o nome!", Toast.LENGTH_SHORT).show();
            return;
        }

        double kmPercorrida, qtdCombustivel;

        try {
            kmPercorrida = Double.parseDouble(etKMPercorrida.getText().toString());
        }catch (Exception e){
            Toast.makeText(this, "Você não declarou os Km percorridos!", Toast.LENGTH_SHORT).show();
            etKMPercorrida.requestFocus();
            return;
        }

        try {
            qtdCombustivel = Double.parseDouble(etQtdCombustivel.getText().toString());
        }catch (Exception e){
            Toast.makeText(this, "Você não declarou o consumo de combustível!", Toast.LENGTH_SHORT).show();
            etQtdCombustivel.requestFocus();
            return;
        }

        double mediaConsumo = kmPercorrida / qtdCombustivel;
        contador = contador + 1;
        mediaFrota = (mediaFrota + mediaConsumo) / contador;

        tvConsumoMedio = new TextView(this);
        tvConsumoMedio.setText(nome + " - " + mediaConsumo + "Km/L");
        tvConsumoMedio.setGravity(Gravity.CENTER_HORIZONTAL);
        rsLayout.addView(tvConsumoMedio);
        tvConsumoGeral.setText(" " + String.valueOf(mediaFrota) + "Km/L");


    }

    public void limpar(View v){

        etNomeCarro.setText("");
        etKMPercorrida.setText("");
        etQtdCombustivel.setText("");

    }
}
