package com.example.shop4shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText fullName,username,password,confirm;
    Button SignUp;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullName=findViewById(R.id.ed_fullname);
        username=findViewById(R.id.ed_Username);
        password=findViewById(R.id.ed_Password);
        confirm=findViewById(R.id.ed_confirm);
        SignUp=findViewById(R.id.btn_signUp);
        Toolbar toolbar=findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        db=new DatabaseHandler(this);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Fname=fullName.getText().toString();
                String Uname=username.getText().toString();
                String Pswd=password.getText().toString();
                String Cpswd=confirm.getText().toString();

                if(!Pswd.equals(Cpswd)){
                    Toast.makeText(SignUpActivity.this,"Password must be same",Toast.LENGTH_LONG).show();

                }

                else if(Fname.length()!=0 && Uname.length()!=0 && Pswd.length()!=0){
                        UserModel user=new UserModel();
                        user.set_name(Fname);
                        user.set_username(Uname);
                        user.set_password(Pswd);
                        AddData(user);
                        fullName.setText("");
                        username.setText("");
                        password.setText("");
                        confirm.setText("");
                }
                else{
                    Toast.makeText(SignUpActivity.this,"All fields must be fill...",Toast.LENGTH_LONG).show();
                }
            }
        });



    }
    public void AddData(UserModel user)
    {
        boolean i=db.addData(user);
                if(i==true){
            Toast.makeText(SignUpActivity.this,"Successful...",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(SignUpActivity.this,"Unsuccessful...",Toast.LENGTH_LONG).show();
                }
    }

}
