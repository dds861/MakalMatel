package com.dd.database.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.android.gms.ads.AdView;

public class Main2Activity extends AppCompatActivity {

    ListView listView;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);




        //получаем доступ к базе
//        DatabaseAccess databaseAccess = DatabaseAccess.getDatabaseAccess(this);

        //открываем соединение
//        databaseAccess.open();

        //инициализируем listview
        listView = findViewById(R.id.listview2);

        //получаем из предыдущего activity какую позицию выбрали
        int position = getIntent().getIntExtra("position", 0);

        //из базы данных получаем список данных по полученной позиции
//        List<Product> products = DatabaseAccess2.getListOfMakal(position + 1, getApplicationContext());
//
//        саздаем и инициализируем адаптер
//        MyAdapter2 myAdapter = new MyAdapter2(getApplicationContext(), products);

        //присваем адаптер к listview
//        listView.setAdapter(myAdapter);



//
//

    }


}
