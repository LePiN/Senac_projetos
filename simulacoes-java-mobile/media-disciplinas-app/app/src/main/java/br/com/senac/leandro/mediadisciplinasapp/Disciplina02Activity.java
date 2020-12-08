package br.com.senac.leandro.mediadisciplinasapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.io.Serializable;

public class Disciplina02Activity extends Activity {

    Bundle buDisciplina01;
    EditText etDisciplina02;
    NumberPicker npN1, npN2, npN3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplina02);
        buDisciplina01  = getIntent().getExtras();
        System.out.println(buDisciplina01.getSerializable("pDisciplina01"));
        etDisciplina02 = findViewById(R.id.editTextDisciplina02);
        npN1 = findViewById(R.id.numberPickerD2Nota1);
        npN2 = findViewById(R.id.numberPickerD2Nota2);
        npN3 = findViewById(R.id.numberPickerD2Nota3);

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

        Disciplina d2 = new Disciplina(etDisciplina02.getText().toString(), npN1.getValue(), npN2.getValue(), npN3.getValue());
        Disciplina d1 = (Disciplina) getIntent().getExtras().getSerializable("pDisciplina01");
        Intent it = new Intent(this, ResultadoActivity.class);
        it.putExtra("pDisciplina01",d1);
        it.putExtra("pDisciplina02", d2);
        startActivity(it);
        finish();
    }

}
