package com.dd.database.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DatabaseAccess extends AppCompatActivity {

    private static SQLiteDatabase database;

    //делаем запрос в базу и возвращаем Cursor
    private static Cursor getDatabaseCursor(Context context) {

        //формируем запрос, здесь мы получаем все данные из таблицы "makal"
        SQLiteOpenHelper openHelper = new DatabaseOpenHelper(context);
        database = openHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM makal", null);

        //возвращаем Cursor
        return cursor;
    }

    //метод возвращаем список МакалМател по определенной колонке
    public static List<Product> getListOfMakal(int columnPosition, Context context) {


        //создаем список чтобы возвратить его
        List<Product> list = new ArrayList<>();

        //Получаем Cursor из базы
        Cursor cursor = getDatabaseCursor(context);

        //перемещаем курсор на первую строку в таблице чтобы перебирать
        cursor.moveToFirst();

        if (columnPosition == -1) {
            columnPosition = new Random().nextInt((cursor.getColumnCount()) + 1);
        }

        //перебираем все строки колонки в таблице до последнего
        while (!cursor.isAfterLast()) {

            //получаем очередной макал мател из колонки
            String getMakal = cursor.getString(columnPosition);
            Log.i("autolog", "columnPosition: " + columnPosition);

            //проверяем не пустая ли ячейка в колонке
            if (!getMakal.equals("")) {

                //добавляем в список чтоб потом отправить этот список
                list.add(new Product(getMakal));
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
    public static String getRandomMakalFromDatabase(Context context) {

        //из базы данных получаем список данных по полученной позиции
        List<Product> listOfMakalFromDatabase = getListOfMakal(-1, context);

        //получаем случайное число
        int randomNum = new Random().nextInt(listOfMakalFromDatabase.size());

        //getting random makal matel from list and replace all new lines (/n)
        String randomMakal = listOfMakalFromDatabase.get(randomNum).getTextView1().replaceAll("\\\\n", "\n");

        return randomMakal;
    }
}