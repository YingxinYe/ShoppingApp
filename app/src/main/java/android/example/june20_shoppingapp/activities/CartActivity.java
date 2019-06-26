package android.example.june20_shoppingapp.activities;

import android.content.Intent;
import android.example.june20_shoppingapp.R;
import android.example.june20_shoppingapp.adapters.AdapterCart;
import android.example.june20_shoppingapp.database.DBHelper;
import android.example.june20_shoppingapp.fragments.CartBarFragment;
import android.example.june20_shoppingapp.fragments.CartEmptyFragment;
import android.example.june20_shoppingapp.fragments.CartItemFragment;
import android.example.june20_shoppingapp.models.Cart;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class CartActivity extends AppCompatActivity {
    int total_price_amount;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    Toolbar toolbar;
    ArrayList<Cart> mmlist;
    DBHelper dbHelper;
    AdapterCart adapterCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        toolbar = findViewById(R.id.cart_toolbar);
        toolbar.setTitle("Shopping Cart");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new DBHelper(this);
        mmlist = dbHelper.readAlldata();

        adapterCart = new AdapterCart(this, mmlist);
        adapterCart.notifyDataSetChanged();

        fragmentManager=getSupportFragmentManager();
        transaction=fragmentManager.beginTransaction();

        if(mmlist.size()==0) transaction.add(R.id.fragment_container_cart_item,new CartEmptyFragment()).commit();
        else{
            fragmentManager.beginTransaction().add(R.id.fragment_container_cart_item,new CartItemFragment()).commit();
            fragmentManager.beginTransaction().add(R.id.fragment_container_cart_bar,new CartBarFragment()).commit();
        }
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


}
