package com.example.inventorymanager.Section2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inventorymanager.Adapter.CartAdapter;
import com.example.inventorymanager.R;
import com.example.inventorymanager.model.stock;
import com.example.inventorymanager.Adapter.SelectProductAdapter;

import java.util.ArrayList;

public class Cart extends AppCompatActivity  {

    //Spinner spinner;
    public EditText cart_cusname;
    public RecyclerView recyclercartView;
    public CartAdapter cartAdapterview;
    public Button PLACEORDER;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cart_cusname = findViewById(R.id.cus_nameid);
        recyclercartView = findViewById(R.id.cartrecycler);
        recyclercartView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<stock> cartlist;


        cartlist = getIntent().getParcelableArrayListExtra("passlist");
        if(cartlist==null)
        {
            Toast.makeText(this, "cartlist is empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            for(stock Stock : cartlist) {

                Log.i("bhidni", Stock.getName());
            }

        }

        cartAdapterview = new CartAdapter(Cart.this,cartlist);
        recyclercartView.setAdapter(cartAdapterview);
        
        PLACEORDER = findViewById(R.id.confirmOder_buttonid);
        PLACEORDER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Cart.this , final_cart.class);
                SelectProductAdapter obj = new SelectProductAdapter();
                Boolean ans = obj.cartarraylist.isEmpty();

                Log.d("emptycheck", "emptycheck : " +ans);
                if(ans ==false && cartlist!=null && cartlist.isEmpty()==false) {
                    //Toast.makeText(Cart.this, "Order successfully placed with cart size " +obj.cartarraylist.size(), Toast.LENGTH_SHORT).show();
                  //  obj.cartarraylist.clear();
                    intent.putExtra("finalcartlist",cartAdapterview.cartlist);
                    intent.putExtra("finalcusname",cart_cusname.getText().toString());
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Cart.this, "CART EMPTY", Toast.LENGTH_SHORT).show();
                }



                //trial

            //    Intent intent = new Intent(Cart.this , SelectProduct.class);























            }
        });




    }




}