package com.example.TodoList.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.TodoList.R;
import com.example.TodoList.database.UsersDb;

import java.util.ArrayList;
import java.util.List;


public class SignupActivity extends AppCompatActivity {
    EditText FirstName,LastName,UserName,Password,ConfirmPassword;
    UsersDb db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button btnSignUp = findViewById(R.id.SignUp);
        db=new UsersDb(this);
         FirstName=findViewById(R.id.FirstName);
         LastName=findViewById(R.id.LastName);
         UserName=findViewById(R.id.UserName);
         Password=findViewById(R.id.Password);
         ConfirmPassword=findViewById(R.id.ConfirmPassword);
     btnSignUp.setOnClickListener(view -> {
         SignUpFunction();
     });
    }

    public void SignUpFunction(){
        List<String> SignUpData=new ArrayList<String>();
        SignUpData.add(FirstName.getText().toString());
        SignUpData.add(LastName.getText().toString());
        SignUpData.add(UserName.getText().toString());
        SignUpData.add(Password.getText().toString());
        SignUpData.add(ConfirmPassword.getText().toString());

        boolean result= db.AddUser(SignUpData);
        if(result==true){
            Toast.makeText(getApplicationContext(),"SuccessFully Sign Up ", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(getApplicationContext(),"Unable to  Sign Up ", Toast.LENGTH_LONG).show();

        }
    }
}
