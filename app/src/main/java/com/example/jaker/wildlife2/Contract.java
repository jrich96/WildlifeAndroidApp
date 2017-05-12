package com.example.jaker.wildlife2;

import android.provider.BaseColumns;

/**
 * Created by jaker on 06/05/2017.
 */

public final class Contract
{
    public static final String DB_NAME = "document.db";

    private Contract()
    {
    }

    public static class Document implements BaseColumns
    {
        public static final String TABLE_NAME = "document_table";
        public static final String COLNAME_id = "_id";
        public static final String COLNAME_name = "name";
        public static final String COLNAME_category = "category";
        public static final String COLNAME_amount = "amount";
        public static final String COLNAME_location = "location";
        public static final String COLNAME_activity = "activity";
    }
}
