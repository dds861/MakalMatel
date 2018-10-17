package com.dd.database.sqlite.Activity1.Model;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dd.database.sqlite.Activity1.View.IView;
import com.dd.database.sqlite.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Model implements IModel {
    private SQLiteDatabase database;

    public Model(IView iView) {
        database = new DatabaseOpenHelper((Context) iView).getWritableDatabase();
    }


    @Override
    public List<String> getListFromDatabase() {

        String sqlQueryText = "SELECT * FROM makal";
        Cursor cursor = database.rawQuery(sqlQueryText, null);
        cursor.moveToFirst();

        List<String> list = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}