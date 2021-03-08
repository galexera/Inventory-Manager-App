package com.example.inventorymanager.Section1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.inventorymanager.R;
import com.example.inventorymanager.dbhelper.dbhandler;

import java.util.List;

public class delete_item extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button delbutton ;
    private Spinner spnr;
     String del_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);
        delbutton = findViewById(R.id.delete_id);
        spnr = findViewById(R.id.spinnerdel_id);
        dbhandler mydb = new dbhandler(this);
        List<String> name_list =mydb.getallnames();

        // Creating adapter for spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,name_list);

        // Drop down layout style - list view with radio button
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spnr.setAdapter(arrayAdapter);


        delbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                del_name =spnr.getSelectedItem().toString();
                mydb.deleteitem(del_name);
                Log.d("delete", "Successfully deleted " +del_name);
                Toast.makeText(delete_item.this, "Successfully deleted : " + del_name, Toast.LENGTH_LONG).show();
                Intent var = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                startActivity(var);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
     //    del_name =spnr.getSelectedItem().toString();
        Log.d("delete", "selected " +del_name);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}