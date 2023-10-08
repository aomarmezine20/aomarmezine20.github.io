package com.example.cs50;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find by view for two button in home interface
        Button buttonlogin =findViewById(R.id.btn_login);
        Button buttonSignup =findViewById(R.id.btn_signup);
        TextView textview_creat_account = findViewById(R.id.text_creat_aacount);

        //listener for button login to redirect you to login page
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,loginActivity2.class);
                startActivity(intent);
            }
        });

        //listener for button signup to redirect you to sign up page

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,signupActivity2.class);
                startActivity(intent);
            }
        });

        // listener for creat a accout text view to redirect you to page of sign up

        textview_creat_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,signupActivity2.class);
                startActivity(intent);
            }
        });





    }
}