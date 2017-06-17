package com.dorkduck.bookbarter;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by sushant on 17/6/17.
 */

public class Utils {
    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }
        return mDatabase;
    }
}
