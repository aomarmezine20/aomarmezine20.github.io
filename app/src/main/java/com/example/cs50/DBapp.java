package com.example.cs50;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBapp extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DB";
    public static final String TABLE_NAME = "user";
    public static final String COL_2 = "EMAIL";
    public static final String COL_3 = "USERNAME";
    public static final String COL_4 = "PASSWORD";


    public DBapp(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT , USERNAME TEXT,PASSWORD TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }


    //registration

    public boolean insertData(String email, String password, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ContentValues = new ContentValues();
        ContentValues.put(COL_2, email);
        ContentValues.put(COL_3, username);
        ContentValues.put(COL_4, password);
        long result = db.insert(TABLE_NAME, null, ContentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    //LOGIN

    public Cursor login_user(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res1 = db.rawQuery(" Select * from " + DBapp.TABLE_NAME + " where "+ DBapp.COL_2 + "=?" + " and "  + DBapp.COL_4+ "=?", new String[] {email,password});
        return res1;
    }

    public Cursor signup_user(String email,String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res2 = db.rawQuery(" Select * from " + DBapp.TABLE_NAME + " where "+ DBapp.COL_2 + "=?" + " and "  + DBapp.COL_3+ "=?", new String[] {email,username});
        return res2;
    }
}