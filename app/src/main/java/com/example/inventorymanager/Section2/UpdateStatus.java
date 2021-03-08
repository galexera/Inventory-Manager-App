package com.example.inventorymanager.Section2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inventorymanager.R;
import com.example.inventorymanager.dbhelper.dbhandler;

public class UpdateStatus extends AppCompatActivity {


    public EditText statusupdateid;
    public Button statuspdatebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status);
        statusupdateid = findViewById(R.id.updatestatusid);
        statuspdatebtn = findViewById(R.id.updatestatusbtn);


        statuspdatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbhandler mydb = new dbhandler(UpdateStatus.this);
                mydb.updatestatus(statusupdateid.getText().toString());
                Toast.makeText(UpdateStatus.this , statusupdateid.getText().toString() + " ID :Status Updated", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateStatus.this,Registry.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });


    }
}