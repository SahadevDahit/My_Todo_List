package com.example.TodoList.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.TodoList.R;
import com.example.TodoList.database.TodoDb;

import java.util.ArrayList;
import java.util.List;

public class UpdateDeleteActivity extends AppCompatActivity {
    TodoDb db;
    public String todoid;
    EditText Title,SubTitle,Description;
    Button UpdateTodo,DeleteTodo;
    public String userId;
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,CustomListViewActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedelete);

        Intent intent = getIntent();
        todoid = intent.getStringExtra("todoId");
        userId = intent.getStringExtra("userID");

        db=new TodoDb(this);
        Title=findViewById(R.id.Title);
        SubTitle=findViewById(R.id.SubTitle);
        Description=findViewById(R.id.Description);
        UpdateTodo=findViewById(R.id.UpdateTodo);
        DeleteTodo=findViewById(R.id.DeleteTodo);
        getTodosById();
        UpdateTodo.setOnClickListener(View ->{
            UpdateTodo();
        });
        DeleteTodo.setOnClickListener(View ->{
            DeleteTodo();
        });
    }

    private void getTodosById() {
        List<String> ToDoData= new ArrayList<>();
        ToDoData= db.getTodosById(todoid);
        Title.setText(ToDoData.get(0));
        SubTitle.setText(ToDoData.get(1));
        Description.setText(ToDoData.get(2));

    }
    private void UpdateTodo() {
        List<String> ToDoData=new ArrayList<String>();
        ToDoData.add(todoid);
        ToDoData.add(Title.getText().toString());
        ToDoData.add(SubTitle.getText().toString());
        ToDoData.add(Description.getText().toString());
         boolean result=  db.updateTodo(ToDoData);
        if(result==true){
            Toast.makeText(getApplicationContext(),"Sucess to update",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"Unable In update",Toast.LENGTH_SHORT).show();

        }

    }

    private void DeleteTodo() {
        boolean result=db.deleteTodo(todoid);
        if(result==false){
            Toast.makeText(getApplicationContext(),"Unable to delete",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"Sucess In delete",Toast.LENGTH_SHORT).show();

        }

    }


}
