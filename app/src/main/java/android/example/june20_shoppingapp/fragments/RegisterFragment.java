package android.example.june20_shoppingapp.fragments;


import android.content.SharedPreferences;
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
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText register_user, register_pw;
    Button register_btn;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        register_user = view.findViewById(R.id.register_username);
        register_pw = view.findViewById(R.id.register_password);
        register_btn = view.findViewById(R.id.register_button);

        sharedPreferences = getActivity().getSharedPreferences("USER", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entered_username = register_user.getText().toString();
                String entered_password = register_pw.getText().toString();

                editor.putString("USERNAME", entered_username);
                editor.putString("PASSWORD",entered_password);
                editor.commit();

                Toast.makeText(getActivity(),"You successfully registered!",Toast.LENGTH_SHORT).show();
                fragmentManager=getActivity().getSupportFragmentManager();
                transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container,new LoginFragment(),null).commit();
            }
        });

        return view;
    }

}
