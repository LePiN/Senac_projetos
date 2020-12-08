package br.com.senac.leandro.calculadoraapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity {

    EditText etN1, etN2;
    TextView tvResultado;
    LinearLayout rsLayout;
    int n1;
    int n2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        etN1 = findViewById(R.id.editN1);
        etN2 = findViewById(R.id.editN2);
        rsLayout = findViewById(R.id.layoutResultado);
    }

    private void recuperar(){

        try {
            n1 = Integer.parseInt(etN1.getText().toString());
        }catch (Exception e){
            Toast.makeText(this, "Você esqueceu o valor do campo 1!", Toast.LENGTH_SHORT).show();
            etN1.requestFocus();
            return;
        }

        try {
            n2 = Integer.parseInt(etN2.getText().toString());
        }catch (Exception e){
            Toast.makeText(this, "Você esqueceu o valor do campo 2!", Toast.LENGTH_SHORT).show();
            etN2.requestFocus();
            return;
        }

    }

    public void limpar(View v){
        etN1.setText("");
        etN2.setText("");
    }

    public void somar(View v){

        recuperar();

        int resultado = n1 + n2;

        tvResultado = new TextView(this);
        tvResultado.setText(n1 + " + " + n2 + " = " + resultado);
        tvResultado.setGravity(Gravity.CENTER_HORIZONTAL);
        rsLayout.addView(tvResultado);

        limpar(v);
    }

    public void subtrair(View v){

        recuperar();

        int resultado = n1 - n2;

        tvResultado = new TextView(this);
        tvResultado.setText(n1 + " - " + n2 + " = " + resultado);
        tvResultado.setGravity(Gravity.CENTER_HORIZONTAL);
        rsLayout.addView(tvResultado);

        limpar(v);
    }

    public void multiplicar(View v){

        recuperar();

        int resultado = n1 * n2;

        tvResultado = new TextView(this);
        tvResultado.setText(n1 + " X " + n2 + " = " + resultado);
        tvResultado.setGravity(Gravity.CENTER_HORIZONTAL);
        rsLayout.addView(tvResultado);

       limpar(v);
    }

    public void dividir (View v) {

        recuperar();

        double resultado = (double)n1 /(double)n2;

        tvResultado = new TextView(this);
        tvResultado.setText(n1 + " / " + n2 + " = " + resultado);
        tvResultado.setGravity(Gravity.CENTER_HORIZONTAL);
        rsLayout.addView(tvResultado);

        limpar(v);
    }
}
