package br.com.senac.leandro.pesquisaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity {
    Pessoa pessoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        pessoa = new Pessoa();
    }

    public void chamarTela(View view) {
        Intent it = null;
        int requestCode = 0;
        switch (view.getId()){
            case R.id.btDadosPessoais:
                it = new Intent(this, DadosPessoaisActivity.class);
                it.putExtra("pPessoa", pessoa);
                requestCode = Constantes.REQUEST_DADOS_PESSOAIS;
                break;
            case R.id.btDadosContato:
                it = new Intent(this, DadosContatoActivity.class);
                requestCode = Constantes.REQUEST_DADOS_CONTATO;
                break;
            case R.id.btDadoEndereco:
                it = new Intent(this, DadosEnderecoActivity.class);
                requestCode = Constantes.REQUEST_DADOS_ENDERECO;
                break;
            case R.id.btVisualizar:
                it = new Intent(this, ResultadoActivity.class);
                requestCode = Constantes.REQUEST_RESULTADOS;
                it.putExtra("pPessoa", pessoa);
                break;

            default:
                Toast.makeText(this, "Não deu!", Toast.LENGTH_SHORT).show();
        }
        startActivityForResult(it, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_CANCELED){
            Toast.makeText(this, "Voltou!", Toast.LENGTH_SHORT).show();
        }else if((requestCode==Constantes.REQUEST_DADOS_PESSOAIS) && (resultCode==RESULT_OK)){
            Bundle parametros = data.getExtras();
            pessoa.setNome(parametros.getString("pNome"));
            pessoa.setIdade(parametros.getInt("pIdade"));
        }else if((requestCode==Constantes.REQUEST_DADOS_CONTATO) && (resultCode==RESULT_OK)){
            Bundle parametros = data.getExtras();
            pessoa.setTelefone(parametros.getString("pTelefone"));
            pessoa.setEmail(parametros.getString("pEmail"));
        }else if((requestCode==Constantes.REQUEST_DADOS_ENDERECO) && (resultCode==RESULT_OK)){
            Bundle parametros = data.getExtras();
            pessoa.setCep(parametros.getInt("pCEP"));
            pessoa.setCidade(parametros.getString("pCidade"));
            pessoa.setEstado(parametros.getString("pEstado"));
        }else if((requestCode==Constantes.REQUEST_RESULTADOS) && (resultCode==RESULT_OK)) {
            pessoa = new Pessoa();
        }
    }
}
