package com.dd.database.sqlite.Activity2.Model;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dd.database.sqlite.Activity2.View.IView2;
import com.dd.database.sqlite.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model2 implements IModel2 {
    private SQLiteDatabase database;


    public Model2(IView2 iView2) {
        database = new DatabaseOpenHelper((Context) iView2).getWritableDatabase();
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
        database.close();
        return list;
    }

    //метод возвращаем список МакалМател по определенной колонке
    public List<String> getListOfMakal(int columnPosition, Context context) {


        //создаем список чтобы возвратить его
        List<String> list = new ArrayList<>();

        //Получаем Cursor из базы
        String sqlQueryText = "SELECT * FROM makal";
        Cursor cursor = database.rawQuery(sqlQueryText, null);

        //перемещаем курсор на первую строку в таблице чтобы перебирать
        cursor.moveToFirst();

        if (columnPosition == -1) {
            columnPosition = new Random().nextInt((cursor.getColumnCount()) + 1);
        }

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
        database.close();

        //возврашаем список
        return list;
    }

    //метод возвращает случайный макалМател
//    public String getRandomMakalFromDatabase(Context context) {
//
//        //из базы данных получаем список данных по полученной позиции
//        List<String> listOfMakalFromDatabase = getListOfMakal(-1, context);
//
//        //получаем случайное число
//        int randomNum = new Random().nextInt(listOfMakalFromDatabase.size());
//
//        //getting random makal matel from list and replace all new lines (/n)
//        String randomMakal = listOfMakalFromDatabase.get(randomNum).getTextView1().replaceAll("\\\\n", "\n");
//
//        return randomMakal;
//    }
}