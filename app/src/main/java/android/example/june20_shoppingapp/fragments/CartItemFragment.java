package android.example.june20_shoppingapp.fragments;


import android.example.june20_shoppingapp.adapters.AdapterCart;
import android.example.june20_shoppingapp.database.DBHelper;
import android.example.june20_shoppingapp.models.Cart;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.june20_shoppingapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartItemFragment extends Fragment implements AdapterCart.refreshPage {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdapterCart adapterCart;
    ArrayList<Cart> mmlist;
    DBHelper dbHelper;
    int total_price_amount;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    public CartItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cart_item, container, false);
        recyclerView = view.findViewById(R.id.cart_recycle_view);

        dbHelper = new DBHelper(getContext());
        mmlist = dbHelper.readAlldata();

        layoutManager = new LinearLayoutManager(getContext());
        adapterCart = new AdapterCart(getContext(), mmlist);

        adapterCart.setClickListener(this);//initialize interface
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterCart);

        setBarPrice();

        adapterCart.notifyDataSetChanged();

        return view;
    }

    private void setBarPrice(){
        fragmentManager=getActivity().getSupportFragmentManager();
        CartBarFragment bar = new CartBarFragment();
        total_price_amount = 0;
        for (int i = 0; i < mmlist.size(); i++) {
            total_price_amount += mmlist.get(i).getQuantity() * mmlist.get(i).getProductPrice();
        }

        Bundle bundle = new Bundle();
        bundle.putString("TOTAL", String.valueOf(total_price_amount));
        bar.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.fragment_container_cart_bar,bar,null).commit();
    }

    public void refreshPageAction(View v,int position) {
        Cart cart=mmlist.get(position);

        switch (v.getId()){
            case R.id.cart_delete_item_button:
                dbHelper.deleteProduct(cart);
                mmlist.remove(position);
                adapterCart.setData(mmlist);

                if(mmlist.size()==0){
                    fragmentManager=getActivity().getSupportFragmentManager();
                    //fragmentManager.beginTransaction().replace(R.id.fragment_container_cart_item,new CartEmptyFragment()).commit();
                    for(Fragment fragment:fragmentManager.getFragments()){
                        fragmentManager.beginTransaction().remove(fragment).commit();
                    }
                    fragmentManager.beginTransaction().add(R.id.fragment_container_cart_item,new CartEmptyFragment()).commit();
                    break;
//                    Fragment fragment=fragmentManager.getFragments().get(1);
//                    fragmentManager.beginTransaction().remove(fragment).commit();
                }
                else {
                    setBarPrice();
                }
                break;

            case R.id.icon_plus:
                dbHelper.add_quantity(cart);
                cart.setQuantity(cart.getQuantity()+1);

                adapterCart.setData(mmlist);
                setBarPrice();

                break;

            case R.id.icon_minus:
                if(cart.getQuantity()>1){
                    dbHelper.minus_quantity(cart);
                    cart.setQuantity(cart.getQuantity()-1);
                    adapterCart.setData(mmlist);
                    setBarPrice();
                    break;

                }else{
                    dbHelper.deleteProduct(cart);
                    mmlist.remove(position);
                    adapterCart.setData(mmlist);

                    if(mmlist.size()==0){
                        fragmentManager=getActivity().getSupportFragmentManager();
                        //fragmentManager.beginTransaction().replace(R.id.fragment_container_cart_item,new CartEmptyFragment()).commit();
                        for(Fragment fragment:fragmentManager.getFragments()){
                            fragmentManager.beginTransaction().remove(fragment).commit();
                        }
                        fragmentManager.beginTransaction().add(R.id.fragment_container_cart_item,new CartEmptyFragment()).commit();
                        break;
//                    Fragment fragment=fragmentManager.getFragments().get(1);
//                    fragmentManager.beginTransaction().remove(fragment).commit();
                    }
                    else {
                        setBarPrice();
                        break;
                    }

                }


        }
        adapterCart.setClickListener(this);
    }

}
