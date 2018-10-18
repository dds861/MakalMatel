package com.dd.database.sqlite.Widget.Model;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dd.database.sqlite.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModelWidget implements IModelWidget {
    private SQLiteDatabase database;

    public ModelWidget(DatabaseOpenHelper openHelper) {
        database = openHelper.getWritableDatabase();
    }

    //метод возвращает случайный макалМател
    @Override
    public String getRandomMakalFromDatabase() {

        Random rand = new Random();
        int max = 136;
        int min = 1;
        int columnPosition = rand.nextInt(max - min + 1) + min;
        Log.i("autolog", "random columnPosition: " + columnPosition);


        //создаем список чтобы возвратить его
        List<String> list = new ArrayList<>();

        //Получаем Cursor из базы
        String sqlQueryText = "SELECT * FROM makal";
        Cursor cursor = database.rawQuery(sqlQueryText, null);
        Log.i("autolog", "cursor size: " + cursor.getCount());

        //перемещаем курсор на первую строку в таблице чтобы перебирать
        cursor.moveToFirst();

//        if (columnPosition == -1) {
//            columnPosition = new Random().nextInt((cursor.getColumnCount()) + 1);
//        }

        //перебираем все строки колонки в таблице до последнего
        while (!cursor.isAfterLast()) {

            //получаем очередной макал мател из колонки
            String getMakal = cursor.getString(columnPosition);

            //проверяем не пустая ли ячейка в колонке
            if (!getMakal.equals("")) {
                //добавляем в список чтоб потом отправить этот список
                list.add(getMakal);
            }

            //перемещаемся на следующую строку в таблице
            cursor.moveToNext();
        }

        //закрываем курсор и базу чтоб не было утечки памяти
        cursor.close();


        int randomMakal = rand.nextInt(list.size());

        Log.i("autolog", "random item #: " + randomMakal);
        Log.i("autolog", "list.size(): " + list.size());
        //возврашаем список
        return list.get(randomMakal);
    }
}