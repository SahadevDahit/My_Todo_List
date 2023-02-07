package com.example.TodoList.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.TodoList.R;
import com.example.TodoList.database.TodoDb;

import java.util.ArrayList;
import java.util.List;

public class AddTodoActivity extends AppCompatActivity {
    TodoDb db;
    EditText Title,SubTitle,Description;
    Button AddTodo;
    SharedPreferences sp;
    public String userID;
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,CustomListViewActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        sp=getSharedPreferences("props",MODE_PRIVATE);
        if(sp.contains("userID")){
            userID=sp.getString("userID","");
            Log.i("sd",sp.getString("userID",""));
        }
        db=new TodoDb(this);
        Title=findViewById(R.id.Title);
        SubTitle=findViewById(R.id.SubTitle);
        Description=findViewById(R.id.Description);
        AddTodo=findViewById(R.id.AddTodo);
        AddTodo.setOnClickListener(View ->{
            AddTodo();
        });

    }


    public void AddTodo(){
        List<String> ToDoData=new ArrayList<String>();
        ToDoData.add(userID);
        ToDoData.add(Title.getText().toString());
        ToDoData.add(SubTitle.getText().toString());
        ToDoData.add(Description.getText().toString());
        boolean result= db.AddTodo(ToDoData);
        if(result==true){
            Toast.makeText(getApplicationContext(),"SuccessFully todo added ", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(getApplicationContext(),"Unable to  Add todo ", Toast.LENGTH_LONG).show();

        }
    }

}
