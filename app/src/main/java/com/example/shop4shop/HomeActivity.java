package com.example.shop4shop;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements OnSelectionListener {

    ListView listView;
    DatabaseHandler db;
    ArrayList<ProductModel> mProductList;
    CoordinatorLayout mCoordinatorLayout;
    ProductAdapter productAdapter;
    Session session;
    Button logout;
    Context mContext=HomeActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView=findViewById(R.id.listView);
        db=new DatabaseHandler(mContext);
        mProductList=new ArrayList<>();

        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        logout=findViewById(R.id.btn_logout);


        session=new Session(this);
        if(!session.loggedin()){
            logout();
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

         //   mProductList=db.getListContents();
           // productAdapter=new ProductAdapter(mContext,mProductList);
            //listView.setAdapter(productAdapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void logout() {
        session.setLoggedin(false);
        Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCartUpdateListener(long status, int position) {
        if(status>0){
            Snackbar.make(mCoordinatorLayout,"Product added to cart",Snackbar.LENGTH_LONG).show();
        }
        else{
            Snackbar.make(mCoordinatorLayout,"Product add failed",Snackbar.LENGTH_LONG).show();
        }
    }
}
