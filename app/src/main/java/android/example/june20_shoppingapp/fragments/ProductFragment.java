package android.example.june20_shoppingapp.fragments;


import android.example.june20_shoppingapp.adapters.AdapterProduct;
import android.example.june20_shoppingapp.models.Product;
import android.example.june20_shoppingapp.models.SampleData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class ProductFragment extends Fragment {
    static String category;
    ArrayList<Product> mlist;
    AdapterProduct adapterProduct;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mlayoutManager;

    public ProductFragment() {
        // Required empty public constructor
    }

    public static ProductFragment newInstance(String title) {  //set好这个mlist
        category=title;
        ProductFragment fragment=new ProductFragment();  //what state will this fragment go?
        Log.i("MyTag","Fragment created");
        Bundle args=new Bundle();
        args.putString("cat",category);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("MyTag","OnCreateView called");
        View view= inflater.inflate(R.layout.fragment_product, container, false);
        category=getArguments().getString("cat");
        mlist= SampleData.getData(category);
        init(view);
        //Log.i("MyTag","OnCreateView done");
        return view;
    }

    private void init(View v) {
        mRecyclerView=v.findViewById(R.id.fragment_recycler_view);  //这些原本是在activity里面的，只是现在放在了fragment里面而已
        mlayoutManager=new LinearLayoutManager(getActivity());
        adapterProduct =new AdapterProduct(getActivity(),mlist);
        adapterProduct.notifyDataSetChanged();

        mRecyclerView.setLayoutManager(mlayoutManager);
        mRecyclerView.setAdapter(adapterProduct);

    }

}
