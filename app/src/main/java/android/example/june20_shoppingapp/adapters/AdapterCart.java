package android.example.june20_shoppingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.example.june20_shoppingapp.R;
import android.example.june20_shoppingapp.activities.CartActivity;
import android.example.june20_shoppingapp.activities.DetailActivity;
import android.example.june20_shoppingapp.database.DBHelper;
import android.example.june20_shoppingapp.fragments.CartEmptyFragment;
import android.example.june20_shoppingapp.models.Cart;
import android.example.june20_shoppingapp.models.Product;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.MyViewHolder> {

    Context context;
    ArrayList<Cart> mlist;
    LayoutInflater layoutInflater;
    DBHelper dbHelper;
    refreshPage fresh_action;


    public AdapterCart(Context context,ArrayList<Cart> list){
        this.context=context;
        mlist=list;
        layoutInflater=LayoutInflater.from(context);
        dbHelper=new DBHelper(this.context); //not sure
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=layoutInflater.inflate(R.layout.row_adapter_cart,viewGroup,false);
        return new MyViewHolder(view);
    }

    public void onBindViewHolder(@NonNull AdapterCart.MyViewHolder myViewHolder, int i) {
        Cart cart=mlist.get(i);
        myViewHolder.name.setText(cart.getCartItemName());
        myViewHolder.unit.setText(" "+cart.getQuantity()+" ");
        myViewHolder.price.setText("$ "+cart.getProductPrice());

        int total_amount=0;
        total_amount+=cart.getProductPrice()*cart.getQuantity();
        myViewHolder.total_price.setText("Total: $ "+String.valueOf(total_amount));

        Glide.with(context).load(cart.getCartItemImage()).into(myViewHolder.image); //load(url);   myViewHolder.image.setImageResource(); 不用
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setData(ArrayList<Cart> mmlist) {
        mlist=mmlist;
        //adapterCart.notifyDataSetChanged();
        notifyDataSetChanged();
    }


    public interface refreshPage{
        void refreshPageAction(View v,int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,unit,price,total_price;
        ImageView image;
        Button delete_btn;
        Spinner spinner;
        ImageView minus_btn,plus_btn;

        public MyViewHolder(@NonNull View itemView)  {
            super(itemView);
            name=itemView.findViewById(R.id.cart_product_name);
            unit=itemView.findViewById(R.id.cart_product_unit);
            price=itemView.findViewById(R.id.cart_product_price);
            image=itemView.findViewById(R.id.cart_image_view);
            total_price=itemView.findViewById(R.id.product_total_price);
            delete_btn=itemView.findViewById(R.id.cart_delete_item_button);
            spinner=itemView.findViewById(R.id.cart_quantity_spinner);
            minus_btn=itemView.findViewById(R.id.icon_minus);
            plus_btn=itemView.findViewById(R.id.icon_plus);

            delete_btn.setOnClickListener(this);
            minus_btn.setOnClickListener(this);
            plus_btn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            fresh_action.refreshPageAction(v,getAdapterPosition());
        }
    }

    public void setClickListener(refreshPage fresh_action){this.fresh_action=fresh_action;}
}
