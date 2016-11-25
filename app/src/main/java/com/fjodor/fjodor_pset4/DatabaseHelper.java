package com.fjodor.fjodor_pset4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String Table_Name = "TODO_LIST";

    public static final String _ID = "_id";
    public static final String TODO = "todo";

    static final String DB_NAME = "TODO_LIST.DB";

    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE = "create table " +
            Table_Name + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TODO + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }
}

