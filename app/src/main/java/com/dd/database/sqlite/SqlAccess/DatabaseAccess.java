package com.dd.database.sqlite.SqlAccess;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dd.database.sqlite.R;
import com.dd.database.sqlite.makalMatel.ModelMakalMatel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DatabaseAccess extends AppCompatActivity {


    //делаем запрос в базу и возвращаем Cursor
    private static Cursor getDatabaseCursor(Context context, String tableName) {

        //формируем запрос, здесь мы получаем все данные из таблицы "makal"
        SQLiteOpenHelper openHelper = new DatabaseOpenHelper(context);
        SQLiteDatabase database = openHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + tableName, null);

        //возвращаем Cursor
        return cursor;
    }

    //метод возвращаем список МакалМател по определенной колонке
    public static List<ModelMakalMatel> getColumnListFromTableSql(int columnPosition, Context context, String tableName) {


        //создаем список чтобы возвратить его
        List<ModelMakalMatel> list = new ArrayList<>();

        //Получаем Cursor из базы
        Cursor cursor = getDatabaseCursor(context, tableName);

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
                list.add(new ModelMakalMatel(getMakal));
            }

            //перемещаемся на следующую строку в таблице
            cursor.moveToNext();
        }

        //закрываем курсор и базу чтоб не было утечки памяти
        cursor.close();

        //возврашаем список
        return list;
    }


    //метод возвращает случайный макалМател
    public static String getRandomItemFromTableSql(Context context) {

        //из базы данных получаем список данных по полученной позиции
        List<ModelMakalMatel> listOfMakalFromDatabase = getColumnListFromTableSql(-1, context, context.getString(R.string.tableMakal));

        //получаем случайное число
        int randomNum = new Random().nextInt(listOfMakalFromDatabase.size());

        //getting random makal matel from list and replace all new lines (/n)
        String randomMakal = listOfMakalFromDatabase.get(randomNum).getTextView1().replaceAll("\\\\n", "\n");

        return randomMakal;
    }


    public List<String> getListOfMakalMatelsCategory(Context context) {

        List<String> stringList = new ArrayList<>();

        Cursor cursor = getDatabaseCursor(context, getString(R.string.tableMakal));

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            stringList.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();

        return stringList;
    }






    //----------------------------------------------------------------------------------------------------------
    //Ertegiler
    //----------------------------------------------------------------------------------------------------------
    private static DatabaseAccess instance;
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;

    public DatabaseAccess() {
    }

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

    public List<String> getListSkazkiCategory() {
        List<String> list = new ArrayList<>();
        String sqlQueryText = "SELECT names_of_skazki FROM names";
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

    public List<String> getNames(String tableName) {


        String sqlQueryText = "SELECT " + tableName + " FROM names";
        Log.i("autolog", "sqlQueryText: " + sqlQueryText);

        List<String> list = new ArrayList<>();
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

    public List<String> getTable(String tableSelect, String tableMain) {


        String sqlQueryText = "SELECT " + tableSelect + " FROM " + tableMain;
        Log.i("autolog", "sqlQueryText: " + sqlQueryText);

        List<String> list = new ArrayList<>();
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
}