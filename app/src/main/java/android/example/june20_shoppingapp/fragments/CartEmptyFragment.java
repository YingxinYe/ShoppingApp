package android.example.june20_shoppingapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.june20_shoppingapp.R;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartEmptyFragment extends Fragment {

    Button continue_shopping;
    public CartEmptyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cart_empty, container, false);
        continue_shopping=view.findViewById(R.id.continue_shopping);
        return view;
    }

}
