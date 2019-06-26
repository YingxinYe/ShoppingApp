package android.example.june20_shoppingapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.example.june20_shoppingapp.R;
import android.example.june20_shoppingapp.fragments.LoginFragment;
import android.example.june20_shoppingapp.fragments.RegisterFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Toolbar toolbar;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fragmentManager=getSupportFragmentManager();
        transaction=fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container,new LoginFragment()).addToBackStack(null).commit();

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Log in");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //this activity removed from the back stack
                finish();
                break;
            case R.id.menu_search:
                break;
            case R.id.menu_cart:
                startActivity(new Intent(this,CartActivity.class));
                break;
            case R.id.menu_profile:
                break;
            case R.id.menu_login:
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
        return true;
    }
}
