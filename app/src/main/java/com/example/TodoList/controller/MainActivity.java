package com.example.TodoList.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.TodoList.R;


public class MainActivity extends AppCompatActivity {
   Button SignIn,SignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignIn=findViewById(R.id.Login);
        SignUp=findViewById(R.id.SignUp);
        SignIn.setOnClickListener(view -> {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        });
        SignUp.setOnClickListener(view -> {
            Intent intent = new Intent(this,SignupActivity.class);
            startActivity(intent);
        });
    }
}