package br.r8store.activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import br.senac.sc.lucas.r8store.R;
import br.r8store.utils.BottomNavigationViewHelper;

public class NavigationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);//permite usar ate 5 itens alinhados no Bottom menu
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.main_menu_home:
                    Toast.makeText(NavigationActivity.this, "menu home", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.main_menu_avaliacoes:
                    Toast.makeText(NavigationActivity.this, "menu avaliacoes", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.main_menu_shopping:
                    Toast.makeText(NavigationActivity.this, "menu shopping", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.main_menu_ofertas:
                    Toast.makeText(NavigationActivity.this, "menu ofertas", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.main_menu_premios:
                    Toast.makeText(NavigationActivity.this, "menu premios", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };

}
