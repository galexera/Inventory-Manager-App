package com.example.inventorymanager.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.Section1.Product_details;
import com.example.inventorymanager.R;
import com.example.inventorymanager.model.stock;


import java.util.ArrayList;
import java.util.List;

public class RecycleviewAdapter extends RecyclerView.Adapter<RecycleviewAdapter.stockviewholder> implements Filterable {


    public static Context context;
    public static List<stock> stockList;
    public List<stock> backup;

    public RecycleviewAdapter(Context context, List<stock> stocklist){
        this.context = context;
        this.stockList = stocklist;
        backup = new ArrayList<>(stocklist);
    }

    @NonNull
    @Override
    public stockviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.row ,parent,false);
        return new stockviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull stockviewholder holder, int position) {
        stock Stock = stockList.get(position);
        holder.stockname.setText(Stock.getName());


    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence keyword) {
            List<stock> filtereddata = new ArrayList<>();

            if(keyword.toString().isEmpty())
            {
                filtereddata.addAll(backup);
            }
            else
            {
                for(stock Stock :backup)
                {
                    if(Stock.getName().toLowerCase().contains(keyword.toString().toLowerCase()))
                    {
                        filtereddata.add(Stock);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filtereddata;



            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            stockList.clear();
            stockList.addAll((ArrayList<stock>)results.values);
            notifyDataSetChanged();
        }
    };


    public static class stockviewholder extends RecyclerView.ViewHolder implements  View.OnClickListener{


        public TextView stockname;
        public stockviewholder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            stockname=itemView.findViewById(R.id.productname_id);
        }

        @Override
        public void onClick(View view) {
            Log.d("stockdb","Clicked on recycle view");

            int postion = this.getAdapterPosition();
            stock Stock = stockList.get(postion);

            String  name = Stock.getName();
            int quantity = Stock.getQuantity();
            int price = Stock.getPrice();

            Intent intent = new Intent(context , Product_details.class);
            intent.putExtra("rname",name);
            intent.putExtra("rquantity",quantity);
            intent.putExtra("rprice",price);
            context.startActivity(intent);
        }
    }
}

