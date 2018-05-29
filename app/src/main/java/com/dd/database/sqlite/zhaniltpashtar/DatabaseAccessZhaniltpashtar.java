package com.dd.database.sqlite.zhaniltpashtar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;

import com.dd.database.sqlite.R;
import com.dd.database.sqlite.SqlAccess.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccessZhaniltpashtar extends AppCompatActivity {
    private static DatabaseAccessZhaniltpashtar instance;
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private Context context;


    private DatabaseAccessZhaniltpashtar(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
        this.context = context;
    }

    //
    public static DatabaseAccessZhaniltpashtar getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccessZhaniltpashtar(context);
        }
        return instance;
    }

    //открываем соедединение с базой
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    //закрываем соедединение с базой
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    private Cursor getCursor(String tableName){

        Cursor cursor;
        String sqlQueryText = "SELECT * FROM " + tableName;
        cursor = database.rawQuery(sqlQueryText, null);

        return cursor;
    }

    public List<String> getListSkazki() {
        List<String> list = new ArrayList<>();

        Cursor cursor = getCursor(context.getString(R.string.tableZhaniltpashtar));
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (!cursor.getString(0).equals("")) {

                list.add(cursor.getString(0));
            }
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getListMakal() {
        List<String> list = new ArrayList<>();

        Cursor cursor = getCursor(context.getString(R.string.tableMakal));
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (!cursor.getString(0).equals("")) {

                list.add(cursor.getString(0));
            }
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


}