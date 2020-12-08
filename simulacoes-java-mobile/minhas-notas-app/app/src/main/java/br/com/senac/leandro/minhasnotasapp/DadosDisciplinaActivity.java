package br.com.senac.leandro.minhasnotasapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DadosDisciplinaActivity extends Activity {

    EditText etDisciplina, etProfessor;
    Disciplina disciplina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_disciplina);
        etDisciplina = findViewById(R.id.editDisciplina);
        etProfessor = findViewById(R.id.editProfessor);

        disciplina = (Disciplina) getIntent().getExtras().getSerializable("pDisciplina");
        etDisciplina.setText(disciplina.getNome());
        etProfessor.setText(disciplina.getProfessor());

    }

    public void salvar(View view) {
        Intent it = new Intent();
        it.putExtra("pDisciplina", etDisciplina.getText().toString());
        it.putExtra("pProfessor", etProfessor.getText().toString());
        setResult(RESULT_OK, it);
        finish();
    }

    public void cancelar(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
