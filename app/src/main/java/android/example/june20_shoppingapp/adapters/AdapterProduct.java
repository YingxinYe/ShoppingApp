package android.example.june20_shoppingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.example.june20_shoppingapp.R;
import android.example.june20_shoppingapp.activities.DetailActivity;
import android.example.june20_shoppingapp.models.Product;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.MyViewHolder> {
    ArrayList<Product> mlist;
    Context context;
    LayoutInflater inflater;

    public AdapterProduct(Context context,ArrayList<Product> list){
        this.context=context;
        mlist=list;
        inflater=LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.row_adapter_product,viewGroup,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Product product=mlist.get(i);
        myViewHolder.name.setText(product.getName());
        myViewHolder.unit.setText("Quantity: "+product.getUnit()+" pc");
        myViewHolder.price.setText("$ "+product.getPrice());

        Glide.with(context).load(product.getImage()).into(myViewHolder.image); //load(url);

        //myViewHolder.image.setImageResource(); 不用
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name,unit,price;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.product_name);
            unit=itemView.findViewById(R.id.product_unit);
            price=itemView.findViewById(R.id.product_price);
            image=itemView.findViewById(R.id.image_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Product product=mlist.get(getAdapterPosition());
            Intent intent=new Intent(context, DetailActivity.class);
            intent.putExtra("DETAIL",product);
            context.startActivity(intent);
        }
    }
}
