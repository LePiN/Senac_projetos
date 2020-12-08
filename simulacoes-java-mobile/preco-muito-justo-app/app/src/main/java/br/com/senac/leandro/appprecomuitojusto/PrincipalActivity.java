package br.com.senac.leandro.appprecomuitojusto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class PrincipalActivity extends AppCompatActivity {

    EditText etNome, etValor, etParcelas, etJuros;
    TextView tvNome, tvValorInicial, tvParcelas, tvValorTotal, tvJuros;
    CheckBox cbEntrada;
    double jurosUsuario;
    DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        etNome = findViewById(R.id.editNomeProduto);
        etValor = findViewById(R.id.editValor);
        etParcelas = findViewById(R.id.editParcelas);
        etJuros = findViewById(R.id.editJuros);
        tvNome = findViewById(R.id.rsNome);
        tvValorInicial = findViewById(R.id.rsValorInicial);
        tvParcelas = findViewById(R.id.rsParcelas);
        tvValorTotal = findViewById(R.id.rsValorTotal);
        tvJuros = findViewById(R.id.rsJuros);
        cbEntrada = findViewById(R.id.checkboxEntrada);
    }

    public void calcular(View v){

        String nome = etNome.getText().toString();
        if(nome.isEmpty()){
            Toast.makeText(this, "Digite o nome! ", Toast.LENGTH_SHORT).show();
            return;
        }

        double valor;
        double parcelas;
        double juros;

        try {
            valor = Double.parseDouble(etValor.getText().toString());
        }catch (Exception e){
            Toast.makeText(this, "Você errou no valor!", Toast.LENGTH_SHORT).show();
            etValor.requestFocus();
            return;
        }

        try {
            parcelas = Double.parseDouble(etParcelas.getText().toString());

        }catch (Exception e){
            Toast.makeText(this, "Você errou nas parcelas!", Toast.LENGTH_SHORT).show();
            etParcelas.requestFocus();
            return;
        }

        try {
            juros = Double.parseDouble(etJuros.getText().toString());
        }catch (Exception e){
            Toast.makeText(this, "Você errou nos juros!", Toast.LENGTH_SHORT).show();
            etJuros.requestFocus();
            return;
        }

        double valorParcelas = (valor + (valor*juros/100))/ parcelas;
        double valorTotal = valor + (valor*juros/100);
        double totalJuros = valor * juros/100;

        tvNome.setText(getString(R.string.txProduto) + " " + nome);
        tvValorInicial.setText(getString(R.string.txValorInicial) + " " + df.format(valor));
        tvParcelas.setText(getString(R.string.txValorParcelas) + " " + df.format(valorParcelas));
        tvValorTotal.setText(getString(R.string.txValorTotal) + " " + df.format(valorTotal));
        tvJuros.setText(getString(R.string.txTotalJuros)+ " " + df.format(totalJuros));
    }

    public void addJuros(View v){

        double juros;

        try {
            juros = Double.parseDouble(etJuros.getText().toString());
        }catch (Exception e){
            Toast.makeText(this, "Você errou nos juros!", Toast.LENGTH_SHORT).show();
            etJuros.requestFocus();
            return;
        }

       if(cbEntrada.isChecked()){
            jurosUsuario = juros;
            juros = juros - 5;
             if(juros<0){
                    juros = 0;
                }

            etJuros.setText(String.valueOf(juros));
        }
        if(!cbEntrada.isChecked()){

            etJuros.setText(String.valueOf(jurosUsuario));
       }
    }

    public void limpar(View v){

        etNome.setText(" ");
        etValor.setText(" ");
        etParcelas.setText(" ");
        etJuros.setText(" ");
        tvNome.setText(" ");
        tvValorInicial.setText(" ");
        tvParcelas.setText(" ");
        tvValorTotal.setText(" ");
        tvJuros.setText(" ");
        cbEntrada.setChecked(false);
    }
}
