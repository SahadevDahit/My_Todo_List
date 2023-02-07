package com.example.TodoList.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.TodoList.R;
import com.example.TodoList.TodosItemActivity;
import com.example.TodoList.adapter.TodoAdapterActivity;
import com.example.TodoList.database.TodoDb;

import java.util.ArrayList;

public class CustomListViewActivity extends AppCompatActivity {
    TodoDb db;
    Button AddTodo;
     ListView list;
  private String userID;
    SharedPreferences sp;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_listview);
        sp=getSharedPreferences("props",MODE_PRIVATE);
        if(sp.contains("userID")){
            userID=sp.getString("userID","");
        }
        db=new TodoDb(this);
        AddTodo=findViewById(R.id.AddTodo);
        list = findViewById(R.id.list);
        ArrayList<TodosItemActivity> arrayoftodos = new ArrayList<TodosItemActivity>();
        arrayoftodos=  db.getTodos(userID);
        if(!arrayoftodos.isEmpty()){
            TodoAdapterActivity adapter = new TodoAdapterActivity(this, arrayoftodos);
            list.setAdapter(adapter);
        }

list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
        String id = ((TextView) view.findViewById(R.id.id)).getText().toString();
        Intent i = new Intent(getApplicationContext(), UpdateDeleteActivity.class);
        i.putExtra("todoId",id);
        i.putExtra("userId",userID);
        startActivity(i);
    }


});

 AddTodo.setOnClickListener(view -> {
     Intent i = new Intent(getApplicationContext(), AddTodoActivity.class);
     startActivity(i);
 });
    }


}
