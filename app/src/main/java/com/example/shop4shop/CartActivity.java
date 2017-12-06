package com.example.shop4shop;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ListView listView;
    SharedPreferences sharedPreferences;

    CartAdapter cartAdapter;
    DatabaseHandler db;
    ArrayList<CartModel> modelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listView =findViewById(R.id.list_view_cart_page);
        db=new DatabaseHandler(this);
        sharedPreferences=getApplicationContext().getSharedPreferences("MyFile",0);
        int uid=sharedPreferences.getInt("UID",0);
        Log.d("UID","uid...."+uid);


        modelArrayList=db.getCartContents(uid);
        Log.d("Array ","arraylist"+modelArrayList.size());
        cartAdapter=new CartAdapter(modelArrayList,this);
        listView.setAdapter(cartAdapter);
    }
}
