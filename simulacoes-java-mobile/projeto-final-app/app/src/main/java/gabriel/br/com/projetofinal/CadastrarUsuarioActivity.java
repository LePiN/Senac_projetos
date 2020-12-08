package gabriel.br.com.projetofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);
    }

    public void CadastrarUsuario(View view) {
        Intent it = new Intent(CadastrarUsuarioActivity.this, LoginActivity.class);
        startActivity(it);
    }

    public void voltar(View view) {
        Intent it = new Intent(CadastrarUsuarioActivity.this, LoginActivity.class);
        startActivity(it);

    }
}
