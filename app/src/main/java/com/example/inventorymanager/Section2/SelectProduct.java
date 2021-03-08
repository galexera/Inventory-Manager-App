package com.example.inventorymanager.Section2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.inventorymanager.Adapter.RecycleviewAdapter;
import com.example.inventorymanager.Adapter.SelectProductAdapter;
import com.example.inventorymanager.R;
import com.example.inventorymanager.dbhelper.dbhandler;
import com.example.inventorymanager.model.stock;
import com.example.inventorymanager.Adapter.SelectProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class SelectProduct extends AppCompatActivity {
    public RecyclerView recyclerView1;
    public SelectProductAdapter recycleviewAdapter1;
    public ArrayList<stock>  productarraylist;
    public Button proceedTocart;

 //   public TextView cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_product);
        recyclerView1 = findViewById(R.id.recyclerviewSelectProduct_id);
        proceedTocart = findViewById(R.id.proceed_id);

        dbhandler mydb1 = new dbhandler(this);
        productarraylist = new ArrayList<>();


        ArrayList<stock> productlist = mydb1.getallstocks();
        for (stock Stock : productlist)
        {
            Log.d("stockdb","Name: productpage "+ Stock.getName()+"\n");
            productarraylist.add(Stock);
        }

        recycleviewAdapter1 = new SelectProductAdapter(SelectProduct.this,productlist);
        recyclerView1.setAdapter(recycleviewAdapter1);



    }

    public void onclick( View view)
    {
        recycleviewAdapter1.passdata();
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
                recycleviewAdapter1.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}