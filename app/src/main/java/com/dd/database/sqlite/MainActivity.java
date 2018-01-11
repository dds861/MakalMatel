package com.dd.database.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    private AdView mAdView;
    MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //реклама
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //получаем доступ к базе
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);

        //открываем соединение
        databaseAccess.open();

        //инициализируем listview
        listView = findViewById(R.id.listview1);

        //из базы данных получаем список данных
        List<Product> products = databaseAccess.getList(0);



        //саздаем и инициализируем адаптер
        myAdapter = new MyAdapter(getApplicationContext(), products);


        

        //присваем адаптер к listview
        listView.setAdapter(myAdapter);


        //создаем Кликер на item-ы в listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //создаем и инициализируем Intent
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);

                //добавляем дополнительный параметр, позицию которуб Кликнули
                intent.putExtra("position", position);

                //запускаем Intent, и открываем новый activity
                startActivity(intent);

            }
        });


    }


}
