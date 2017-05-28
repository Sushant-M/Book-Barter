package com.dorkduck.bookbarter.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sushant on 28/5/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "book_barter";
    public final static int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public static final String CREATE_DATABASE = "CREATE TABLE "+
            Contract.Entries.TABLE_NAME + " (" +
            Contract.Entries._ID + " INTEGER PRIMARY KEY," +
            Contract.Entries.BOOK_NAME + " TEXT," +
            Contract.Entries.BOOK_AUTHOR + " TEXT)";

    public final static String DELETE_DATABASE = "DROP TABLE IF EXISTS "+ Contract.Entries.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_DATABASE);
        onCreate(db);
    }
}
