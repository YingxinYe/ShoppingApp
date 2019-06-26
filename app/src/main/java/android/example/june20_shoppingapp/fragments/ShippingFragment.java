package android.example.june20_shoppingapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.june20_shoppingapp.R;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShippingFragment extends Fragment {
    EditText shipping_name,shipping_address,shipping_city,shipping_zip,shipping_mobile;
    Button next_btn;

    public ShippingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_shipping, container, false);
        shipping_name=view.findViewById(R.id.shipping_name);
        shipping_address=view.findViewById(R.id.shipping_address);
        shipping_city=view.findViewById(R.id.shipping_city);
        shipping_zip=view.findViewById(R.id.shipping_zip);
        shipping_mobile=view.findViewById(R.id.shipping_mobile);

        next_btn=view.findViewById(R.id.shipping_next_button);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_pay,new PaymentFragment(),null).addToBackStack(null).commit();
            }
        });

        return view;
    }

}
