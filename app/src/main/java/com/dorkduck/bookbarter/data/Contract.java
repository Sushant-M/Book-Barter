package com.dorkduck.bookbarter.data;

import android.provider.BaseColumns;

/**
 * Created by sushant on 28/5/17.
 */

public final class Contract {

    private Contract(){}

    public static class Entries implements BaseColumns{
        public static final String TABLE_NAME = "Books";
        public static final String BOOK_NAME = "name";
        public static final String BOOK_AUTHOR = "author";
    }
}
