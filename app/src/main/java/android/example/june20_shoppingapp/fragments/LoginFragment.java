package android.example.june20_shoppingapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.example.june20_shoppingapp.activities.LoginActivity;
import android.example.june20_shoppingapp.activities.MainActivity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.june20_shoppingapp.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment {
    TextView login_textview,login_register_remind;
    EditText login_user,login_pw;
    Button login_btn;
    String savedUsername;
    String savedPassword;
    boolean isLogin;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);

        login_textview=view.findViewById(R.id.login_login_text);
        login_user=view.findViewById(R.id.login_user_name);
        login_pw=view.findViewById(R.id.login_password);
        login_btn=view.findViewById(R.id.login_button);
        login_register_remind=view.findViewById(R.id.login_register_remind);

        isLogin=false;
        sharedPreferences=getActivity().getSharedPreferences("USER",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        login_register_remind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager=getActivity().getSupportFragmentManager();
                transaction=fragmentManager.beginTransaction();

                transaction.replace(R.id.fragment_container,new RegisterFragment(),null).addToBackStack(null).commit();
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entered_username=login_user.getText().toString();
                String entered_password=login_pw.getText().toString();


                savedUsername=sharedPreferences.getString("USERNAME",null);
                savedPassword=sharedPreferences.getString("PASSWORD",null);


                if(savedUsername==null || savedPassword==null){

                    Toast.makeText(getActivity(),"Please register first",Toast.LENGTH_SHORT).show();
                }
                else if(savedUsername.equals(entered_username) && savedPassword.equals(entered_password)){

                    isLogin=true;
                    editor.putBoolean("LOGIN",isLogin);
                    editor.commit();
                    Toast.makeText(getActivity(),"Successfully log in",Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                    //startActivity(new Intent(getActivity(), MainActivity.class));
                }else{
                    Toast.makeText(getActivity(),getString(R.string.re_enter_info),Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }


}
