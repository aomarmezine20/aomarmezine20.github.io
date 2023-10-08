package com.example.cs50;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog ;

public class signupActivity2 extends AppCompatActivity {
    TextView already_signin;
    EditText username,email,password;
    Button signup;
    DBapp my_DB;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        username=findViewById(R.id.editName);
        email = findViewById(R.id.editEmail);
        password=findViewById(R.id.editPassword);
        signup=findViewById(R.id.buttonSignup);
        my_DB = new DBapp(this);

        Register_user();

        //make listener for text already sign in
        already_signin =findViewById(R.id.textView_alreadysign);
        already_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(signupActivity2.this,loginActivity2.class);
                startActivity(intent);
            }
        });
    }

    private void Register_user() {

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Email=email.getText().toString();
                String pass=password.getText().toString();
                String user=username.getText().toString();
                Cursor res2 = my_DB.signup_user(Email,user);

                 if(!isEmailValid(Email)){
                     new SweetAlertDialog( signupActivity2.this, SweetAlertDialog.ERROR_TYPE)
                             .setTitleText("Message")
                             .setContentText("Is Not Valid Email try Again")
                             .setConfirmText("Oops...").show();
                }
                else if(!isPasswordvalid(pass)){

                     new SweetAlertDialog( signupActivity2.this, SweetAlertDialog.ERROR_TYPE)
                             .setTitleText("Message")
                             .setContentText("Too Small password length !!!")
                             .setConfirmText("Oops...").show();
                }
                else if(Email.isEmpty()){

                     new SweetAlertDialog( signupActivity2.this, SweetAlertDialog.ERROR_TYPE)
                             .setTitleText("Message")
                             .setContentText("Mail field Required !!")
                             .setConfirmText("Oops...").show();
                }
                else if(pass.isEmpty()) {

                     new SweetAlertDialog( signupActivity2.this, SweetAlertDialog.ERROR_TYPE)
                             .setTitleText("Message")
                             .setContentText("Password Field Required !!")
                             .setConfirmText("Oops...").show();
                 }

                else if(res2.getCount()> 0){

                     new SweetAlertDialog( signupActivity2.this, SweetAlertDialog.ERROR_TYPE)
                             .setTitleText("Message")
                             .setContentText("Account already exist !!")
                             .setConfirmText("Oops...").show();

                }
                else {
                     boolean isInserted= my_DB.insertData(Email,pass,user);
                     new SweetAlertDialog( signupActivity2.this, SweetAlertDialog.SUCCESS_TYPE)
                             .setTitleText("Message")
                             .setContentText("You are Registered")
                             .setConfirmText("OK")
                             .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener(){
                                 @Override
                                 public void onClick(SweetAlertDialog sweetAlertDialog) {
                                         Intent i =new Intent( signupActivity2.this, loginActivity2.class);
                                         startActivity(i);
                             }
                             })
                     .show();
                 }




            }
        });
    }

    private boolean isEmailValid(String email){
        return email.contains("@");
    }

    private boolean isPasswordvalid(String password){
        return password.length()>6;
    }
}

