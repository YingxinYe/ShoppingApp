package android.example.june20_shoppingapp.activities;

import android.content.Intent;
import android.example.june20_shoppingapp.R;
import android.example.june20_shoppingapp.fragments.ShippingFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class PayActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        fragmentManager=getSupportFragmentManager();
        transaction=fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container_pay,new ShippingFragment()).commit();
        //finish();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Payment Process");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //this activity removed from the back stack
                finish();
                break;
            case R.id.menu_search:
                break;
            case R.id.menu_cart:
                startActivity(new Intent(this, CartActivity.class));
                break;
            case R.id.menu_profile:
                break;
            case R.id.menu_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
        return true;
    }

//    @Override
//    public void onBackPressed() {
//        finish();
//        Intent i=new Intent(this, MainActivity.class);
//        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(i);
//    }
}
