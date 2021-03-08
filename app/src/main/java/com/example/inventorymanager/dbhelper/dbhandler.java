package com.example.inventorymanager.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.inventorymanager.Section2.final_cart;
import com.example.inventorymanager.model.registry;
import com.example.inventorymanager.model.stock;
import com.example.inventorymanager.params.Params;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.inventorymanager.params.Params.key_datetime;
import static com.example.inventorymanager.params.Params.key_id1;
import static com.example.inventorymanager.params.Params.key_lists;
import static com.example.inventorymanager.params.Params.key_name;
import static com.example.inventorymanager.params.Params.key_name1;
import static com.example.inventorymanager.params.Params.key_price;
import static com.example.inventorymanager.params.Params.key_quantity;
import static com.example.inventorymanager.params.Params.key_status;
import static com.example.inventorymanager.params.Params.key_tprice;
import static com.example.inventorymanager.params.Params.table_name;

public class dbhandler  extends SQLiteOpenHelper {
    public dbhandler(@Nullable Context context) {
        super(context, Params.db_name,  null, Params.db_version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.table_name + "(" +
                Params.key_id + " INTEGER PRIMARY KEY, " +
                Params.key_name + " TEXT, " +
                Params.key_quantity + " TEXT, " +
                Params.key_price + " TEXT " + ")";
                Log.d("stock", create);
                db.execSQL(create);

        String create1 ="CREATE TABLE " + Params.table1_name + "(" +
                Params.key_id1 + " INTEGER PRIMARY KEY, " +
                Params.key_name1 + " TEXT, " +
                Params.key_lists + " TEXT, " +
                Params.key_tprice + " TEXT, " +
                Params.key_datetime + " TEXT, " +
                Params.key_status + " TEXT " + ")";
        Log.d("bc", "registry table created");
                db.execSQL(create1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+Params.table_name);
        db.execSQL("DROP TABLE IF EXISTS "+Params.table1_name);
        onCreate(db);

    }

    public void addstock(stock Stock)
    {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(Params.key_name,Stock.getName());
    values.put(Params.key_quantity,Stock.getQuantity());
    values.put(Params.key_price,Stock.getPrice());
    db.insert(Params.table_name,null,values);
    Log.d("stock" +
            "db","successfully inserted");
    db.close();
     }

      public ArrayList<stock> getallstocks() {
          ArrayList<stock> stockList=new ArrayList<>();
          SQLiteDatabase db=this.getReadableDatabase();

          //query to read data from database
          String select="SELECT * FROM " + Params.table_name;
          Cursor cursor=db.rawQuery(select, null);

          // create a loop to get data
          if (cursor.moveToFirst()) {
              do {
                  stock Stock=new stock();
                  Stock.setId(Integer.parseInt(cursor.getString(0)));
                  Stock.setName(cursor.getString(1));
                  Stock.setQuantity(Integer.parseInt(cursor.getString(2)));
                  Stock.setPrice(Integer.parseInt(cursor.getString(3)));
                  stockList.add(Stock);
              } while (cursor.moveToNext());
          }
          return stockList;

      }

      public List<String> getallnames(){
        List<String> namelist = new ArrayList<String>();
        SQLiteDatabase db =  this.getReadableDatabase();

        String qry = "SELECT "+ key_name +" FROM " + table_name;
        Cursor cursor=db.rawQuery(qry, null);
        if(cursor.moveToFirst())
        {
            do {
                //stock stk = new stock();
                //namelist.add(stk.getName());
                //String name = cursor.getString(cursor.getColumnIndex(key_name));
                String name = cursor.getString(cursor.getColumnIndex(key_name));
                namelist.add(name);
            }
            while(cursor.moveToNext());
        }
          Log.d("getnames", String.valueOf(namelist));
        return namelist;
      }

      public  void deleteitem(String x){
          SQLiteDatabase db =  this.getWritableDatabase();
          db.delete(Params.table_name,Params.key_name+"=?",new String[]{x} );
          Log.d("delete",   x +" deleted ");
      }

      public void updatestock(int quantity,int price,String name)
      {
          SQLiteDatabase db = this.getWritableDatabase();
          ContentValues values = new ContentValues();
          values.put(Params.key_quantity,quantity);
          values.put(Params.key_price,price);
          db.update(Params.table_name,values,Params.key_name+"=?",new String[]{name});
          Log.d("update", "quantity and price updated ");

      }

    public int updateqty(String name,int quantity,Context context)
    {
        int flag =0 ;
        SQLiteDatabase db = this.getWritableDatabase();
        String[] projections = { key_quantity};
        String Selection = key_name+" LIKE ?";
        String[] Selection_args = {name};
        Cursor cursor =db.query(table_name,projections,Selection,Selection_args,null,null,null);
        if(cursor.moveToFirst()){

            int  pre_qty = cursor.getInt(0);
            ContentValues contentValues = new ContentValues();
            if(pre_qty>=quantity) {
                flag=1;
                contentValues.put(key_quantity, pre_qty - quantity);
                db.update(table_name,contentValues, key_name+"=?",new String[]{name});
            }


        }
        return flag;
    }

    public void addtoregistry(String name,ArrayList<stock> lists,int price,String status)
    {
        Date currentTime = Calendar.getInstance().getTime();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(key_name1,name);

        Gson gson = new Gson();
        String jsonstring = gson.toJson(lists);
        values.put(key_lists,jsonstring);

        values.put(key_tprice,price);

        values.put(key_datetime, String.valueOf(currentTime));

        values.put(key_status,status);
        db.insert(Params.table1_name,null,values);
        db.close();
        Log.d("bc", "addtoregistry: order added to registry ");
    }
    public ArrayList<registry> getregistry()
    {
        ArrayList<registry> registrylist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM " + Params.table1_name;
        Cursor cursor = db.rawQuery(select, null);
        if(cursor.moveToFirst())
        {
            do {
                registry Registry = new registry();

                Registry.setId1(Integer.parseInt(cursor.getString(0)));
                Registry.setCustomerName(cursor.getString(1));

                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<stock>>() {}.getType();
                String temp = cursor.getString(2);
                ArrayList<stock> Stocklist =gson.fromJson(temp,type);
                Registry.setLists(Stocklist);
                Log.d("bc", "getregistry: " +Stocklist.size());

                Registry.setTprice(Integer.parseInt(cursor.getString(3)));
                Registry.setDatatime(cursor.getString(4));

                Registry.setStatus(cursor.getString(5));

                registrylist.add(Registry);
            }
            while(cursor.moveToNext());
        }
        return registrylist;
    }

    public void deleteitemRegistry(String id)
    {
        SQLiteDatabase mydb = this.getWritableDatabase();
        mydb.delete(Params.table1_name,Params.key_id1+"=?",new String[]{id});

    }

    public void updatestatus(String id_update)
    {  String current= null;
       String updatedstatus_unpaid= "UnPaid";
        String updatedstatus_paid= "Paid";
       ContentValues values = new ContentValues();
        SQLiteDatabase mydb = this.getWritableDatabase();
        String read = "SELECT * FROM " + Params.table1_name;
        Cursor cursor = mydb.rawQuery(read,new String[]{});
        if (cursor != null) {
             if(cursor.moveToFirst())
            {
                do {
                     current = cursor.getString(5);
                }
                while (cursor.moveToNext());
            }
            Log.d("bc", "current status "+current);
            if(current.equals("Paid"))
            {

                Log.d("bc", "updated status "+"UnPaid");
                values.put(key_status,"UnPaid");
                mydb.update(Params.table1_name,values,key_id1+"=?",new String[]{id_update});
            }
            else
            {

                Log.d("bc", "updated status "+"Paid");
                values.put(key_status,"Paid");
                mydb.update(Params.table1_name,values,key_id1+"=?",new String[]{id_update});
            }
        }

    }

}
