package com.example.shop4shop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    Button signUp;
    DatabaseHandler db;
        Session session;
        SharedPreferences sharedPreference;
        SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.ed_username);
        password = findViewById(R.id.ed_password);
        login = findViewById(R.id.btn_login);
        signUp = findViewById(R.id.btn_signup);
        db=new DatabaseHandler(this);
        session=new Session(this);
        sharedPreference=getApplicationContext().getSharedPreferences("MyFile",MODE_PRIVATE);
        editor=sharedPreference.edit();
        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserName = username.getText().toString();
                String Password = password.getText().toString();
                int Uid=db.getUserId(UserName);
                Log.d("USerid ...","id"+Uid);
                editor.putInt("UID",Uid);
                editor.apply();

                String password = db.search(UserName);


                if (password.equals(Password)) {

                    List<ProductModel> productList=new ArrayList<>();
                    productList.add(new ProductModel("Elct1",200,"Electronics"));
                    productList.add(new ProductModel("Elct2",200,"Electronics"));

                    productList.add(new ProductModel("sprt1",100,"Sports"));
                    productList.add(new ProductModel("sprt2",100,"Sports"));

                    productList.add(new ProductModel("gross1",240,"Grossary"));
                    productList.add(new ProductModel("gross2",240,"Grossary"));
                    db.addProduct(productList);
                    session.setLoggedin(true);

                    Intent intent = new Intent(LoginActivity.this, TabbedActivity.class);
                    startActivity(intent);
                    finish();

                }
                else if(UserName.equals("") && Password.equals("")){
                    Toast.makeText(LoginActivity.this,"Username and Password should not be blank...",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(LoginActivity.this, "Username and password dont match...", Toast.LENGTH_LONG).show();
                }

            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        if(session.loggedin()){
            startActivity(new Intent(LoginActivity.this,TabbedActivity.class));
            finish();
        }
    }
}
