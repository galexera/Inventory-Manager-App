package com.example.inventorymanager.Section2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inventorymanager.R;
import com.example.inventorymanager.dbhelper.dbhandler;

public class deleteitemRegistry extends AppCompatActivity {

    EditText Editdelete;
    Button deleteitembtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleteitem_registry);
        Editdelete = (EditText)findViewById(R.id.deleteregistryinputid);

        String textid =Editdelete.getText().toString();

        deleteitembtn = findViewById(R.id.deleteitemregistry);
        deleteitembtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbhandler mydb = new dbhandler(deleteitemRegistry.this);
                Log.d("bc", "INSIDE REGISTRY " + textid);
                mydb.deleteitemRegistry(Editdelete.getText().toString());
                Toast.makeText(deleteitemRegistry.this , Editdelete.getText().toString() + " ID :Order deleted", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(deleteitemRegistry.this,Registry.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

    }
}