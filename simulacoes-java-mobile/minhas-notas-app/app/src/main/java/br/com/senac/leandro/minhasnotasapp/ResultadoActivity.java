package br.com.senac.leandro.minhasnotasapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ResultadoActivity extends Activity {

    TextView etDisciplina, etProfessor, etTitulo1, etTitulo2, etNota1, etNota2;
    Disciplina disciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        etDisciplina = findViewById(R.id.editResultDisciplina);
        etProfessor = findViewById(R.id.editResultProfessor);
        etTitulo1 = findViewById(R.id.editResultAvaliacao1);
        etNota1 = findViewById(R.id.editResultNota1);
        etTitulo2 = findViewById(R.id.editResultAvaliacao2);
        etNota2 = findViewById(R.id.editResultNota2);

        disciplina = (Disciplina) getIntent().getExtras().getSerializable("pDisciplina");

        etDisciplina.setText(getString(R.string.lb_disciplina) + ": " + disciplina.getNome() );
        etProfessor.setText(getString(R.string.lb_professor) + ": " + disciplina.getProfessor() );
        etTitulo1.setText(getString(R.string.lb_avaliacao1) + ": " + disciplina.getAvaliacao1().getTitulo() );
        etNota1.setText(getString(R.string.lb_nota1) + ": " + disciplina.getAvaliacao1().getNota() );
        etTitulo2.setText(getString(R.string.lb_avaliacao2) + ": " + disciplina.getAvaliacao2().getTitulo() );
        etNota2.setText(getString(R.string.lb_nota2) + ": " + disciplina.getAvaliacao2().getNota() );




    }

    public void editar(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void limpar(View view) {
        Intent it = new Intent();
        Disciplina disciplina = new Disciplina();
        it.putExtra("pNovaDisciplina", disciplina);
        setResult(RESULT_OK, it);
        finish();
    }
}
