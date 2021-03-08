package com.example.inventorymanager.Section1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inventorymanager.R;
import com.example.inventorymanager.dbhelper.dbhandler;

public class Product_details extends AppCompatActivity {

    private TextView product_name;
    private TextView product_price;
    private TextView product_quantity;

    private String p_name;
    private String p_quantity;
    private String p_price;

    private Button update;
    private Button priceup;
    private Button pricedown;
    private Button quantityup;
    private Button quantitydown;

    private int temp;
    private int updated_quantity;
    private int updated_price;
    private int quantity_updated;
    private int price_updated;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_product_details);

        product_name= (TextView)findViewById(R.id.nameid);
        product_quantity= (TextView)findViewById(R.id.quantityid);
        product_price= (TextView)findViewById(R.id.priceid);
        update = (Button)findViewById(R.id.updateid);

        p_name =getIntent().getStringExtra("rname");
        p_quantity=String.valueOf(getIntent().getIntExtra("rquantity",0));
        p_price=String.valueOf(getIntent().getIntExtra("rprice",0));

        quantity_updated = Integer.parseInt(p_quantity);
        price_updated = Integer.parseInt(p_price);

        product_name.setText(p_name);
        product_quantity.setText(p_quantity);
        product_price.setText(p_price);


        priceup=findViewById(R.id.price_upid);
        pricedown=findViewById(R.id.price_downid);
        quantityup=findViewById(R.id.quantity_upid);
        quantitydown=findViewById(R.id.quantity_downid);

        priceup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp=(Integer.parseInt(product_price.getText().toString())) +1;
                product_price.setText(String.valueOf(temp));
            }
        });

        pricedown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp=(Integer.parseInt(product_price.getText().toString()));
                if (temp==0)
                {
                    Toast.makeText(Product_details.this, "INVALID", Toast.LENGTH_SHORT).show();
                }
                else {
                    temp=temp-1;
                    price_updated= temp;
                    product_price.setText(String.valueOf(temp));
                }
            }
        });

        quantityup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp=(Integer.parseInt(product_quantity.getText().toString())) + 1;
                quantity_updated=temp;
                product_quantity.setText(String.valueOf(temp));
            }
        });

        quantitydown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp=(Integer.parseInt(product_quantity.getText().toString()));
                if (temp==0)
                {
                    Toast.makeText(Product_details.this, "STOCK EMPTY, REFILL!", Toast.LENGTH_SHORT).show();
                }
                else {
                    temp=temp-1;
                    quantity_updated =temp;
                    product_quantity.setText(String.valueOf(temp));
                }

            }
        });

       // updated_quantity=Integer.parseInt((product_quantity.getText().toString()));
        //updated_price =Integer.parseInt(product_quantity.getText().toString());
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Log.d("Uquantity", String.valueOf(updated_quantity));
                //Log.d("Uprice", String.valueOf(updated_price));
                Log.d("Uquantity", String.valueOf(quantity_updated));
                Log.d("Uprice", String.valueOf(price_updated));
                dbhandler db= new dbhandler(Product_details.this);
                db.updatestock(quantity_updated,price_updated,p_name);
                Toast.makeText(Product_details.this,"Successfully Updated" ,Toast.LENGTH_SHORT).show();
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });














    }
}