package com.example.inventorymanager.Section2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.inventorymanager.Adapter.SelectProductAdapter;
import com.example.inventorymanager.R;
import com.example.inventorymanager.dbhelper.dbhandler;
import com.example.inventorymanager.model.stock;

import java.util.ArrayList;

public class final_cart extends AppCompatActivity {

    public ArrayList<stock> finallist;
    public String customer_name;
    public TextView name;
    public ListView showlist;
    public int cartsize;
    public TextView finalqty;
    public TextView finalprice;
    public Button finalplaceorder;
    public ToggleButton  status;
    public String paymentstatus = "UnPaid";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_cart);
        finallist = getIntent().getParcelableArrayListExtra("finalcartlist");
        customer_name = getIntent().getStringExtra("finalcusname");

            //check done
        int price =0;
        for(stock Stock :finallist) {
             price= price + Stock.getQuantity()*Stock.getPrice();
        }
        for(stock Stock :finallist) {
          //  Log.d("bc",Stock.getName() );
            Log.d("bc", Stock.getName() + Stock.getQuantity() + " ITEMS" );
        }
        Log.d("bc", "Total price " + price);
        Log.d("bc",  "Customer name : "+customer_name);
        showlist =findViewById(R.id.showlist_id);
        ArrayList<String> show = new ArrayList<>();
        for(stock Stock :finallist)
        {
            show.add("Product Name :" + Stock.getName() + "\n" +  "Qty : " + Stock.getQuantity() + "\n" + "Price per qty : " +Stock.getPrice() +"\n");
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,show);
        showlist.setAdapter(arrayAdapter);
        cartsize = finallist.size();
        finalqty = findViewById(R.id.final_qty);
        finalqty.setText("("+cartsize+" items ) :" );
        finalprice = findViewById(R.id.finalprice);
        finalprice.setText(String.valueOf(price));
        name = findViewById(R.id.finall_cusnameid);
        name.setText(customer_name);

        status = findViewById(R.id.toggleButton);

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(status.isChecked())
                {
                    paymentstatus = "Paid";

                }
                else
                {
                    paymentstatus = "UnPaid";
                }

            }
        });



        finalplaceorder = findViewById(R.id.finalplaceorder);
        int tPrice=price;
        finalplaceorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag=1;
                for(stock Stock :finallist)
                {
                    dbhandler mydb = new dbhandler(final_cart.this);
                    flag = mydb.updateqty(Stock.getName(),Stock.getQuantity(),getApplicationContext());
                }
                if(flag!=1)
                {
                    Toast.makeText(final_cart.this, " Stock insufficient", Toast.LENGTH_SHORT).show();
                }
                else {
                    dbhandler mydb = new dbhandler(final_cart.this);
                    mydb.addtoregistry(customer_name,finallist, tPrice,paymentstatus);
                    Toast.makeText(final_cart.this, "Order successfully placed", Toast.LENGTH_SHORT).show();
                    Intent i=getBaseContext().getPackageManager()
                            .getLaunchIntentForPackage(getBaseContext().getPackageName());

                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    SelectProductAdapter obj=new SelectProductAdapter();
                    obj.cartarraylist.clear();
                    finallist.clear();
                    startActivity(i);
                }
                }
        });

    }
}