package com.dd.database.sqlite.zhaniltpashtar;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by dds86 on 16-Nov-17.
 */

public class DatabaseOpenHelperZhaniltpashtar extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "makal.db";
    private static final int DATABASE_VERSION = 1;


    public DatabaseOpenHelperZhaniltpashtar(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}