package com.dorkduck.bookbarter.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by sushant on 28/5/17.
 */

public class BookProvider extends ContentProvider {

    static final String AUTHORITY = "com.dorkduck.bookbarter";
    static final String URL = "content://" + AUTHORITY + "/books";
    static final Uri uri = Uri.parse(URL);

    private SQLiteDatabase sqLiteDatabase;

    public DatabaseHelper databaseHelper;

    public static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static{
        uriMatcher.addURI(AUTHORITY,"books",0);
    }


    @Override
    public boolean onCreate() {
        databaseHelper = new DatabaseHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        sqLiteDatabase = databaseHelper.getWritableDatabase();

        long rowid = sqLiteDatabase.insert(Contract.Entries.TABLE_NAME,"",values);

        if(rowid>0){
            Uri uri_ = ContentUris.withAppendedId(uri,rowid);
            getContext().getContentResolver().notifyChange(uri_,null);
            return uri_;
        }
        throw new SQLException("Couldn't insert into database"+ uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
