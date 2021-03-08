package com.example.inventorymanager.Section1;

import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventorymanager.Adapter.RecycleviewAdapter;
import com.example.inventorymanager.R;
import com.example.inventorymanager.dbhelper.dbhandler;
import com.example.inventorymanager.model.stock;

import java.util.ArrayList;
import java.util.List;

public class stocklist_page extends AppCompatActivity {
    public RecyclerView recyclerView;
    public RecycleviewAdapter recyclerviewAdapter;
    public ArrayList<stock> stockarraylist;

    public Button additem;
    public Button deleteitem;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stocklist);
        recyclerView=findViewById(R.id.recyclerviewid);

        additem=(Button) findViewById(R.id.addid);
        deleteitem=(Button) findViewById(R.id.deleteid);

        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(stocklist_page.this, com.example.inventorymanager.Section1.additem.class);
                startActivity(intent);
            }
        });
        deleteitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(stocklist_page.this, delete_item.class);
                startActivity(intent);
            }
        });

        dbhandler mydb=new dbhandler(this);

        stockarraylist=new ArrayList<>();

        //get all stocks
        List<stock> stocklist=mydb.getallstocks();
        for (stock Stock : stocklist) {
            Log.d("stockdb", "Name: " + Stock.getName() + "\n");
            stockarraylist.add(Stock);
        }

        recyclerviewAdapter=new RecycleviewAdapter(stocklist_page.this, stocklist);
        recyclerView.setAdapter(recyclerviewAdapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.search_menu);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                recyclerviewAdapter.getFilter().filter(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}
