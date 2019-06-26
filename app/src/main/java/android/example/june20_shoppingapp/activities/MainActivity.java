package android.example.june20_shoppingapp.activities;

import android.content.Intent;
import android.example.june20_shoppingapp.R;
import android.example.june20_shoppingapp.adapters.ProductFragmentAdapter;
import android.example.june20_shoppingapp.models.SampleData;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);
        toolbar=findViewById(R.id.toolbar);

        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        ProductFragmentAdapter adapter=new ProductFragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(SampleData.MOBILE);
        adapter.addFragment(SampleData.LAPTOP);
        adapter.addFragment(SampleData.DESKTOP);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
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
