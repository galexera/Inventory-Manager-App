package com.example.inventorymanager.params;

import com.example.inventorymanager.model.stock;

import java.util.ArrayList;

public class Params {

    public static final int db_version=2;
    public static final String db_name ="inventory_manager";
    public static final String table_name = "stock_table";
    public static final String table1_name = "registry_table";

     // keys of table
    public static final String key_id = "id";
    public static final String key_name = "name";
    public static final String key_quantity = "quantity";
    public static final String key_price = "price";

    //keys of table2
    public static final String key_id1 = "id";
    public static final String key_name1 = "customerName";
    public static final String key_lists = "lists";
    public static final String key_tprice = "tprice";
    public static final String key_datetime = "datetime";
    public static final String key_status ="status";


}
