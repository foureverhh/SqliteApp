package com.example.zfgg04.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    //database name and table name
    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_MAME = "student_table";
    //column name
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        //Create a sql database instance
        //SQLiteDatabase db = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_MAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_MAME);
        onCreate(db);
    }

    public boolean insertDate(String name,String surname,String marks){
        //Create a sql database instance
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        long result =db.insert(TABLE_MAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    //To show the table on view
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        //Get all data in the db instance
        Cursor res = db.rawQuery("select * from "+TABLE_MAME,null);
        return res;
    }
}
