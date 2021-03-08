package com.example.inventorymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.inventorymanager.Section1.stocklist_page;
import com.example.inventorymanager.Section2.orderpage2;

public class MainActivity extends AppCompatActivity {

    private Button orders;

    public void openactivity(View view)
    {
        Intent intent = new Intent(MainActivity.this , stocklist_page.class);
        startActivity(intent);

    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orders=(Button)findViewById(R.id.orderhomeid);
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, orderpage2.class);
                startActivity(intent);
            }
        });




    }

}