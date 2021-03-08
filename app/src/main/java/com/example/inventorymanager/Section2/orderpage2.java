package com.example.inventorymanager.Section2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.inventorymanager.R;

public class orderpage2 extends AppCompatActivity {
    private Button orderButton;
    private  Button registrybtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.section2_home);
        orderButton = (Button)findViewById(R.id.sec2_orderid);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(orderpage2.this, SelectProduct.class);
                startActivity(intent);

            }
        });

        registrybtn = findViewById(R.id.registry_id);
        registrybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(orderpage2.this, Registry.class);
                startActivity(intent);

            }
        });

    }
}