package android.example.june20_shoppingapp.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.example.june20_shoppingapp.activities.LoginActivity;
import android.example.june20_shoppingapp.activities.PayActivity;
import android.example.june20_shoppingapp.adapters.AdapterCart;
import android.example.june20_shoppingapp.database.DBHelper;
import android.example.june20_shoppingapp.models.Cart;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.june20_shoppingapp.R;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartBarFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Button cart_page_buy_now_button;
    TextView total_price_text_view;
    int total_price_amount;
    ArrayList<Cart> mmlist;
    DBHelper dbHelper;
    AdapterCart adapterCart;
    SharedPreferences sharedPreferences;

    public CartBarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cart_bar, container, false);
        cart_page_buy_now_button=view.findViewById(R.id.cart_page_buy_now_button);
        total_price_text_view = view.findViewById(R.id.cart_page_total_price);

        dbHelper = new DBHelper(getContext());
        mmlist = dbHelper.readAlldata();

        if(getArguments()!=null){
            Bundle bundle=getArguments();
            String total=bundle.getString("TOTAL");
            total_price_text_view.setText(String.valueOf("Total: $ "+total));
        }

        cart_page_buy_now_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences=getActivity().getSharedPreferences("USER", Context.MODE_PRIVATE);
                boolean isLogin=sharedPreferences.getBoolean("LOGIN",false);
                if(isLogin){
                    Intent i=new Intent(getActivity(), PayActivity.class);
                    //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    getActivity().finish();
                    startActivity(i);
                }else{
                    Toast.makeText(getActivity(),"Please log in first",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }

            }
        });

        return view;
    }

}
