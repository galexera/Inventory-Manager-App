package com.example.inventorymanager.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.R;
import com.example.inventorymanager.Section2.Cart;
import com.example.inventorymanager.Section2.SelectProduct;
import com.example.inventorymanager.model.stock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SelectProductAdapter extends RecyclerView.Adapter<SelectProductAdapter.selectproductviewholder> implements Filterable {

    private static Context context;
    private static ArrayList<stock> productList;
    public static Intent intent3;
    public static ArrayList<stock> cartarraylist=new ArrayList<>();
    public static Button addtocartbtn;
    public List<stock> backup;

    public void passdata() {
        intent3=new Intent(context, Cart.class);
        intent3.putExtra("passlist", cartarraylist);
        intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent3);

    }

    public SelectProductAdapter() {
    }

    public SelectProductAdapter(Context context, ArrayList<stock> productList) {
        this.context=context;
        this.productList=productList;
        backup=new ArrayList<>(productList);


    }


    @NonNull
    @Override
    public selectproductviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.productselect_row, parent, false);
        return new selectproductviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull selectproductviewholder holder, int position) {
        stock Stock=productList.get(position);
        holder.product_name.setText(Stock.getName());
        holder.product_price.setText(String.valueOf(Stock.getPrice()) + " Rs");
        int quantity=Stock.getQuantity();
        if (quantity >= 1) {
            addtocartbtn.setEnabled(true);
            holder.product_status.setTextColor(Color.GREEN);
            holder.product_status.setText("Available");
        } else {
            holder.product_status.setTextColor(Color.RED);
            holder.product_status.setText("Out of stock");
            addtocartbtn.setEnabled(false);
            
        }

        addtocartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Item added to cart", Toast.LENGTH_SHORT).show();
                stock Stock=productList.get(position);
                cartarraylist.add(Stock);
            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public  Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {


        @Override
        protected FilterResults performFiltering(CharSequence keyword) {
            List<stock> filtereddata=new ArrayList<>();

            if (keyword.toString().isEmpty()) {
                filtereddata.addAll(backup);
            } else {
                for (stock Stock : backup) {
                    if (Stock.getName().toLowerCase().contains(keyword.toString().toLowerCase())) {
                        filtereddata.add(Stock);
                    }
                }
            }

            FilterResults results=new FilterResults();
            results.values=filtereddata;


            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            productList.clear();
            productList.addAll((ArrayList<stock>) results.values);
            notifyDataSetChanged();
        }
    };


    public static class selectproductviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView product_name;
        public TextView product_price;
        public TextView product_status;


        public selectproductviewholder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            product_name=itemView.findViewById(R.id.selectproductname_id);
            product_price=itemView.findViewById(R.id.costprice_id);
            product_status=itemView.findViewById(R.id.statusid);
            addtocartbtn=itemView.findViewById(R.id.addtocart_id);


        }

        @Override
        public void onClick(View view) {
            Log.d("click", "Clicked on selectproduct recycle  view");
            //       int postion = this.getAdapterPosition();
            //     stock Stock = productList.get(postion);

            //     cartarraylist.add(Stock);


        }
    }
}

