package com.example.shop4shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    EditText fullName,username,password,confirm;
    Button SignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullName=findViewById(R.id.ed_fullname);
        username=findViewById(R.id.ed_Username);
        password=findViewById(R.id.ed_Password);
        confirm=findViewById(R.id.ed_confirm);
        SignUp=findViewById(R.id.btn_signUp);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
