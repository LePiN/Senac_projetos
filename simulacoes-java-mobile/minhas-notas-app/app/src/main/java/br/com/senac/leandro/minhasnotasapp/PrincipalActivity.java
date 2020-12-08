package br.com.senac.leandro.minhasnotasapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PrincipalActivity extends Activity {
    Disciplina disciplina;
    Avaliacao avaliacao1, avaliacao2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        disciplina = new Disciplina();
    }

    public void direcionarTela(View view) {

        Intent it = null;
        int requestCode = 0;
        switch (view.getId()){
            case R.id.btDisciplina:
                it = new Intent(this, DadosDisciplinaActivity.class);
                requestCode = Constantes.REQUEST_DISCIPLINA;
                it.putExtra("pDisciplina", disciplina);
                break;
            case R.id.btAvaliacao1:
                it = new Intent(this, AvaliacaoActivity.class);
                requestCode = Constantes.REQUEST_AVALIACAO1;
                it.putExtra("pAvaliacao1", Constantes.REQUEST_AVALIACAO1);
                it.putExtra("pDisciplina", disciplina);
                break;
            case R.id.btAvaliacao2:
                it = new Intent(this, AvaliacaoActivity.class);
                requestCode = Constantes.REQUEST_AVALIACAO2;
                it.putExtra("pAvaliacao2", Constantes.REQUEST_AVALIACAO2);
                it.putExtra("pDisciplina", disciplina);
                break;
            case R.id.btEnviarDados:
                it = new Intent(this, ResultadoActivity.class);
                it.putExtra("pDisciplina", disciplina);
                requestCode = Constantes.REQUEST_RESULTADO;
                break;
            case R.id.btFechar:
                finish();
                break;
            default:
                Toast.makeText(this, "Algo errado, não está certo!", Toast.LENGTH_SHORT).show();
        }

        if(it!=null)
            startActivityForResult(it, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_CANCELED){
            Toast.makeText(this, "Nada foi acrescentado!", Toast.LENGTH_SHORT).show();
        }else if(resultCode==RESULT_OK){
            if(requestCode == Constantes.REQUEST_DISCIPLINA){
                Bundle parametros = data.getExtras();
                disciplina.setNome(parametros.getString("pDisciplina"));
                disciplina.setProfessor(parametros.getString("pProfessor"));
            }else if(requestCode == Constantes.REQUEST_AVALIACAO1){
                Bundle parametros = data.getExtras();
                disciplina.setAvaliacao1((Avaliacao)parametros.getSerializable("pAvaliacao"));
            }else if(requestCode == Constantes.REQUEST_AVALIACAO2){
                Bundle parametros = data.getExtras();
                disciplina.setAvaliacao2((Avaliacao)parametros.getSerializable("pAvaliacao"));
            }else if(requestCode == Constantes.REQUEST_RESULTADO){
                Bundle parametros = data.getExtras();
                disciplina = (Disciplina) parametros.getSerializable("pNovaDisciplina");
            }
        }
    }
}
