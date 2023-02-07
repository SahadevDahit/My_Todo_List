package com.example.TodoList.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.TodoList.R;
import com.example.TodoList.database.UsersDb;

public class LoginActivity extends AppCompatActivity {
    private EditText txtUsername,txtPassword;
    private Button btnSignIn;
    UsersDb db;
    SharedPreferences sp;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db=new UsersDb(this);
        txtUsername=findViewById(R.id.Username);
        txtPassword=findViewById(R.id.Password);
        btnSignIn=findViewById(R.id.SignIn);
        sp=getSharedPreferences("props",MODE_PRIVATE);
        btnSignIn.setOnClickListener(view -> {
            SignIn();
        });
    }


    private void SignIn() {
        String result=null;
         result=db.SignIn(txtUsername.getText().toString(),txtPassword.getText().toString());
        if(result!=null){
            ed=sp.edit();
            ed.putString("userID",result);
            ed.apply();
            Intent intent = new Intent(this,CustomListViewActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Unable to signin",Toast.LENGTH_SHORT).show();

        }
    }
}
