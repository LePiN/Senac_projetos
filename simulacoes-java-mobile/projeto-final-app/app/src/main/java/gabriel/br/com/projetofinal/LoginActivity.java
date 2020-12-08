package gabriel.br.com.projetofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void TelaDeCadastro(View view) {
        Intent it = new Intent(LoginActivity.this, CadastrarUsuarioActivity.class);
        startActivity(it);

    }

    public void entrar(View view) {
        Intent it = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(it);
    }
}
