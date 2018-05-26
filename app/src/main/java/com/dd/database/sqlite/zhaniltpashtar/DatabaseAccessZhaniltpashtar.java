package com.dd.database.sqlite.zhaniltpashtar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;

import com.dd.database.sqlite.R;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccessZhaniltpashtar extends AppCompatActivity {
    private static DatabaseAccessZhaniltpashtar instance;
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private Context context;


    private DatabaseAccessZhaniltpashtar(Context context) {
        this.openHelper = new DatabaseOpenHelperZhaniltpashtar(context);
        this.context = context;
    }

    public static DatabaseAccessZhaniltpashtar getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccessZhaniltpashtar(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public List<String> getListSkazkiCategory() {
        List<String> list = new ArrayList<>();
        String sqlQueryText = "SELECT * FROM " + context.getString(R.string.tableZhaniltpashtar);
        Cursor cursor = database.rawQuery(sqlQueryText, null);
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

//    public List<String> getNames(String tableName) {
//
//
//        String sqlQueryText = "SELECT " + tableName + " FROM names";
//        Log.i("autolog", "sqlQueryText: " + sqlQueryText);
//
//        List<String> list = new ArrayList<>();
//        Cursor cursor = database.rawQuery(sqlQueryText, null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            if (!cursor.getString(0).equals("")) {
//
//                list.add(cursor.getString(0));
//            }
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return list;
//    }

//    public List<String> getTable(String tableSelect, String tableMain) {
//
//
//        String sqlQueryText = "SELECT " + tableSelect + " FROM " + tableMain;
//        Log.i("autolog", "sqlQueryText: " + sqlQueryText);
//
//        List<String> list = new ArrayList<>();
//        Cursor cursor = database.rawQuery(sqlQueryText, null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            if (!cursor.getString(0).equals("")) {
//
//                list.add(cursor.getString(0));
//            }
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return list;
//    }

}