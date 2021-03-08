package com.example.inventorymanager.Section1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inventorymanager.R;
import com.example.inventorymanager.dbhelper.dbhandler;
import com.example.inventorymanager.model.stock;

public class additem extends AppCompatActivity {
    private EditText name;
    private EditText quantity;
    private EditText price;
    private Button insert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);
        name = (EditText)findViewById(R.id.nameadd);
        quantity =(EditText)findViewById(R.id.quantityadd);
        price = (EditText)findViewById(R.id.priceadd);
        insert =(Button)findViewById(R.id.insertid);

        dbhandler mydb = new dbhandler(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stock object = new stock(name.getText().toString(),Integer.parseInt(quantity.getText().toString()),Integer.parseInt(price.getText().toString()));
                mydb.addstock(object);
                Toast.makeText(additem.this, "Successfully Inserted", Toast.LENGTH_SHORT).show();
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        if(!prefs.getBoolean("firstTime", false)) {
//            stock product1=new stock("ALMONDS AMERICAN", 5, 225);
//            mydb.addstock(product1);
//            stock product2=new stock("ALMONDS MAMRA", 3, 750);
//            mydb.addstock(product2);
//            stock product3=new stock("CASHEWS WHOLE", 10, 325);
//            mydb.addstock(product3);
//            stock product4=new stock("CASHEWS SALTED", 10, 400);
//            mydb.addstock(product4);
//            stock product5=new stock("CASHEWS TUKDA", 10, 250);
//            mydb.addstock(product5);
//            stock product6=new stock("WALNUTS PREMIUM", 10, 450);
//            mydb.addstock(product6);
//            stock product7=new stock("WALNUTS TUKDA", 10, 350);
//            mydb.addstock(product7);
//            stock product8=new stock("PISTACHIOS ROASTED", 10, 350);
//            mydb.addstock(product8);
//            stock product9=new stock("PECAN NUTS", 10, 350);
//            mydb.addstock(product9);
//
//        stock product10=new stock(" PINE NUTS", 10, 800);
//        mydb.addstock(product10);
//        stock product11=new stock("PISTACHOS PREMIUM", 10, 200);
//        mydb.addstock(product11);
//        stock product12=new stock(" HAZELNUTS", 10, 150);
//        mydb.addstock(product12);
//        stock product13=new stock("CHIRONJI(CHAROLI1)", 10, 150);
//        mydb.addstock(product13);
//        stock product14=new stock("SEEDLESS DATES", 10, 145);
//        mydb.addstock(product14);
//        stock product15=new stock("TUNISIAN(WITH SEEDS)", 10, 325);
//        mydb.addstock(product15);
//        stock product16=new stock("MEDJOUL(WITH SEEDS)", 10, 850);
//        mydb.addstock(product16);
//
//
//            SharedPreferences.Editor editor = prefs.edit();
//            editor.putBoolean("firstTime", true);
//            editor.commit();
//
//        }


    }
}