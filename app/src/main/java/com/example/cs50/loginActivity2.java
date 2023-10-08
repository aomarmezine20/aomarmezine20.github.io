package com.example.cs50;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity2 extends AppCompatActivity {
    TextView dont_have_account;
    EditText email,password;
    Button signin;
    DBapp my_DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        // This Line hides the action bar


        email=findViewById(R.id.editEmail);
        password=findViewById(R.id.editPassword);
        signin=findViewById(R.id.buttonLogin);

        my_DB = new DBapp(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemp_login();
            }
        });

        //make listener for text dont have account
        dont_have_account=findViewById(R.id.textview_dont_have_account);
        dont_have_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(loginActivity2.this,signupActivity2.class);
                startActivity(intent);
            }
        });
    }

    private void attemp_login() {
        String Email=email.getText().toString();
        String pass=password.getText().toString();
        Cursor res1 = my_DB.login_user(Email,pass);
        if(!isEmailValid(Email)){
            Toast.makeText( this, "Email Invalid", Toast.LENGTH_SHORT).show();
        }
        else if(!isPasswordValid(pass)){
            Toast.makeText(  this, "Password Invalid", Toast.LENGTH_SHORT).show();
        }

        else if (res1.getCount()> 0){
            final Intent intent = new Intent(loginActivity2.this,Home.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(  this, "Account not found Please Sign up!!", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isEmailValid(String email){
        return email.contains("@");
    }

    private boolean isPasswordValid(String password){
        return password.length()>6;
    }


}