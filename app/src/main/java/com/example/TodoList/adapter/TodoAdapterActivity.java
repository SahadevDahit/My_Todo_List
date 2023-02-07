package com.example.TodoList.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.TodoList.TodosItemActivity;
import com.example.TodoList.R;

import java.util.ArrayList;

public class TodoAdapterActivity  extends ArrayAdapter<TodosItemActivity> {
    Context context;
    public TodoAdapterActivity(@NonNull Context context, ArrayList<TodosItemActivity> todoList) {

        super(context, 0, todoList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_todo_item, parent, false);
        }
        TodosItemActivity todos = getItem(position);
        TextView textView1 = currentItemView.findViewById(R.id.id);
        textView1.setText(todos.getId());
        TextView textView2 = currentItemView.findViewById(R.id.title);
        textView2.setText(todos.getTitle());
        TextView textView3 = currentItemView.findViewById(R.id.subtitle);
        textView3.setText(todos.getSubtitle());


               return currentItemView;
    }
}


