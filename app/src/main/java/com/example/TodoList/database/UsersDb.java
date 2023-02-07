package com.example.TodoList.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

public class UsersDb extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserDb.db";
    private static final String TABLE_NAME = "Users";
    private static final String col1 = "ID";
    private static final String col2 = "FirstName";
    private static final String col3 = "LastName";
    private static final String col4 = "UserName";
    private static final String col5 = "Password";

    public UsersDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("sd", "DataBase created .....");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryInsert = "CREATE TABLE " + TABLE_NAME + "( ID INTEGER PRIMARY KEY AUTOINCREMENT , FirstName TEXT ,LastName TEXT,UserName TEXT, Password TEXT )";
        db.execSQL(queryInsert);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean AddUser(List<String> data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col2, data.get(0).toString());
        values.put(col3, data.get(1).toString());
        values.put(col4, data.get(2).toString());
        values.put(col5, data.get(3).toString());
        boolean res = db.insert(TABLE_NAME, null, values) > 0;
        return res;
    }

    public void SignIn() {

    }

    public String SignIn(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String id=null;
        Cursor cursor=db.rawQuery("SELECT * FROM Users t WHERE t.username = '"+ username + "' AND t.password = '" + password+"'", null);
        if(cursor.getCount() >0){
            if (cursor.moveToFirst()) {
                do {
                     id=cursor.getString(0);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return id;
        } else {
            return null;
        }
    }
}



