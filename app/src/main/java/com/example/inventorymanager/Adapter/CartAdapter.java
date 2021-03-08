package com.example.inventorymanager.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.R;
import com.example.inventorymanager.Section2.Cart;
import com.example.inventorymanager.model.stock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.cartviewholder> {



    public Context context;
    public ArrayList<stock> cartlist;
    public Button deleteitem;
    public int totalprice ;
    int cart_quantity;
    String x;




    public CartAdapter(Context context, ArrayList<stock> cartlist)
    {
        this.context = context;
        this.cartlist = cartlist;
    }




    @NonNull
    @Override
    public CartAdapter.cartviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_row,parent,false);
        return new cartviewholder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull CartAdapter.cartviewholder holder, int position) {
        stock Stock = cartlist.get(position);
        holder.name_cart.setText(Stock.getName());
        holder.price_cart.setText(String.valueOf(Stock.getPrice()));

        holder.itemquantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    x = holder.itemquantity.getText().toString();
                    cart_quantity = Integer.parseInt(x);
                } catch (NumberFormatException nfe) {
                    // Handle the condition when str is not a number.
                }
                Stock.setQuantity(cart_quantity);
                Log.d("bc", "recler viwe after " + Stock.getName() + " " +cart_quantity);
                Log.d("bc", "FROM STOCK after "+ Stock.getQuantity());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    x = holder.itemquantity.getText().toString();
                    cart_quantity = Integer.parseInt(x);
                } catch (NumberFormatException nfe) {
                    // Handle the condition when str is not a number.
                }
                Stock.setQuantity(cart_quantity);

            }
        });
        Log.d("bc", "FROM STOCK recyler" +Stock.getName() + Stock.getQuantity());


        deleteitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                totalprice = totalprice - Stock.getPrice();
                Log.d("totalprice", "Remaining " +totalprice );
                cartlist.remove(holder.getAdapterPosition());
                Log.d("totalprice",  "cartlistitems : " +cartlist.size());

                Log.d("fucku", "       setTextoftotal(); called ");
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(),cartlist.size());

            }
        });

    }


    @Override
    public int getItemCount() {
        return cartlist.size();
    }

    public class cartviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name_cart;

        public TextView price_cart;

        public EditText  itemquantity;

        public cartviewholder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name_cart  = itemView.findViewById(R.id.cart_pname);
            itemquantity=itemView.findViewById(R.id.qty);
            price_cart =  itemView.findViewById(R.id.c_priceid);
            deleteitem = itemView.findViewById(R.id.c_delbuttonid);


            Log.d("totalprice",  "cartlistitems : " +cartlist.size());



        }

        @Override
        public void onClick(View view) {

        }
    }
    public String gettotal()
    {
        int var=0;

        for (stock x : cartlist) {
            var=var + 1;
            Log.d("totalprice", "cartviewholder: loop " + var);
            totalprice=totalprice + x.getPrice();
        }
        Log.d("totalprice ", "Available inside price " + totalprice);

        return  String.valueOf(totalprice);

    }


}
