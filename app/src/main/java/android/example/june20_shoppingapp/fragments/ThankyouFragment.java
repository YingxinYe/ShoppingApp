package android.example.june20_shoppingapp.fragments;


import android.content.Intent;
import android.example.june20_shoppingapp.activities.CartActivity;
import android.example.june20_shoppingapp.activities.MainActivity;
import android.example.june20_shoppingapp.database.DBHelper;
import android.example.june20_shoppingapp.models.Cart;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.june20_shoppingapp.R;
import android.widget.Button;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThankyouFragment extends Fragment {
    Button button;


    public ThankyouFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_thankyou, container, false);
        button=view.findViewById(R.id.thankyou_continue_shopping_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                for(int i=0;i<mlist.size();i++){
//                    dbHelper.deleteProduct(mlist.get(i));
//                }


                getActivity().finish();
                Intent i=new Intent(getActivity(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);

            }
        });
        return view;
    }



}
