package android.example.june20_shoppingapp.activities;

import android.content.Intent;
import android.example.june20_shoppingapp.R;
import android.example.june20_shoppingapp.database.DBHelper;
import android.example.june20_shoppingapp.models.Product;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView detail_name,detail_unit,detail_price;
    Button add_to_cart,buy_now;
    Toolbar toolbar;
    DBHelper dbHelper;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView=findViewById(R.id.detail_image_view);
        detail_name=findViewById(R.id.detail_product_name);
        detail_unit=findViewById(R.id.detail_product_unit);
        detail_price=findViewById(R.id.detail_product_price);
        add_to_cart=findViewById(R.id.add_to_cart_button);
        buy_now=findViewById(R.id.buy_now_cart_button);
        toolbar=findViewById(R.id.toolbar);

        dbHelper=new DBHelper(this);

        Intent intent=getIntent();
        product= (Product) intent.getSerializableExtra("DETAIL");
        Glide.with(this).load(product.getImage()).into(imageView);

        toolbar.setTitle(product.getName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detail_name.setText(product.getName());
        detail_unit.setText(product.getUnit());
        detail_price.setText(String.valueOf(product.getPrice()));


        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.addProduct(product);
                //Log.i("MyTag","You added "+product.getName());
            }
        });
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
