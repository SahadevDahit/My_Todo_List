package com.example.TodoList.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.TodoList.TodosItemActivity;

import java.util.ArrayList;
import java.util.List;


public class TodoDb extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TodoDb.db";
    private static final String TABLE_NAME = "Todos_Table";
    private static final String col1 = "UserID";
    private static final String col2 = "Title";
    private static final String col3 = "SubTitle";
    private static final String col4 = "Description";
    public TodoDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("sd","Todo DataBase created .....");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryInsert = "CREATE TABLE " + TABLE_NAME+"( ID INTEGER PRIMARY KEY AUTOINCREMENT , UserID TEXT, Title TEXT ,SubTitle TEXT,Description TEXT )";
        db.execSQL(queryInsert);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public ArrayList<TodosItemActivity> getTodos(String userId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor Todos = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where  userid = " + userId, null);
        ArrayList<TodosItemActivity> todosData = new ArrayList<TodosItemActivity>();
        if (Todos.moveToFirst()) {
            do {
                todosData.add(new TodosItemActivity(Todos.getString(0),Todos.getString(2),Todos.getString(3)));
            } while (Todos.moveToNext());
        }
        Todos.close();

        return todosData;
    }

    public List<String> getTodosById(String todoid){
        ArrayList<String> todosData = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " where  id = " + todoid;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
              todosData.add(cursor.getString(2));
                todosData.add(cursor.getString(3));
                todosData.add(cursor.getString(4));
            }
        }
        return todosData;
    }
    public boolean AddTodo(List<String> toDoData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col1, toDoData.get(0));
        values.put(col2, toDoData.get(1));
        values.put(col3, toDoData.get(2));
        values.put(col4, toDoData.get(3));


        long res= db.insert(TABLE_NAME, null, values);
        if(res==-1) {
            return false;
        }else {
            return true;
        }
    }
    public boolean updateTodo(List<String> toDoData){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col2, toDoData.get(1));
        values.put(col3, toDoData.get(2));
        values.put(col4, toDoData.get(3));
        String id=toDoData.get(0);
      boolean res=db.update(TABLE_NAME, values, "id = '" + id + "'",null)>0;
        db.close();
      return res;
    }

    public boolean deleteTodo(String todoId) {
        SQLiteDatabase db = this.getWritableDatabase();
         boolean res=  db.delete(TABLE_NAME, "id = '" + todoId + "'", null) > 0;
        return res;
    }
}
