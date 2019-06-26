package android.example.june20_shoppingapp.fragments;

import android.content.Context;
import android.example.june20_shoppingapp.database.DBHelper;
import android.example.june20_shoppingapp.models.Cart;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.june20_shoppingapp.R;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class PaymentFragment extends Fragment {

    RadioButton cod, creditcard, wallet;
    Button next;
    DBHelper dbHelper;
    ArrayList<Cart> mlist=new ArrayList<>();

    public PaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        cod = view.findViewById(R.id.payment_radioButton_cod);
        creditcard = view.findViewById(R.id.payment_radioButton_creditcart);
        wallet = view.findViewById(R.id.payment_radioButton_wallet);

        dbHelper=new DBHelper(getActivity());
        mlist=dbHelper.readAlldata();

        next = view.findViewById(R.id.payment_next_button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cod.isChecked()) {
                    Toast.makeText(getContext(),"You choose COD payment",Toast.LENGTH_SHORT).show();
                } else if (creditcard.isChecked()) {
                    dbHelper.dropTableProduct();

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_pay, new ThankyouFragment(), null).commit();
                }else if(wallet.isChecked()){
                    Toast.makeText(getContext(),"You choose Wallet payment",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }


}
