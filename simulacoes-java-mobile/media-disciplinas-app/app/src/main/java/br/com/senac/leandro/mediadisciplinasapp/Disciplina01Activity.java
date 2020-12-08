package br.com.senac.leandro.mediadisciplinasapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class Disciplina01Activity extends Activity {

    EditText etNomeDisciplina;
    NumberPicker npN1, npN2, npN3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplina01);
        etNomeDisciplina = findViewById(R.id.editTextDisciplina01);
        npN1 = findViewById(R.id.numberPickerD1Nota1);
        npN2 = findViewById(R.id.numberPickerD1Nota2);
        npN3 = findViewById(R.id.numberPickerD1Nota3);

        npN1.setMinValue(0);
        npN1.setMaxValue(10);
        npN1.setValue(6);

        npN2.setMinValue(0);
        npN2.setMaxValue(10);
        npN2.setValue(6);

        npN3.setMinValue(0);
        npN3.setMaxValue(10);
        npN3.setValue(6);
    }

    public void enviar(View v){

        Disciplina d1 = new Disciplina(etNomeDisciplina.getText().toString(), npN1.getValue(), npN2.getValue(), npN3.getValue());
        //System.out.println(d1.toString());
        Intent it = new Intent(this, Disciplina02Activity.class);
        it.putExtra("pDisciplina01", d1);
        startActivity(it);
        etNomeDisciplina.setText("");
        npN1.setValue(6);
        npN2.setValue(6);
        npN3.setValue(6);
    }
}
