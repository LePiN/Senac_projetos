package br.com.senac.leandro.mediadisciplinasapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultadoActivity extends Activity {

    Bundle parametros;
    Disciplina disciplina1;
    Disciplina disciplina2;
    LinearLayout layoutDisciplina1;
    LinearLayout layoutDisciplina2;
    LinearLayout layoutGeral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        parametros = getIntent().getExtras();
        disciplina1 = (Disciplina) parametros.getSerializable("pDisciplina01");
        disciplina2 = (Disciplina) parametros.getSerializable("pDisciplina02");
        layoutDisciplina1 = findViewById(R.id.resultadoDisciplina1);
        layoutDisciplina2 = findViewById(R.id.resultadoDisciplina2);
        layoutGeral = findViewById(R.id.resultadoGeral);

        TextView tvDisciplina01 = new TextView(this);
        tvDisciplina01.setText(getString(R.string.label_disciplina01) + ": " + disciplina1.getNomeDisciplina());
        layoutDisciplina1.addView(tvDisciplina01);

        TextView tvMediaD1 = new TextView(this);
        tvMediaD1.setText(getString(R.string.label_media) + ": " + disciplina1.calcularMedia());
        layoutDisciplina1.addView(tvMediaD1);

        TextView tvSituacaoD1 = new TextView(this);
        tvSituacaoD1.setText(getString(R.string.label_situacao) + ": " + disciplina1.verificarSituacao());
        layoutDisciplina1.addView(tvSituacaoD1);

        TextView tvDisciplina02 = new TextView(this);
        tvDisciplina02.setText(getString(R.string.label_disciplina02) + ": " + disciplina2.getNomeDisciplina());
        layoutDisciplina2.addView(tvDisciplina02);

        TextView tvMediaD2 = new TextView(this);
        tvMediaD2.setText(getString(R.string.label_media) + ": " + disciplina2.calcularMedia());
        layoutDisciplina2.addView(tvMediaD2);

        TextView tvSituacaoD2 = new TextView(this);
        tvSituacaoD2.setText(getString(R.string.label_situacao) + ": " + disciplina2.verificarSituacao());
        layoutDisciplina2.addView(tvSituacaoD2);

        TextView tvMediaGeral = new TextView(this);
        calcularMedia();
        tvMediaGeral.setText(getString(R.string.label_media_geral) + ": " + this.calcularMedia());
        layoutGeral.addView(tvMediaGeral);

        TextView tvSituacaoGeral = new TextView(this);
        verificarSituacao();
        tvSituacaoGeral.setText(getString(R.string.label_situacao_geral) + ": " + this.verificarSituacaoGeral());
        layoutGeral.addView(tvSituacaoGeral);

    }

    public double calcularMedia(){
        double mediaGeral = (disciplina1.calcularMedia() + disciplina2.calcularMedia())/2;
        return  mediaGeral;
    }

    public String verificarSituacao(){
        String situacao;
        if(this.calcularMedia()>=6){
            situacao = getString(R.string.tx_aprovado);
        }else{
            situacao = getString(R.string.tx_reprovado);
        }

        return situacao;
    }

    public String verificarSituacaoGeral(){

        String situacaoGeral;
        if((disciplina1.verificarSituacao().equals(getString(R.string.tx_reprovado)) || disciplina2.verificarSituacao().equals(getString(R.string.tx_reprovado)))){
            situacaoGeral = getString(R.string.tx_reprovado);
        }else{
            situacaoGeral = situacaoGeral = getString(R.string.tx_aprovado);
        }
        return situacaoGeral;
    }

    public void retornar(View view) {

        finish();
    }
}
