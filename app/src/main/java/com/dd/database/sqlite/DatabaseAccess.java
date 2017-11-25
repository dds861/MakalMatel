package com.dd.database.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess extends AppCompatActivity {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
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

    public List<Product> getList(int columnPosition) {

        //создаем список чтобы возвратить его
        List<Product> list = new ArrayList<>();
        //формируем запрос, здесь мы получаем все данные из таблицы "makal"
        String sqlQueryText = "SELECT * FROM makal";
        //Получаем колонку из таблицы т.е. делаем запрос в SQLite, и записываем все в курсор
        Cursor cursor = database.rawQuery(sqlQueryText, null);
        //перемещаем курсор на первую строку в таблице чтобы перебирать
        cursor.moveToFirst();
        //перебираем все строки колонки в таблице до последнего
        while (!cursor.isAfterLast()) {
            //получаем очередной макал мател из колонки
            String getMakal = cursor.getString(columnPosition);
            //проверяем не пустая ли ячейка в колонке
            if (!getMakal.equals("")) {
                //добавляем в список чтоб потом отправить этот список
                list.add(new Product(getMakal));
            }
            //перемещаемся на следующую строку в таблице
            cursor.moveToNext();
        }
        //закрываем курсор чтоб не было утечки памяти
        cursor.close();
        //возврашаем список
        return list;
    }
}