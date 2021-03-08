package com.example.inventorymanager.Section2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.inventorymanager.R;
import com.example.inventorymanager.dbhelper.dbhandler;
import com.example.inventorymanager.model.registry;
import com.example.inventorymanager.model.stock;

import java.util.ArrayList;
import java.util.List;

public class Registry extends AppCompatActivity {
    ListView registry_list;
    List<registry> registryArrayList;
    public Button clearregistry;
    public Button Statusupdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        registry_list = findViewById(R.id.registry_list);
        dbhandler mydb = new dbhandler(this);

        registryArrayList= new ArrayList<registry>();
        registryArrayList = mydb.getregistry();
        ArrayList<String> show_registry = new ArrayList<>();
        ArrayList<String> show_stock=new ArrayList<>();;

        for(registry xRegistry : registryArrayList)
        {
            ArrayList<stock> Stocklist =xRegistry.getLists();

            Log.d("bc", "IN registry file  " +Stocklist.size());

            for(stock Stock :Stocklist)
            {

                show_stock.add( "\n" + "Product Name :" + Stock.getName() + "\n" +  "Qty : " + Stock.getQuantity() + "\n" + "Price per qty : " +Stock.getPrice() +"\n");

            }
            show_registry.add("Order ID : " +xRegistry.getId1() + "\n" +"Customer Name : " +xRegistry.getCustomerName() +"\n" +show_stock+ "Time : " +xRegistry.getDatatime() +"\n" + "Totalprice : " +xRegistry.getTprice()
             + "\n" +"Payment Status : " +xRegistry.getStatus() + "\n" );
            show_stock.clear();
        }




        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,show_registry);
        registry_list.setAdapter(arrayAdapter);

        clearregistry = findViewById(R.id.clearregistryid);
        clearregistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registry.this,deleteitemRegistry.class);
                startActivity(intent);


            }
        });

        Statusupdate = findViewById(R.id.Statusupdateid);
        Statusupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registry.this ,UpdateStatus.class);
                startActivity(intent);
            }
        });
        }
    }
